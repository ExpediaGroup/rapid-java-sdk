/*
 * Copyright (C) 2022 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.sdk.core.client

import com.expediagroup.sdk.core.configuration.Credentials
import com.expediagroup.sdk.core.configuration.provider.ConfigurationProvider
import com.expediagroup.sdk.core.constant.ConfigurationName
import com.expediagroup.sdk.core.constant.Constant
import com.expediagroup.sdk.core.constant.provider.ExceptionMessageProvider.getMissingRequiredConfigurationMessage
import com.expediagroup.sdk.core.constant.provider.LoggingMessageProvider
import com.expediagroup.sdk.core.contract.Contract
import com.expediagroup.sdk.core.contract.adhereTo
import com.expediagroup.sdk.core.model.exception.client.ExpediaGroupConfigurationException
import com.expediagroup.sdk.core.model.getTransactionId
import com.expediagroup.sdk.core.plugin.Hooks
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationHookFactory
import com.expediagroup.sdk.core.plugin.authentication.AuthenticationPlugin
import com.expediagroup.sdk.core.plugin.authentication.strategy.AuthenticationStrategy
import com.expediagroup.sdk.core.plugin.encoding.EncodingConfiguration
import com.expediagroup.sdk.core.plugin.encoding.EncodingPlugin
import com.expediagroup.sdk.core.plugin.exception.ExceptionHandlingConfiguration
import com.expediagroup.sdk.core.plugin.exception.ExceptionHandlingPlugin
import com.expediagroup.sdk.core.plugin.hooks
import com.expediagroup.sdk.core.plugin.httptimeout.HttpTimeoutConfiguration
import com.expediagroup.sdk.core.plugin.httptimeout.HttpTimeoutPlugin
import com.expediagroup.sdk.core.plugin.logging.ExpediaGroupLoggerFactory
import com.expediagroup.sdk.core.plugin.logging.LoggingConfiguration
import com.expediagroup.sdk.core.plugin.logging.LoggingPlugin
import com.expediagroup.sdk.core.plugin.plugins
import com.expediagroup.sdk.core.plugin.request.DefaultRequestConfiguration
import com.expediagroup.sdk.core.plugin.request.DefaultRequestPlugin
import com.expediagroup.sdk.core.plugin.serialization.SerializationConfiguration
import com.expediagroup.sdk.core.plugin.serialization.SerializationPlugin
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request

val DEFAULT_HTTP_CLIENT_ENGINE: HttpClientEngine =
    OkHttp.create {
        config {
            eventListener(OkHttpEventListener)
        }
    }

/**
 * The base integration point between the SDK Core and the product SDKs.
 */
abstract class Client(
    namespace: String,
    environmentProvider: EnvironmentProvider = DefaultEnvironmentProvider(namespace)
) : EnvironmentProvider by environmentProvider {
    private val httpHandler = DefaultHttpHandler(environmentProvider)

    companion object {
        private val log = ExpediaGroupLoggerFactory.getLogger(this::class.java)
    }

    /** The configuration provider to use. */
    abstract val configurationProvider: ConfigurationProvider

    /** The HTTP client to perform requests with. */
    abstract val httpClient: HttpClient

    internal fun buildHttpClient(
        configurationProvider: ConfigurationProvider,
        authenticationType: AuthenticationStrategy.AuthenticationType,
        httpClientEngine: HttpClientEngine = DEFAULT_HTTP_CLIENT_ENGINE
    ): HttpClient =
        HttpClient(httpClientEngine) {
            val httpClientConfig = this

            val key: String = configurationProvider.key ?: fireMissingConfigurationIssue(ConfigurationName.KEY)
            val secret: String = configurationProvider.secret ?: fireMissingConfigurationIssue(ConfigurationName.SECRET)
            val endpoint: String = configurationProvider.endpoint ?: fireMissingConfigurationIssue(ConfigurationName.ENDPOINT)
            val authEndpoint: String = configurationProvider.authEndpoint ?: fireMissingConfigurationIssue(ConfigurationName.AUTH_ENDPOINT)
            val requestTimeout: Long = configurationProvider.requestTimeout ?: fireMissingConfigurationIssue(ConfigurationName.REQUEST_TIMEOUT_MILLIS)
            val connectionTimeout: Long = configurationProvider.connectionTimeout ?: fireMissingConfigurationIssue(ConfigurationName.CONNECTION_TIMEOUT_MILLIS)
            val socketTimeout: Long = configurationProvider.socketTimeout ?: fireMissingConfigurationIssue(ConfigurationName.SOCKET_TIMEOUT_MILLIS)
            val maskedLoggingHeaders: Set<String> = configurationProvider.maskedLoggingHeaders ?: setOf()
            val maskedLoggingBodyFields: Set<String> = configurationProvider.maskedLoggingBodyFields ?: setOf()

            val authenticationConfiguration =
                AuthenticationConfiguration.from(
                    httpClientConfig,
                    Credentials.from(key, secret),
                    authEndpoint,
                    authenticationType
                )

            plugins {
                use(LoggingPlugin).with(LoggingConfiguration.from(httpClientConfig, maskedLoggingHeaders, maskedLoggingBodyFields))
                use(SerializationPlugin).with(SerializationConfiguration.from(httpClientConfig))
                use(AuthenticationPlugin).with(authenticationConfiguration)
                use(DefaultRequestPlugin).with(DefaultRequestConfiguration.from(httpClientConfig, endpoint))
                use(EncodingPlugin).with(EncodingConfiguration.from(httpClientConfig))
                use(HttpTimeoutPlugin).with(HttpTimeoutConfiguration.from(httpClientConfig, requestTimeout, connectionTimeout, socketTimeout))
                use(ExceptionHandlingPlugin).with(ExceptionHandlingConfiguration.from(httpClientConfig))
            }

            hooks {
                use(AuthenticationHookFactory).with(authenticationConfiguration)
            }
        }

    /** Throw an exception if the configuration is missing. */
    private fun fireMissingConfigurationIssue(configurationKey: String): Nothing = throw ExpediaGroupConfigurationException(getMissingRequiredConfigurationMessage(configurationKey))

    private fun isNotSuccessfulResponse(response: HttpResponse) = response.status.value !in Constant.SUCCESSFUL_STATUS_CODES_RANGE

    @Suppress("unused") // This is used by the product SDKs.
    suspend fun throwIfError(response: HttpResponse, operationId: String) {
        if (isNotSuccessfulResponse(response)) {
            log.info(LoggingMessageProvider.getResponseUnsuccessfulMessage(response.status, response.request.headers.getTransactionId()))
            throwServiceException(response, operationId)
        }
    }

    abstract suspend fun throwServiceException(
        response: HttpResponse,
        operationId: String
    )

    suspend fun performGet(url: String): HttpResponse = httpHandler.performGet(httpClient, url)

    /**
     * A [Client] builder.
     */
    abstract class Builder<SELF : Builder<SELF>> {
        /** Sets the API key to use for authentication. */
        protected var key: String? = null

        /** Sets the API secret to use for authentication. */
        protected var secret: String? = null

        /** Sets the API endpoint to use for requests. */
        protected var endpoint: String? = null

        /**
         * Sets the request timeout in milliseconds.
         *
         * Request timeout is the time period from the start of the request to the completion of the response.
         *
         * Default is infinite - no timeout.
         */
        protected var requestTimeout: Long? = null

        /**
         * Sets the connection timeout in milliseconds.
         *
         * Connection timeout is the time period from the start of the request to the establishment of the connection with the server.
         *
         * Default is 10 seconds (10000 milliseconds).
         */
        protected var connectionTimeout: Long? = null

        /**
         * Sets the socket timeout in milliseconds.
         *
         * Socket timeout is the maximum period of inactivity between two consecutive data packets.
         *
         * Default is 15 seconds (15000 milliseconds).
         */
        protected var socketTimeout: Long? = null

        /** Sets tne body fields to be masked in logging. */
        protected var maskedLoggingHeaders: Set<String>? = null

        /** Sets tne body fields to be masked in logging. */
        protected var maskedLoggingBodyFields: Set<String>? = null

        /** Sets the API key to use for authentication.
         *
         * @param key The API key to use for authentication.
         * @return The [Builder] instance.
         */
        fun key(key: String): SELF {
            this.key = key
            return self()
        }

        /** Sets the API secret to use for authentication.
         *
         * @param secret The API secret to use for authentication.
         * @return The [Builder] instance.
         */
        fun secret(secret: String): SELF {
            this.secret = secret
            return self()
        }

        /** Sets the API endpoint to use for requests.
         *
         * @param endpoint The API endpoint to use for requests.
         * @return The [Builder] instance.
         */
        fun endpoint(endpoint: String): SELF {
            this.endpoint = endpoint.adhereTo(Contract.TRAILING_SLASH)
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.ENDPOINT, endpoint))
            return self()
        }

        /**
         * Sets the request timeout in milliseconds.
         * Request timeout is the time period from the start of the request to the completion of the response.
         * Default is infinite - no timeout.
         *
         * @param milliseconds The request timeout to be used.
         * @return The [Builder] instance.
         */
        fun requestTimeout(milliseconds: Long): SELF {
            this.requestTimeout = milliseconds
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.REQUEST_TIMEOUT_MILLIS, milliseconds.toString()))
            return self()
        }

        /**
         * Sets the connection timeout in milliseconds.
         * Connection timeout is the time period from the start of the request to the establishment of the connection with the server.
         * Default is 10 seconds (10000 milliseconds).
         *
         * @param milliseconds The connection timeout to be used.
         * @return The [Builder] instance.
         */
        fun connectionTimeout(milliseconds: Long): SELF {
            this.connectionTimeout = milliseconds
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.CONNECTION_TIMEOUT_MILLIS, milliseconds.toString()))
            return self()
        }

        /**
         * Sets the socket timeout in milliseconds.
         * Socket timeout is the maximum period of inactivity between two consecutive data packets.
         * Default is 15 seconds (15000 milliseconds).
         *
         * @param milliseconds The socket timeout to be used.
         * @return The [Builder] instance.
         */
        fun socketTimeout(milliseconds: Long): SELF {
            this.socketTimeout = milliseconds
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.SOCKET_TIMEOUT_MILLIS, milliseconds.toString()))
            return self()
        }

        /**
         * Sets tne headers to be masked in logging.
         *
         * @param headers the headers to be masked in logging.
         * @return The [Builder] instance.
         */
        fun maskedLoggingHeaders(vararg headers: String): SELF {
            this.maskedLoggingHeaders = headers.toSet()
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.MASKED_LOGGING_HEADERS, headers.joinToString()))
            return self()
        }

        /**
         * Sets tne body fields to be masked in logging.
         *
         * @param fields the body fields to be masked in logging.
         * @return The [Builder] instance.
         */
        fun maskedLoggingBodyFields(vararg fields: String): SELF {
            this.maskedLoggingBodyFields = fields.toSet()
            log.info(LoggingMessageProvider.getRuntimeConfigurationProviderMessage(ConfigurationName.MASKED_LOGGING_BODY_FIELDS, fields.joinToString()))
            return self()
        }

        /** Create a [Client] object. */
        abstract fun build(): Client

        @Suppress("UNCHECKED_CAST") // This is safe because of the type parameter
        protected open fun self(): SELF = this as SELF
    }
}

/** Executes the hooks for the client. */
fun <T : Client> T.finalize() = Hooks.execute(this)
