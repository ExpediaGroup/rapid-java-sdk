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
package com.expediagroup.sdk.core.constant

internal object LoggingMessage {
    const val LOGGING_PREFIX = "ExpediaSDK:"

    const val TOKEN_RENEWAL_IN_PROGRESS = "Renewing token"

    const val TOKEN_RENEWAL_SUCCESSFUL = "Token renewal successful"

    const val TOKEN_CLEARING_IN_PROGRESS = "Clearing tokens"

    const val TOKEN_CLEARING_SUCCESSFUL = "Tokens successfully cleared"

    const val TOKEN_EXPIRED = "Token expired or is about to expire. Request will wait until token is renewed"

    const val OMITTED = "<-- omitted -->"

    const val LOG_LIMIT = 4094

    const val TRUNCATED = "<-- truncated -->"
}
