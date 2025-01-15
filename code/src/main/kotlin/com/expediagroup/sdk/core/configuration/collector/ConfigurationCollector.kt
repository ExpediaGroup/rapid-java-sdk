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
package com.expediagroup.sdk.core.configuration.collector

import com.expediagroup.sdk.core.configuration.provider.ConfigurationProvider
import com.expediagroup.sdk.core.constant.ConfigurationName.AUTH_ENDPOINT
import com.expediagroup.sdk.core.constant.ConfigurationName.CONFIGURATION_COLLECTOR
import com.expediagroup.sdk.core.constant.ConfigurationName.CONNECTION_TIMEOUT_MILLIS
import com.expediagroup.sdk.core.constant.ConfigurationName.ENDPOINT
import com.expediagroup.sdk.core.constant.ConfigurationName.KEY
import com.expediagroup.sdk.core.constant.ConfigurationName.MASKED_LOGGING_HEADERS
import com.expediagroup.sdk.core.constant.ConfigurationName.REQUEST_TIMEOUT_MILLIS
import com.expediagroup.sdk.core.constant.ConfigurationName.SECRET
import com.expediagroup.sdk.core.constant.ConfigurationName.SOCKET_TIMEOUT_MILLIS
import com.expediagroup.sdk.core.constant.provider.LoggingMessageProvider
import com.expediagroup.sdk.core.plugin.logging.ExpediaGroupLoggerFactory
import okhttp3.OkHttpClient

/**
 * Configuration collector that collects configuration from all available providers.
 *
 * @param providers A configuration providers queue.
 */
internal class ConfigurationCollector private constructor(providers: ConfigurationProviderQueue) : ConfigurationProvider {
    override val name: String = CONFIGURATION_COLLECTOR

    companion object Factory {
        private val log = ExpediaGroupLoggerFactory.getLogger(ConfigurationCollector::class.java)

        /**
         * Creates a new [ConfigurationCollector] with the given [providerQueue].
         *
         * @param providerQueue the [ConfigurationProviderQueue] to use.
         * @return a new [ConfigurationCollector] with the given [providerQueue].
         */
        fun create(providerQueue: ConfigurationProviderQueue): ConfigurationCollector = ConfigurationCollector(providerQueue)

        /**
         * Creates a new [ConfigurationCollector] with the given [providers].
         *
         * @param providers the [ConfigurationProvider]s to use.
         * @return a new [ConfigurationCollector] with the given [providers].
         */
        fun create(vararg providers: ConfigurationProvider): ConfigurationCollector = create(ConfigurationProviderQueue.from(providers.asList()))
    }

    override val key: String? = providers.firstWith { it.key }.also { it?.log(KEY) }?.retrieve()
    override val secret: String? = providers.firstWith { it.secret }.also { it?.log(SECRET) }?.retrieve()
    override val endpoint: String? = providers.firstWith { it.endpoint }.also { it?.log(ENDPOINT) }?.retrieve()
    override val authEndpoint: String? = providers.firstWith { it.authEndpoint }.also { it?.log(AUTH_ENDPOINT) }?.retrieve()
    override val requestTimeout: Long? = providers.firstWith { it.requestTimeout }.also { it?.log(REQUEST_TIMEOUT_MILLIS) }?.retrieve()
    override val connectionTimeout: Long? = providers.firstWith { it.connectionTimeout }.also { it?.log(CONNECTION_TIMEOUT_MILLIS) }?.retrieve()
    override val socketTimeout: Long? = providers.firstWith { it.socketTimeout }.also { it?.log(SOCKET_TIMEOUT_MILLIS) }?.retrieve()
    override val maskedLoggingHeaders: Set<String>? = providers.firstWith { it.maskedLoggingHeaders }.also { it?.log(MASKED_LOGGING_HEADERS) }?.retrieve()
    override val okHttpClient: OkHttpClient? = providers.firstWith { it.okHttpClient }?.retrieve()

    private fun <T> ProvidedConfiguration<T>.log(configurationName: String) {
        log.info(LoggingMessageProvider.getChosenProviderMessage(configurationName, providerName))
    }
}
