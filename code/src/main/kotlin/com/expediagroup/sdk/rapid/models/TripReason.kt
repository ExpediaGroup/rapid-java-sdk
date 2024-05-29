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

package com.expediagroup.sdk.rapid.models

import com.fasterxml.jackson.annotation.JsonProperty

/**
* The reason category for this reviewer's trip.
* Values: BUSINESS,LEISURE,FRIENDS_AND_FAMILY,BUSINESS_AND_LEISURE
*/
enum class TripReason(val value: kotlin.String) {
    @JsonProperty("business")
    BUSINESS("business"),

    @JsonProperty("leisure")
    LEISURE("leisure"),

    @JsonProperty("friends_and_family")
    FRIENDS_AND_FAMILY("friends_and_family"),

    @JsonProperty("business_and_leisure")
    BUSINESS_AND_LEISURE("business_and_leisure")
}
