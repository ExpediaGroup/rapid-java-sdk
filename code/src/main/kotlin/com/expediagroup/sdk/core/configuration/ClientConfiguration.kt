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
package com.expediagroup.sdk.core.configuration

import com.expediagroup.sdk.core.configuration.provider.RuntimeConfigurationProvider
import okhttp3.OkHttpClient

interface ClientConfiguration {
    val key: String?
    val secret: String?
    val endpoint: String?
    val requestTimeout: Long?
    val connectionTimeout: Long?
    val socketTimeout: Long?
    val maskedLoggingHeaders: Set<String>?
    val maskedLoggingBodyFields: Set<String>?
    val okHttpClient: OkHttpClient?

    /** Build a [RuntimeConfigurationProvider] from a [ClientConfiguration]. */
    fun toProvider(): RuntimeConfigurationProvider =
        RuntimeConfigurationProvider(
            key = key,
            secret = secret,
            endpoint = endpoint,
            requestTimeout = requestTimeout,
            connectionTimeout = connectionTimeout,
            socketTimeout = socketTimeout,
            maskedLoggingHeaders = maskedLoggingHeaders,
            maskedLoggingBodyFields = maskedLoggingBodyFields,
            okHttpClient = okHttpClient
        )
}
