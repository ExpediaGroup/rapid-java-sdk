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

data class GetAdditionalAvailabilityOperationParams(
    val propertyId: kotlin.String,
    val customerIp: kotlin.String? = null,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val token: kotlin.String,
    val checkin: kotlin.String? = null,
    val checkout: kotlin.String? = null,
    val exclusion: kotlin.collections.List<kotlin.String>? = null,
    val filter: kotlin.collections.List<kotlin.String>? = null,
    val include: kotlin.collections.List<kotlin.String>? = null,
    val occupancy: kotlin.collections.List<kotlin.String>? = null,
    val rateOption: kotlin.collections.List<kotlin.String>? = null,
    val salesChannel: kotlin.String? = null,
    val currency: kotlin.String? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var token: kotlin.String? = null,
        private var checkin: kotlin.String? = null,
        private var checkout: kotlin.String? = null,
        private var exclusion: kotlin.collections.List<kotlin.String>? = null,
        private var filter: kotlin.collections.List<kotlin.String>? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null,
        private var occupancy: kotlin.collections.List<kotlin.String>? = null,
        private var rateOption: kotlin.collections.List<kotlin.String>? = null,
        private var salesChannel: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun test(test: kotlin.String) = apply { this.test = test }

        fun token(token: kotlin.String) = apply { this.token = token }

        fun checkin(checkin: kotlin.String) = apply { this.checkin = checkin }

        fun checkout(checkout: kotlin.String) = apply { this.checkout = checkout }

        fun exclusion(exclusion: kotlin.collections.List<kotlin.String>) = apply { this.exclusion = exclusion }

        fun filter(filter: kotlin.collections.List<kotlin.String>) = apply { this.filter = filter }

        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun occupancy(occupancy: kotlin.collections.List<kotlin.String>) = apply { this.occupancy = occupancy }

        fun rateOption(rateOption: kotlin.collections.List<kotlin.String>) = apply { this.rateOption = rateOption }

        fun salesChannel(salesChannel: kotlin.String) = apply { this.salesChannel = salesChannel }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): GetAdditionalAvailabilityOperationParams {
            validateNullity()

            return GetAdditionalAvailabilityOperationParams(
                propertyId = propertyId!!,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token!!,
                checkin = checkin,
                checkout = checkout,
                exclusion = exclusion,
                filter = filter,
                include = include,
                occupancy = occupancy,
                rateOption = rateOption,
                salesChannel = salesChannel,
                currency = currency
            )
        }

        private fun validateNullity() {
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
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
            checkin?.also { put("checkin", listOf(checkin.toString())) }
            checkout?.also { put("checkout", listOf(checkout.toString())) }
            exclusion?.also { put("exclusion", exclusion) }
            filter?.also { put("filter", filter) }
            include?.also { put("include", include) }
            occupancy?.also { put("occupancy", occupancy) }
            rateOption?.also { put("rate_option", rateOption) }
            salesChannel?.also { put("sales_channel", listOf(salesChannel.toString())) }
            currency?.also { put("currency", listOf(currency.toString())) }
        }
    }
}
