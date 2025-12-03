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

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.rapid.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Rates returned are always AVAILABLE.  PRICE_CHANGED and SOLD_OUT are never returned for rates in the Availability
 * call.
 * Values: AVAILABLE, PRICE_CHANGED, SOLD_OUT
 */
@Deprecated("This is replaced by Rate.Status")
enum class Status(val value: kotlin.String) {
    @JsonProperty("available")
    AVAILABLE("available"),

    @Deprecated("Never Returned")
    @JsonProperty("price_changed")
    PRICE_CHANGED("price_changed"),

    @Deprecated("Never Returned")
    @JsonProperty("sold_out")
    SOLD_OUT("sold_out")
}
