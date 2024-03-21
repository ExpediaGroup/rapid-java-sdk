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
 *
 * @param propertyId Expedia property ID.
 * @param days
 */
data class PropertyCalendarAvailability(
    // Expedia property ID.
    @JsonProperty("property_id")
    @field:Valid
    val propertyId: kotlin.String? = null,
    @JsonProperty("days")
    @field:Valid
    val days: kotlin.collections.List<Day>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var days: kotlin.collections.List<Day>? = null
    ) {
        fun propertyId(propertyId: kotlin.String?) = apply { this.propertyId = propertyId }

        fun days(days: kotlin.collections.List<Day>?) = apply { this.days = days }

        fun build(): PropertyCalendarAvailability {
            return PropertyCalendarAvailability(
                propertyId = propertyId,
                days = days
            )
        }
    }
}
