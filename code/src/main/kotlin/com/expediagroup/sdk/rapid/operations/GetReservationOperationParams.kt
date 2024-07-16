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
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The retrieve call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
 * @property affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
 * @property email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
 * @property include Options for which information to return in the response. The value must be lower case.   * history - Include itinerary history, showing details of the changes made to this itinerary
 */
data class GetReservationOperationParams(
    val customerIp: kotlin.String,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val affiliateReferenceId: kotlin.String,
    val email: kotlin.String,
    val include: kotlin.collections.List<kotlin.String>? = null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var affiliateReferenceId: kotlin.String? = null,
        private var email: kotlin.String? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null
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
        fun test(test: kotlin.String) = apply { this.test = test }

        /**
         * @param affiliateReferenceId The affilliate reference id value. This field supports a maximum of 28 characters.
         */
        fun affiliateReferenceId(affiliateReferenceId: kotlin.String) = apply { this.affiliateReferenceId = affiliateReferenceId }

        /**
         * @param email Email associated with the booking. Special characters in the local part or domain should be encoded.<br>
         */
        fun email(email: kotlin.String) = apply { this.email = email }

        /**
         * @param include Options for which information to return in the response. The value must be lower case.   * history - Include itinerary history, showing details of the changes made to this itinerary
         */
        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun build(): GetReservationOperationParams {
            validateNullity()

            return GetReservationOperationParams(
                customerIp = customerIp!!,
                customerSessionId = customerSessionId,
                test = test,
                affiliateReferenceId = affiliateReferenceId!!,
                email = email!!,
                include = include
            )
        }

        private fun validateNullity() {
            if (customerIp == null) {
                throw NullPointerException("Required parameter customerIp is missing")
            }
            if (affiliateReferenceId == null) {
                throw NullPointerException("Required parameter affiliateReferenceId is missing")
            }
            if (email == null) {
                throw NullPointerException("Required parameter email is missing")
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
            affiliateReferenceId?.also { put("affiliate_reference_id", listOf(affiliateReferenceId.toString())) }
            email?.also { put("email", listOf(email.toString())) }
            include?.also { put("include", include) }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
        }
    }
}
