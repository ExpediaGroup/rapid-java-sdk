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

data class GetReservationOperationParams(
    val customerIp: kotlin.String,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val affiliateReferenceId: kotlin.String,
    val email: kotlin.String,
    val include: kotlin.collections.List<kotlin.String>? = null
) : OperationParams {
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
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun test(test: kotlin.String) = apply { this.test = test }

        fun affiliateReferenceId(affiliateReferenceId: kotlin.String) = apply { this.affiliateReferenceId = affiliateReferenceId }

        fun email(email: kotlin.String) = apply { this.email = email }

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
}
