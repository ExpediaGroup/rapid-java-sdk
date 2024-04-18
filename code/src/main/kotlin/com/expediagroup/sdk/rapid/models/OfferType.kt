/*
 * Copyright (C) 2024 Expedia, Inc.
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
package com.expediagroup.sdk.rapid.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
* The type of offer this value add promotion is.
* Values: BUY_ONE_GET_ONE_FREE,CREDIT,DISCOUNT,FREE,VOUCHER
*/
enum class OfferType(val value: kotlin.String) {
    @JsonProperty("buy_one_get_one_free")
    BUY_ONE_GET_ONE_FREE("buy_one_get_one_free"),

    @JsonProperty("credit")
    CREDIT("credit"),

    @JsonProperty("discount")
    DISCOUNT("discount"),

    @JsonProperty("free")
    FREE("free"),

    @JsonProperty("voucher")
    VOUCHER("voucher"),
}
