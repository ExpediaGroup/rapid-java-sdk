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
    "UnusedImport"
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.rapid.models.Loyalty
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param givenName First name of room guest. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param familyName Last name of room guest. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param smoking Specify if the guest would prefer a smoking room. This field is only a request and the property is not guaranteed to honor it, it will not override any non-smoking policies by the hotel.
 * @param specialRequest Special requests to send to hotel (not guaranteed). Do not use this field to communicate B2B customer service requests or pass any sensitive personal or financial information (PII). Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param loyaltyId Deprecated. Please use the loyalty id inside the loyalty object.
 * @param loyalty
 */
data class CreateItineraryRequestRoom(
    // First name of room guest. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("given_name")
    @field:NotNull
    @field:Valid
    val givenName: kotlin.String,
    // Last name of room guest. Max 60 characters. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("family_name")
    @field:NotNull
    @field:Valid
    val familyName: kotlin.String,
    // Specify if the guest would prefer a smoking room. This field is only a request and the property is not guaranteed to honor it, it will not override any non-smoking policies by the hotel.
    @JsonProperty("smoking")
    @field:Valid
    val smoking: kotlin.Boolean? = null,
    // Special requests to send to hotel (not guaranteed). Do not use this field to communicate B2B customer service requests or pass any sensitive personal or financial information (PII). Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("special_request")
    @field:Valid
    val specialRequest: kotlin.String? = null,
    // Deprecated. Please use the loyalty id inside the loyalty object.
    @Deprecated(message = "This property is deprecated.")
    @JsonProperty("loyalty_id")
    @field:Valid
    val loyaltyId: kotlin.String? = null,
    @JsonProperty("loyalty")
    @field:Valid
    val loyalty: Loyalty? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var givenName: kotlin.String? = null,
        private var familyName: kotlin.String? = null,
        private var smoking: kotlin.Boolean? = null,
        private var specialRequest: kotlin.String? = null,
        private var loyaltyId: kotlin.String? = null,
        private var loyalty: Loyalty? = null
    ) {
        fun givenName(givenName: kotlin.String) = apply { this.givenName = givenName }

        fun familyName(familyName: kotlin.String) = apply { this.familyName = familyName }

        fun smoking(smoking: kotlin.Boolean?) = apply { this.smoking = smoking }

        fun specialRequest(specialRequest: kotlin.String?) = apply { this.specialRequest = specialRequest }

        fun loyaltyId(loyaltyId: kotlin.String?) = apply { this.loyaltyId = loyaltyId }

        fun loyalty(loyalty: Loyalty?) = apply { this.loyalty = loyalty }

        fun build(): CreateItineraryRequestRoom {
            val instance =
                CreateItineraryRequestRoom(
                    givenName = givenName!!,
                    familyName = familyName!!,
                    smoking = smoking,
                    specialRequest = specialRequest,
                    loyaltyId = loyaltyId,
                    loyalty = loyalty
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: CreateItineraryRequestRoom) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            givenName = givenName!!,
            familyName = familyName!!,
            smoking = smoking,
            specialRequest = specialRequest,
            loyaltyId = loyaltyId,
            loyalty = loyalty
        )
}
