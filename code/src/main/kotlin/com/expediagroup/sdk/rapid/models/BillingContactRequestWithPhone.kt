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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport",
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.rapid.models.BillingContactRequestAddress
import com.expediagroup.sdk.rapid.models.PhoneRequest
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * @param givenName First/given name of the payment type account holder. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param familyName Last/family name of the payment type account holder. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param address
 * @param phone
 */
data class BillingContactRequestWithPhone(
    // First/given name of the payment type account holder. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("given_name")
    @field:Valid
    val givenName: kotlin.String,
    // Last/family name of the payment type account holder. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("family_name")
    @field:Valid
    val familyName: kotlin.String,
    @JsonProperty("address")
    @field:Valid
    val address: BillingContactRequestAddress,
    @JsonProperty("phone")
    @field:Valid
    val phone: PhoneRequest? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var givenName: kotlin.String? = null,
        private var familyName: kotlin.String? = null,
        private var address: BillingContactRequestAddress? = null,
        private var phone: PhoneRequest? = null,
    ) {
        fun givenName(givenName: kotlin.String) = apply { this.givenName = givenName }

        fun familyName(familyName: kotlin.String) = apply { this.familyName = familyName }

        fun address(address: BillingContactRequestAddress) = apply { this.address = address }

        fun phone(phone: PhoneRequest?) = apply { this.phone = phone }

        fun build(): BillingContactRequestWithPhone {
            // Check required params
            validateNullity()
            return BillingContactRequestWithPhone(
                givenName = givenName!!,
                familyName = familyName!!,
                address = address!!,
                phone = phone,
            )
        }

        private fun validateNullity() {
            if (givenName == null) {
                throw NullPointerException("Required parameter givenName is missing")
            }
            if (familyName == null) {
                throw NullPointerException("Required parameter familyName is missing")
            }
            if (address == null) {
                throw NullPointerException("Required parameter address is missing")
            }
        }
    }

    fun toBuilder() =
        Builder(
            givenName = givenName!!,
            familyName = familyName!!,
            address = address!!,
            phone = phone,
        )
}
