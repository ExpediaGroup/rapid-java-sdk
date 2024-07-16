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

/**
 * @property itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 * @property email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>
 * @property include Options for which information to return in the response. The value must be lower case.   * history - Include itinerary history, showing details of the changes made to this itinerary
 */
data class GetReservationByItineraryIdOperationParams(
    val itineraryId: kotlin.String,
    val customerIp: kotlin.String,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val token: kotlin.String? = null,
    val email: kotlin.String? = null,
    val include: kotlin.collections.List<kotlin.String>? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var itineraryId: kotlin.String? = null,
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var token: kotlin.String? = null,
        private var email: kotlin.String? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null
    ) {
        /**
         * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
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
         * @param test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
         */
        fun test(test: kotlin.String) = apply { this.test = test }

        /**
         * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
         */
        fun token(token: kotlin.String) = apply { this.token = token }

        /**
         * @param email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>
         */
        fun email(email: kotlin.String) = apply { this.email = email }

        /**
         * @param include Options for which information to return in the response. The value must be lower case.   * history - Include itinerary history, showing details of the changes made to this itinerary
         */
        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun build(): GetReservationByItineraryIdOperationParams {
            validateNullity()

            return GetReservationByItineraryIdOperationParams(
                itineraryId = itineraryId!!,
                customerIp = customerIp!!,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
                email = email,
                include = include
            )
        }

        private fun validateNullity() {
            if (itineraryId == null) {
                throw NullPointerException("Required parameter itineraryId is missing")
            }
            if (customerIp == null) {
                throw NullPointerException("Required parameter customerIp is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerIp?.also { put("Customer-Ip", customerIp) }
            customerSessionId?.also { put("Customer-Session-Id", customerSessionId) }
            test?.also { put("Test", test) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            token?.also { put("token", listOf(token.toString())) }
            email?.also { put("email", listOf(email.toString())) }
            include?.also { put("include", include) }
        }
    }
}
