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
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property itineraryId This path variable will be provided as part of the link. This specifies which itinerary the booking receipt request pertains to.
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The booking receipt call has a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `internal_server_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 * @property branding This parameter specifies which branding should be present on the generated PDF. Default behavior will be to provide the booking receipt with `expedia_group` branding. Some partner configurations may change the default to unbranded.
 */
@JsonDeserialize(builder = GetBookingReceiptOperationParams.Builder::class)
data class GetBookingReceiptOperationParams(
    val itineraryId: kotlin.String,
    val customerIp: kotlin.String,
    val customerSessionId: kotlin.String? =
        null,
    val test: GetBookingReceiptOperationParams.Test? =
        null,
    val token: kotlin.String,
    val branding: GetBookingReceiptOperationParams.Branding? =
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

    enum class Branding(
        val value: kotlin.String
    ) {
        EXPEDIA_GROUP("expedia_group"),
        UNBRANDED("unbranded")
    }

    class Builder(
        @JsonProperty("itinerary_id") private var itineraryId: kotlin.String? = null,
        @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("Test") private var test: GetBookingReceiptOperationParams.Test? = null,
        @JsonProperty("token") private var token: kotlin.String? = null,
        @JsonProperty("branding") private var branding: GetBookingReceiptOperationParams.Branding? = null
    ) {
        /**
         * @param itineraryId This path variable will be provided as part of the link. This specifies which itinerary the booking receipt request pertains to.
         */
        fun itineraryId(itineraryId: kotlin.String) = apply { this.itineraryId = itineraryId }

        /**
         * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
         */
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param test The booking receipt call has a test header that can be used to return set responses with the following keywords: * `standard` * `service_unavailable` * `internal_server_error`
         */
        fun test(test: GetBookingReceiptOperationParams.Test) = apply { this.test = test }

        /**
         * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
         */
        fun token(token: kotlin.String) = apply { this.token = token }

        /**
         * @param branding This parameter specifies which branding should be present on the generated PDF. Default behavior will be to provide the booking receipt with `expedia_group` branding. Some partner configurations may change the default to unbranded.
         */
        fun branding(branding: GetBookingReceiptOperationParams.Branding) = apply { this.branding = branding }

        fun build(): GetBookingReceiptOperationParams {
            validateNullity()

            return GetBookingReceiptOperationParams(
                itineraryId = itineraryId!!,
                customerIp = customerIp!!,
                customerSessionId = customerSessionId,
                test = test,
                token = token!!,
                branding = branding
            )
        }

        private fun validateNullity() {
            if (itineraryId == null) {
                throw NullPointerException("Required parameter itineraryId is missing")
            }
            if (customerIp == null) {
                throw NullPointerException("Required parameter customerIp is missing")
            }
            if (token == null) {
                throw NullPointerException("Required parameter token is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerIp?.also {
                put("Customer-Ip", customerIp)
            }
            customerSessionId?.also {
                put("Customer-Session-Id", customerSessionId)
            }
            test?.also {
                put("Test", test.value)
            }
            put("Accept", "application/pdf")
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            token?.also {
                put(
                    "token",
                    listOf(token)
                )
            }
            branding?.also {
                put(
                    "branding",
                    listOf(branding.value)
                )
            }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
            itineraryId?.also {
                put("itinerary_id", itineraryId)
            }
        }
    }
}
