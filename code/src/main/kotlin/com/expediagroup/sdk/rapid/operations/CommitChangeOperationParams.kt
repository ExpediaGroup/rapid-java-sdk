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
 * @property roomId Room ID of a property.<br>
 * @property customerIp IP address of the customer, as captured by your integration. Send IPV4 addresses only.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 */
data class CommitChangeOperationParams
    internal constructor(
        val itineraryId: kotlin.String? =
            null,
        val roomId: kotlin.String? =
            null,
        val customerIp: kotlin.String? =
            null,
        val customerSessionId: kotlin.String? =
            null,
        val test: kotlin.String? =
            null,
        val token: kotlin.String? =
            null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            itineraryId: kotlin.String,
            roomId: kotlin.String,
            customerIp: kotlin.String,
            customerSessionId: kotlin.String? = null,
            test: kotlin.String? = null,
            token: kotlin.String
        ) : this(
            itineraryId = itineraryId,
            roomId = roomId,
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            dummy = Unit
        )

        constructor(context: CommitChangeOperationContext?) : this(
            customerIp = context?.customerIp,
            customerSessionId = context?.customerSessionId,
            test = context?.test,
            dummy = Unit
        )

        class Builder(
            private var itineraryId: kotlin.String? = null,
            private var roomId: kotlin.String? = null,
            private var customerIp: kotlin.String? = null,
            private var customerSessionId: kotlin.String? = null,
            private var test: kotlin.String? = null,
            private var token: kotlin.String? = null
        ) {
            /**
             * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
             */
            fun itineraryId(itineraryId: kotlin.String) = apply { this.itineraryId = itineraryId }

            /**
             * @param roomId Room ID of a property.<br>
             */
            fun roomId(roomId: kotlin.String) = apply { this.roomId = roomId }

            /**
             * @param customerIp IP address of the customer, as captured by your integration. Send IPV4 addresses only.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
             */
            fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

            /**
             * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
             */
            fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

            /**
             * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
             */
            fun test(test: kotlin.String) = apply { this.test = test }

            /**
             * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
             */
            fun token(token: kotlin.String) = apply { this.token = token }

            fun build(): CommitChangeOperationParams {
                validateNullity()

                return CommitChangeOperationParams(
                    itineraryId = itineraryId!!,
                    roomId = roomId!!,
                    customerIp = customerIp!!,
                    customerSessionId = customerSessionId,
                    test = test,
                    token = token!!
                )
            }

            private fun validateNullity() {
                if (itineraryId == null) {
                    throw NullPointerException("Required parameter itineraryId is missing")
                }
                if (roomId == null) {
                    throw NullPointerException("Required parameter roomId is missing")
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

        override fun getPathParams(): Map<String, String> {
            return buildMap {
                itineraryId?.also { put("itinerary_id", itineraryId) }
                roomId?.also { put("room_id", roomId) }
            }
        }
    }
