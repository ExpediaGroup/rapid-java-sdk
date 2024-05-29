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
 * @param countryCode Customer's country code, in two-letter ISO 3166-1 alpha-2 format. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param line1 First line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param line2 Second line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param line3 Third line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param city Customer's city. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param stateProvinceCode Customer's state or province code. Mandatory for AU, CA and US. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 * @param postalCode Customer's postal code. Mandatory for CA, GB, and US. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
 */
data class BillingContactRequestAddress(
    // Customer's country code, in two-letter ISO 3166-1 alpha-2 format. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("country_code")
    @field:Valid
    val countryCode: kotlin.String,
    // First line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("line_1")
    @field:Valid
    val line1: kotlin.String? = null,
    // Second line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("line_2")
    @field:Valid
    val line2: kotlin.String? = null,
    // Third line of customer's street address. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("line_3")
    @field:Valid
    val line3: kotlin.String? = null,
    // Customer's city. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("city")
    @field:Valid
    val city: kotlin.String? = null,
    // Customer's state or province code. Mandatory for AU, CA and US. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("state_province_code")
    @field:Valid
    val stateProvinceCode: kotlin.String? = null,
    // Customer's postal code. Mandatory for CA, GB, and US. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded. Only ISO-8859-1 compliant characters are allowed.
    @JsonProperty("postal_code")
    @field:Valid
    val postalCode: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var countryCode: kotlin.String? = null,
        private var line1: kotlin.String? = null,
        private var line2: kotlin.String? = null,
        private var line3: kotlin.String? = null,
        private var city: kotlin.String? = null,
        private var stateProvinceCode: kotlin.String? = null,
        private var postalCode: kotlin.String? = null
    ) {
        fun countryCode(countryCode: kotlin.String) = apply { this.countryCode = countryCode }

        fun line1(line1: kotlin.String?) = apply { this.line1 = line1 }

        fun line2(line2: kotlin.String?) = apply { this.line2 = line2 }

        fun line3(line3: kotlin.String?) = apply { this.line3 = line3 }

        fun city(city: kotlin.String?) = apply { this.city = city }

        fun stateProvinceCode(stateProvinceCode: kotlin.String?) = apply { this.stateProvinceCode = stateProvinceCode }

        fun postalCode(postalCode: kotlin.String?) = apply { this.postalCode = postalCode }

        fun build(): BillingContactRequestAddress {
            // Check required params
            validateNullity()
            return BillingContactRequestAddress(
                countryCode = countryCode!!,
                line1 = line1,
                line2 = line2,
                line3 = line3,
                city = city,
                stateProvinceCode = stateProvinceCode,
                postalCode = postalCode
            )
        }

        private fun validateNullity() {
            if (countryCode == null) {
                throw NullPointerException("Required parameter countryCode is missing")
            }
        }
    }
}
