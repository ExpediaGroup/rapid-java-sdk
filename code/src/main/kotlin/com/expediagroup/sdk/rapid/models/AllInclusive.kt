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

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * Information about the all-inclusive rate plans available at the property.
 * @param allRatePlans Indicates if all rate plans at the property provide all-inclusive amenities.
 * @param someRatePlans Indicates if some, but not all, rate plans at the property provide all-inclusive amenities.
 * @param details Details about amenities and services included in the all-inclusive rates.
 */
data class AllInclusive(
    // Indicates if all rate plans at the property provide all-inclusive amenities.
    @JsonProperty("all_rate_plans")
    @field:Valid
    val allRatePlans: kotlin.Boolean? = null,
    // Indicates if some, but not all, rate plans at the property provide all-inclusive amenities.
    @JsonProperty("some_rate_plans")
    @field:Valid
    val someRatePlans: kotlin.Boolean? = null,
    // Details about amenities and services included in the all-inclusive rates.
    @JsonProperty("details")
    @field:Valid
    val details: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var allRatePlans: kotlin.Boolean? = null,
        private var someRatePlans: kotlin.Boolean? = null,
        private var details: kotlin.String? = null
    ) {
        fun allRatePlans(allRatePlans: kotlin.Boolean?) = apply { this.allRatePlans = allRatePlans }

        fun someRatePlans(someRatePlans: kotlin.Boolean?) = apply { this.someRatePlans = someRatePlans }

        fun details(details: kotlin.String?) = apply { this.details = details }

        fun build(): AllInclusive {
            return AllInclusive(
                allRatePlans = allRatePlans,
                someRatePlans = someRatePlans,
                details = details
            )
        }
    }
}
