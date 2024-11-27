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
package com.expediagroup.sdk.rapid.operations

import com.expediagroup.sdk.core.model.OperationParams
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The retrieve call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
 * @property affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
 * @property email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
 * @property include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.
 */
@JsonDeserialize(builder = GetReservationOperationParams.Builder::class)
data class GetReservationOperationParams(
    @field:NotNull
    @field:Valid
    val customerIp: kotlin.String,
    @field:Valid
    val customerSessionId: kotlin.String? =
        null,
    val test: GetReservationOperationParams.Test? =
        null,
    @field:NotNull
    @field:Valid
    val affiliateReferenceId: kotlin.String,
    @field:NotNull
    @field:Valid
    val email: kotlin.String,
    val include: kotlin.collections.List<
        GetReservationOperationParams.Include
    >? =
        null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Test(
        val value: kotlin.String
    ) {
        STANDARD("standard"),
        SERVICE_UNAVAILABLE("service_unavailable"),
        INTERNAL_SERVER_ERROR("internal_server_error")
    }

    enum class Include(
        val value: kotlin.String
    ) {
        HISTORY("history")
    }

    class Builder(
        @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("Test") private var test: GetReservationOperationParams.Test? = null,
        @JsonProperty("affiliate_reference_id") private var affiliateReferenceId: kotlin.String? = null,
        @JsonProperty("email") private var email: kotlin.String? = null,
        @JsonProperty("include") private var include: kotlin.collections.List<
            GetReservationOperationParams.Include
        >? = null
    ) {
        /**
         * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
         */
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param test The retrieve call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
         */
        fun test(test: GetReservationOperationParams.Test) = apply { this.test = test }

        /**
         * @param affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
         */
        fun affiliateReferenceId(affiliateReferenceId: kotlin.String) = apply { this.affiliateReferenceId = affiliateReferenceId }

        /**
         * @param email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
         */
        fun email(email: kotlin.String) = apply { this.email = email }

        /**
         * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.
         */
        fun include(
            include: kotlin.collections.List<
                GetReservationOperationParams.Include
            >
        ) = apply { this.include = include }

        fun build(): GetReservationOperationParams {
            val params =
                GetReservationOperationParams(
                    customerIp = customerIp!!,
                    customerSessionId = customerSessionId,
                    test = test,
                    affiliateReferenceId = affiliateReferenceId!!,
                    email = email!!,
                    include = include
                )

            validate(params)

            return params
        }

        private fun validate(params: GetReservationOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            affiliateReferenceId = affiliateReferenceId,
            email = email,
            include = include
        )

    override fun getHeaders(): Headers =
        Headers.build {
            customerIp?.let {
                append("Customer-Ip", it)
            }
            customerSessionId?.let {
                append("Customer-Session-Id", it)
            }
            test?.let {
                append("Test", it.value)
            }
            append("Accept", "application/json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            affiliateReferenceId?.let {
                append("affiliate_reference_id", it)
            }
            email?.let {
                append("email", it)
            }
            include?.let {
                appendAll("include", it.map { it.value })
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
