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
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 */
@JsonDeserialize(builder = PostPaymentSessionsOperationParams.Builder::class)
data class PostPaymentSessionsOperationParams
    internal constructor(
        val customerIp: kotlin.String? = null,
        val customerSessionId: kotlin.String? = null,
        val test: PostPaymentSessionsOperationParams.Test? = null,
        val token: kotlin.String? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            customerIp: kotlin.String,
            customerSessionId: kotlin.String? =
                null,
            test: PostPaymentSessionsOperationParams.Test? =
                null,
            token: kotlin.String
        ) : this(
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            dummy = Unit
        )

        constructor(context: PostPaymentSessionsOperationContext?) : this(
            customerIp = context?.customerIp,
            customerSessionId = context?.customerSessionId,
            test = context?.test,
            dummy = Unit
        )

        enum class Test(
            val value: kotlin.String
        ) {
            STANDARD("standard"),
            SERVICE_UNAVAILABLE("service_unavailable"),
            INTERNAL_SERVER_ERROR("internal_server_error")
        }

        class Builder(
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: PostPaymentSessionsOperationParams.Test? = null,
            @JsonProperty("token") private var token: kotlin.String? = null
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
             * @param test The book call has a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `internal_server_error`
             */
            fun test(test: PostPaymentSessionsOperationParams.Test) = apply { this.test = test }

            /**
             * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
             */
            fun token(token: kotlin.String) = apply { this.token = token }

            fun build(): PostPaymentSessionsOperationParams {
                validateNullity()

                return PostPaymentSessionsOperationParams(
                    customerIp = customerIp!!,
                    customerSessionId = customerSessionId,
                    test = test,
                    token = token!!
                )
            }

            private fun validateNullity() {
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
            }
        }

        override fun getPathParams(): Map<String, String> {
            return buildMap {
            }
        }
    }
