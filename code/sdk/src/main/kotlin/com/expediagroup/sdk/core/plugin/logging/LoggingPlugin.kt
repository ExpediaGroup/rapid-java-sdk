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
import com.expediagroup.sdk.core.constant.ExceptionMessage
import com.expediagroup.sdk.core.constant.LoggingMessage
import com.expediagroup.sdk.core.model.exception.client.ExpediaGroupClientException
import com.expediagroup.sdk.core.plugin.Plugin
import io.ktor.client.plugins.logging.Logging

internal object LoggingPlugin : Plugin<LoggingConfiguration> {
    val clientLoggingMaskedFieldsProviders = mutableMapOf<Client, LoggingMaskedFieldsProvider>()

    override fun install(
        client: Client,
        configurations: LoggingConfiguration
    ) {
        clientLoggingMaskedFieldsProviders[client] =
            LoggingMaskedFieldsProvider(
                configurations.maskedLoggingHeaders,
                configurations.maskedLoggingBodyFields
            )
        configurations.httpClientConfiguration.install(Logging) {
            logger = configurations.getLogger(client)
            level = configurations.level
            sanitizeHeader(LoggingMessage.OMITTED) { header ->
                client.getLoggingMaskedFieldsProvider().getMaskedHeaderFields().contains(header)
            }
        }
        configurations.httpClientConfiguration.install(RequestBodyLogger)
        configurations.httpClientConfiguration.install(ResponseBodyLogger)
    }
}

internal fun Client.getLoggingMaskedFieldsProvider(): LoggingMaskedFieldsProvider =
    LoggingPlugin.clientLoggingMaskedFieldsProviders[this] ?: throw ExpediaGroupClientException(ExceptionMessage.LOGGING_MASKED_FIELDS_NOT_CONFIGURED_FOR_CLIENT)
