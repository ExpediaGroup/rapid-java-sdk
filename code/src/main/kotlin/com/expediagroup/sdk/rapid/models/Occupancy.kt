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
 * Occupancy information.
 * @param maxAllowed
 * @param ageCategories Map of the age categories used to determine the maximum children and adult occupancy.
 */
data class Occupancy(
    @JsonProperty("max_allowed")
    @field:Valid
    val maxAllowed: MaxAllowed? = null,
    // Map of the age categories used to determine the maximum children and adult occupancy.
    @JsonProperty("age_categories")
    @field:Valid
    val ageCategories: kotlin.collections.Map<kotlin.String, CategoryAge>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var maxAllowed: MaxAllowed? = null,
        private var ageCategories: kotlin.collections.Map<kotlin.String, CategoryAge>? = null
    ) {
        fun maxAllowed(maxAllowed: MaxAllowed?) = apply { this.maxAllowed = maxAllowed }

        fun ageCategories(ageCategories: kotlin.collections.Map<kotlin.String, CategoryAge>?) = apply { this.ageCategories = ageCategories }

        fun build(): Occupancy {
            return Occupancy(
                maxAllowed = maxAllowed,
                ageCategories = ageCategories
            )
        }
    }
}
