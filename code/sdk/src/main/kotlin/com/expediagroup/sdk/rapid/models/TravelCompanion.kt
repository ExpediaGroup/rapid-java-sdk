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
* The companion category for any travelers that accompanied this reviewer.
* Values: FAMILY,FAMILY_WITH_CHILDREN,PARTNER,SELF,FRIENDS,PET
*/
enum class TravelCompanion(val value: kotlin.String) {
    @JsonProperty("family")
    FAMILY("family"),

    @JsonProperty("family_with_children")
    FAMILY_WITH_CHILDREN("family_with_children"),

    @JsonProperty("partner")
    PARTNER("partner"),

    @JsonProperty("self")
    SELF("self"),

    @JsonProperty("friends")
    FRIENDS("friends"),

    @JsonProperty("pet")
    PET("pet")
}
