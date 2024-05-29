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
 * @property propertyId Expedia Property ID.<br>
 * @property roomId Room ID of a property.<br>
 * @property rateId Rate ID of a room.<br>
 * @property token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`
 */
data class PriceCheckOperationParams(
    val propertyId: kotlin.String,
    val roomId: kotlin.String,
    val rateId: kotlin.String,
    val customerIp: kotlin.String? = null,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val token: kotlin.String
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var roomId: kotlin.String? = null,
        private var rateId: kotlin.String? = null,
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var token: kotlin.String? = null
    ) {
        /**
         * @param propertyId Expedia Property ID.<br>
         */
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        /**
         * @param roomId Room ID of a property.<br>
         */
        fun roomId(roomId: kotlin.String) = apply { this.roomId = roomId }

        /**
         * @param rateId Rate ID of a room.<br>
         */
        fun rateId(rateId: kotlin.String) = apply { this.rateId = rateId }

        /**
         * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
         */
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`
         */
        fun test(test: kotlin.String) = apply { this.test = test }

        /**
         * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
         */
        fun token(token: kotlin.String) = apply { this.token = token }

        fun build(): PriceCheckOperationParams {
            validateNullity()

            return PriceCheckOperationParams(
                propertyId = propertyId!!,
                roomId = roomId!!,
                rateId = rateId!!,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token!!
            )
        }

        private fun validateNullity() {
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
            }
            if (roomId == null) {
                throw NullPointerException("Required parameter roomId is missing")
            }
            if (rateId == null) {
                throw NullPointerException("Required parameter rateId is missing")
            }
            if (token == null) {
                throw NullPointerException("Required parameter token is missing")
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
        }
    }
}
