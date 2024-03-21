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
 * The entire phone number must be represented across the three fields in this object.
 * @param countryCode The numerical portion of the country code from the phone number. Do not include the leading '+' character.
 * @param areaCode The area code of the phone number.
 * @param number The remaining digits of the phone number.
 */
data class Phone(
    // The numerical portion of the country code from the phone number. Do not include the leading '+' character.
    @JsonProperty("country_code")
    @field:Valid
    val countryCode: kotlin.String? = null,
    // The area code of the phone number.
    @JsonProperty("area_code")
    @field:Valid
    val areaCode: kotlin.String? = null,
    // The remaining digits of the phone number.
    @JsonProperty("number")
    @field:Valid
    val number: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var countryCode: kotlin.String? = null,
        private var areaCode: kotlin.String? = null,
        private var number: kotlin.String? = null
    ) {
        fun countryCode(countryCode: kotlin.String?) = apply { this.countryCode = countryCode }

        fun areaCode(areaCode: kotlin.String?) = apply { this.areaCode = areaCode }

        fun number(number: kotlin.String?) = apply { this.number = number }

        fun build(): Phone {
            return Phone(
                countryCode = countryCode,
                areaCode = areaCode,
                number = number
            )
        }
    }
}
