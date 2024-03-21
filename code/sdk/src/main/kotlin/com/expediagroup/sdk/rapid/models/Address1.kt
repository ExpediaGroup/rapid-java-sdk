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
 * @param line1 First line of street address.
 * @param line2 Second line of street address.
 * @param line3 Third line of street address.
 * @param city City where address is located.
 * @param stateProvinceCode State or province code of address, if applicable.
 * @param postalCode Postal code of address, if applicable.
 * @param countryCode Country code, in two-letter ISO 3166-1 alpha-2 format.
 */
data class Address1(
    // First line of street address.
    @JsonProperty("line_1")
    @field:Valid
    val line1: kotlin.String? = null,
    // Second line of street address.
    @JsonProperty("line_2")
    @field:Valid
    val line2: kotlin.String? = null,
    // Third line of street address.
    @JsonProperty("line_3")
    @field:Valid
    val line3: kotlin.String? = null,
    // City where address is located.
    @JsonProperty("city")
    @field:Valid
    val city: kotlin.String? = null,
    // State or province code of address, if applicable.
    @JsonProperty("state_province_code")
    @field:Valid
    val stateProvinceCode: kotlin.String? = null,
    // Postal code of address, if applicable.
    @JsonProperty("postal_code")
    @field:Valid
    val postalCode: kotlin.String? = null,
    // Country code, in two-letter ISO 3166-1 alpha-2 format.
    @JsonProperty("country_code")
    @field:Valid
    val countryCode: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var line1: kotlin.String? = null,
        private var line2: kotlin.String? = null,
        private var line3: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var stateProvinceCode: kotlin.String? = null,
        private var postalCode: kotlin.String? = null,
        private var countryCode: kotlin.String? = null
    ) {
        fun line1(line1: kotlin.String?) = apply { this.line1 = line1 }

        fun line2(line2: kotlin.String?) = apply { this.line2 = line2 }

        fun line3(line3: kotlin.String?) = apply { this.line3 = line3 }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun stateProvinceCode(stateProvinceCode: kotlin.String?) = apply { this.stateProvinceCode = stateProvinceCode }

        fun postalCode(postalCode: kotlin.String?) = apply { this.postalCode = postalCode }

        fun countryCode(countryCode: kotlin.String?) = apply { this.countryCode = countryCode }

        fun build(): Address1 {
            return Address1(
                line1 = line1,
                line2 = line2,
                line3 = line3,
                city = city,
                stateProvinceCode = stateProvinceCode,
                postalCode = postalCode,
                countryCode = countryCode
            )
        }
    }
}
