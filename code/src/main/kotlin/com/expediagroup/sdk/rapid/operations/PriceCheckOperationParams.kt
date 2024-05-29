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
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        fun roomId(roomId: kotlin.String) = apply { this.roomId = roomId }

        fun rateId(rateId: kotlin.String) = apply { this.rateId = rateId }

        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun test(test: kotlin.String) = apply { this.test = test }

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
