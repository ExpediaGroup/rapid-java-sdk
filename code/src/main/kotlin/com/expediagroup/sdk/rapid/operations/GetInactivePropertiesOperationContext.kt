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

/**
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 */
data class GetInactivePropertiesOperationContext(
    val customerSessionId: kotlin.String? =
        null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var customerSessionId: kotlin.String? = null
    ) {
        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun build(): GetInactivePropertiesOperationContext {
            validateNullity()

            return GetInactivePropertiesOperationContext(
                customerSessionId = customerSessionId
            )
        }

        private fun validateNullity() {
        }
    }

    fun getHeaders(): Map<String, String> {
        return buildMap {
            customerSessionId?.also {
                put("Customer-Session-Id", customerSessionId)
            }
        }
    }
}
