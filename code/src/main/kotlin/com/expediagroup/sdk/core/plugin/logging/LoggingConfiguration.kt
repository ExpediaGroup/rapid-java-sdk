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
package com.expediagroup.sdk.core.plugin.logging

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.plugin.KtorPluginConfiguration
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger

internal data class LoggingConfiguration(
    override val httpClientConfiguration: HttpClientConfig<out HttpClientEngineConfig>,
    val maskedLoggingHeaders: Set<String>,
    val maskedLoggingBodyFields: Set<String>,
    val level: LogLevel = LogLevel.HEADERS,
    val getLogger: (client: Client) -> Logger = createCustomLogger
) : KtorPluginConfiguration(httpClientConfiguration) {
    companion object {
        fun from(
            httpClientConfig: HttpClientConfig<out HttpClientEngineConfig>,
            maskedLoggingHeaders: Set<String>,
            maskedLoggingBodyFields: Set<String>
        ) = LoggingConfiguration(httpClientConfig, maskedLoggingHeaders, maskedLoggingBodyFields)
    }
}

private val createCustomLogger: (client: Client) -> Logger
    get() = {
        object : Logger {
            val delegate = ExpediaGroupLoggerFactory.getLogger(Client::class.java, it)

            override fun log(message: String) = delegate.info(message)
        }
    }
