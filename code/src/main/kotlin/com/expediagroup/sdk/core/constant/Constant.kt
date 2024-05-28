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

import io.ktor.client.plugins.HttpTimeout

internal object Constant {
    const val EMPTY_STRING = ""
    const val TEN_SECONDS_IN_MILLIS = 10_000L
    const val FIFTEEN_SECONDS_IN_MILLIS = 15_000L
    const val INFINITE_TIMEOUT = HttpTimeout.INFINITE_TIMEOUT_MS

    private const val SUCCESSFUL_STATUS_CODES_RANGE_START = 200
    private const val SUCCESSFUL_STATUS_CODES_RANGE_END = 299
    val SUCCESSFUL_STATUS_CODES_RANGE: IntRange = SUCCESSFUL_STATUS_CODES_RANGE_START..SUCCESSFUL_STATUS_CODES_RANGE_END
}
