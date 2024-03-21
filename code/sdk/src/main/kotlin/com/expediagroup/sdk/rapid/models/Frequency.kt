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
* The frequency of when this applies.
* Values: UNKNOWN,PER_NIGHT,PER_DAY,PER_STAY,PER_WEEK,ROUND_TRIP,ONE_WAY
*/
enum class Frequency(val value: kotlin.String) {
    @JsonProperty("unknown")
    UNKNOWN("unknown"),

    @JsonProperty("per_night")
    PER_NIGHT("per_night"),

    @JsonProperty("per_day")
    PER_DAY("per_day"),

    @JsonProperty("per_stay")
    PER_STAY("per_stay"),

    @JsonProperty("per_week")
    PER_WEEK("per_week"),

    @JsonProperty("round_trip")
    ROUND_TRIP("round_trip"),

    @JsonProperty("one_way")
    ONE_WAY("one_way")
}
