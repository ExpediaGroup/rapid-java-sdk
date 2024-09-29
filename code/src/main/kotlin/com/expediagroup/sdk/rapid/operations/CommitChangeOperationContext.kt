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

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
 */
@JsonDeserialize(builder = CommitChangeOperationParams.Builder::class)
data class CommitChangeOperationContext(
    val customerIp: kotlin.String,
    val customerSessionId: kotlin.String? =
        null,
    val test: CommitChangeOperationParams.Test? =
        null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("Test") private var test: CommitChangeOperationParams.Test? = null
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
         * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
         */
        fun test(test: CommitChangeOperationParams.Test) = apply { this.test = test }

        fun build(): CommitChangeOperationContext {
            validateNullity()

            return CommitChangeOperationContext(
                customerIp = customerIp!!,
                customerSessionId = customerSessionId,
                test = test
            )
        }

        private fun validateNullity() {
            if (customerIp == null) {
                throw NullPointerException("Required parameter customerIp is missing")
            }
        }
    }

    fun getHeaders(): Map<String, String> {
        return buildMap {
            customerIp.also {
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
}
