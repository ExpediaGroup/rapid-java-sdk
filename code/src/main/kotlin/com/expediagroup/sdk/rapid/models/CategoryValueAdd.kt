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
* The general category that this value add promotion falls into.
* Values: FOOD_AND_BEVERAGE,ENTERTAINMENT,SERVICE,ACTIVITY,CREDIT
*/
enum class CategoryValueAdd(val value: kotlin.String) {
    @JsonProperty("food_and_beverage")
    FOOD_AND_BEVERAGE("food_and_beverage"),

    @JsonProperty("entertainment")
    ENTERTAINMENT("entertainment"),

    @JsonProperty("service")
    SERVICE("service"),

    @JsonProperty("activity")
    ACTIVITY("activity"),

    @JsonProperty("credit")
    CREDIT("credit")
}
