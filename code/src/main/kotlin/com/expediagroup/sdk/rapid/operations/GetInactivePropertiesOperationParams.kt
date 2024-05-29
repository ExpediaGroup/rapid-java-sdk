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

data class GetInactivePropertiesOperationParams(
    val customerSessionId: kotlin.String? = null,
    val since: kotlin.String? = null,
    val token: kotlin.String? = null,
    val billingTerms: kotlin.String? = null,
    val paymentTerms: kotlin.String? = null,
    val partnerPointOfSale: kotlin.String? = null,
    val platformName: kotlin.String? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var customerSessionId: kotlin.String? = null,
        private var since: kotlin.String? = null,
        private var token: kotlin.String? = null,
        private var billingTerms: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var platformName: kotlin.String? = null
    ) {
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun since(since: kotlin.String) = apply { this.since = since }

        fun token(token: kotlin.String) = apply { this.token = token }

        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun build(): GetInactivePropertiesOperationParams {
            return GetInactivePropertiesOperationParams(
                customerSessionId = customerSessionId,
                since = since,
                token = token,
                billingTerms = billingTerms,
                paymentTerms = paymentTerms,
                partnerPointOfSale = partnerPointOfSale,
                platformName = platformName
            )
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerSessionId?.also { put("Customer-Session-Id", customerSessionId) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            since?.also { put("since", listOf(since.toString())) }
            token?.also { put("token", listOf(token.toString())) }
            billingTerms?.also { put("billing_terms", listOf(billingTerms.toString())) }
            paymentTerms?.also { put("payment_terms", listOf(paymentTerms.toString())) }
            partnerPointOfSale?.also { put("partner_point_of_sale", listOf(partnerPointOfSale.toString())) }
            platformName?.also { put("platform_name", listOf(platformName.toString())) }
        }
    }
}
