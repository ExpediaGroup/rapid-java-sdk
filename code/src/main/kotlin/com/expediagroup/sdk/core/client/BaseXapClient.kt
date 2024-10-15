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

import com.expediagroup.sdk.core.configuration.XapClientConfiguration
import com.expediagroup.sdk.core.configuration.collector.ConfigurationCollector
import com.expediagroup.sdk.core.configuration.provider.ConfigurationProvider
import com.expediagroup.sdk.core.configuration.provider.XapConfigurationProvider
import com.expediagroup.sdk.core.plugin.authentication.strategy.AuthenticationStrategy
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

/**
 * The integration point between the SDK Core and the product SDKs.
 *
 * @param httpClientEngine The HTTP client engine to use.
 * @param clientConfiguration The configuration for the client.
 */
abstract class BaseXapClient(
    namespace: String,
    clientConfiguration: XapClientConfiguration,
    httpClientEngine: HttpClientEngine = DEFAULT_HTTP_CLIENT_ENGINE
) : Client(namespace) {
    private val _configurationProvider: ConfigurationProvider =
        ConfigurationCollector.create(
            clientConfiguration.toProvider(),
            XapConfigurationProvider
        )
    private val _httpClient: HttpClient = buildHttpClient(_configurationProvider, AuthenticationStrategy.AuthenticationType.BASIC, httpClientEngine)

    init {
        finalize()
    }

    override val configurationProvider: ConfigurationProvider
        get() = _configurationProvider

    override val httpClient: HttpClient
        get() = _httpClient

    /** A [BaseXapClient] builder. */
    @Suppress("unused", "UnnecessaryAbstractClass") // This is used by the generated SDK clients.
    abstract class Builder<SELF : Builder<SELF>> : Client.Builder<SELF>()
}
