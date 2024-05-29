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

data class GetRegionOperationParams(
    val regionId: kotlin.String,
    val customerSessionId: kotlin.String? = null,
    val language: kotlin.String,
    val include: kotlin.collections.List<kotlin.String>,
    val billingTerms: kotlin.String? = null,
    val partnerPointOfSale: kotlin.String? = null,
    val paymentTerms: kotlin.String? = null,
    val platformName: kotlin.String? = null,
    val supplySource: kotlin.String? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var regionId: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var language: kotlin.String? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null,
        private var billingTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var platformName: kotlin.String? = null,
        private var supplySource: kotlin.String? = null
    ) {
        fun regionId(regionId: kotlin.String) = apply { this.regionId = regionId }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun language(language: kotlin.String) = apply { this.language = language }

        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

        fun build(): GetRegionOperationParams {
            validateNullity()

            return GetRegionOperationParams(
                regionId = regionId!!,
                customerSessionId = customerSessionId,
                language = language!!,
                include = include!!,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName,
                supplySource = supplySource
            )
        }

        private fun validateNullity() {
            if (regionId == null) {
                throw NullPointerException("Required parameter regionId is missing")
            }
            if (language == null) {
                throw NullPointerException("Required parameter language is missing")
            }
            if (include == null) {
                throw NullPointerException("Required parameter include is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerSessionId?.also { put("Customer-Session-Id", customerSessionId) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            language?.also { put("language", listOf(language.toString())) }
            include?.also { put("include", include) }
            billingTerms?.also { put("billing_terms", listOf(billingTerms.toString())) }
            partnerPointOfSale?.also { put("partner_point_of_sale", listOf(partnerPointOfSale.toString())) }
            paymentTerms?.also { put("payment_terms", listOf(paymentTerms.toString())) }
            platformName?.also { put("platform_name", listOf(platformName.toString())) }
            supplySource?.also { put("supply_source", listOf(supplySource.toString())) }
        }
    }
}
