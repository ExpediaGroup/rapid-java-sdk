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
 * Container for the property's address information.
 * @param line1 Address line 1.
 * @param line2 Address line 2.
 * @param city City.
 * @param stateProvinceCode 2-letter or 3-letter state/province code for Australia, Canada and the USA.
 * @param stateProvinceName Text name of the State/Province - more common for additional countries.
 * @param postalCode Postal/zip code.
 * @param countryCode 2-letter country code, in ISO 3166-1 alpha-2 format.
 * @param obfuscationRequired Flag to indicate whether a property address requires obfuscation before the property has been booked. If true, only the city, province, and country of the address can be shown to the customer.
 * @param localized
 */
data class Address(
    // Address line 1.
    @JsonProperty("line_1")
    @field:Valid
    val line1: kotlin.String? = null,
    // Address line 2.
    @JsonProperty("line_2")
    @field:Valid
    val line2: kotlin.String? = null,
    // City.
    @JsonProperty("city")
    @field:Valid
    val city: kotlin.String? = null,
    // 2-letter or 3-letter state/province code for Australia, Canada and the USA.
    @JsonProperty("state_province_code")
    @field:Valid
    val stateProvinceCode: kotlin.String? = null,
    // Text name of the State/Province - more common for additional countries.
    @JsonProperty("state_province_name")
    @field:Valid
    val stateProvinceName: kotlin.String? = null,
    // Postal/zip code.
    @JsonProperty("postal_code")
    @field:Valid
    val postalCode: kotlin.String? = null,
    // 2-letter country code, in ISO 3166-1 alpha-2 format.
    @JsonProperty("country_code")
    @field:Valid
    val countryCode: kotlin.String? = null,
    // Flag to indicate whether a property address requires obfuscation before the property has been booked. If true, only the city, province, and country of the address can be shown to the customer.
    @JsonProperty("obfuscation_required")
    @field:Valid
    val obfuscationRequired: kotlin.Boolean? = null,
    @JsonProperty("localized")
    @field:Valid
    val localized: Localized? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var line1: kotlin.String? = null,
        private var line2: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var stateProvinceCode: kotlin.String? = null,
        private var stateProvinceName: kotlin.String? = null,
        private var postalCode: kotlin.String? = null,
        private var countryCode: kotlin.String? = null,
        private var obfuscationRequired: kotlin.Boolean? = null,
        private var localized: Localized? = null
    ) {
        fun line1(line1: kotlin.String?) = apply { this.line1 = line1 }

        fun line2(line2: kotlin.String?) = apply { this.line2 = line2 }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun stateProvinceCode(stateProvinceCode: kotlin.String?) = apply { this.stateProvinceCode = stateProvinceCode }

        fun stateProvinceName(stateProvinceName: kotlin.String?) = apply { this.stateProvinceName = stateProvinceName }

        fun postalCode(postalCode: kotlin.String?) = apply { this.postalCode = postalCode }

        fun countryCode(countryCode: kotlin.String?) = apply { this.countryCode = countryCode }

        fun obfuscationRequired(obfuscationRequired: kotlin.Boolean?) = apply { this.obfuscationRequired = obfuscationRequired }

        fun localized(localized: Localized?) = apply { this.localized = localized }

        fun build(): Address {
            return Address(
                line1 = line1,
                line2 = line2,
                city = city,
                stateProvinceCode = stateProvinceCode,
                stateProvinceName = stateProvinceName,
                postalCode = postalCode,
                countryCode = countryCode,
                obfuscationRequired = obfuscationRequired,
                localized = localized
            )
        }
    }
}
