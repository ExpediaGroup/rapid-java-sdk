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
package com.expediagroup.sdk.core.configuration.provider

import com.expediagroup.sdk.core.constant.Constant

/**
 * A configuration provider that can be used to provide configuration values.
 */
interface ConfigurationProvider {
    /** The name of the provider. */
    val name: String

    /** The API key to use for authentication. */
    val key: String?
        get() = Constant.EMPTY_STRING

    /** The API secret to use for authentication. */
    val secret: String?
        get() = Constant.EMPTY_STRING

    /** The API endpoint to use for requests. */
    val endpoint: String?

    /** The API endpoint to use for authentication. */
    val authEndpoint: String?
        get() = Constant.EMPTY_STRING

    /** The time period from the start of the request to the completion of the response. */
    val requestTimeout: Long?

    /** The time period from the start of the request to the establishment of the connection with the server. */
    val connectionTimeout: Long?

    /** The maximum period of inactivity between two consecutive data packets. */
    val socketTimeout: Long?

    /** The headers to be masked in logging. */
    val maskedLoggingHeaders: Set<String>?
        get() = setOf()

    /** The body fields to be masked in logging.*/
    val maskedLoggingBodyFields: Set<String>?
        get() = setOf()
}
