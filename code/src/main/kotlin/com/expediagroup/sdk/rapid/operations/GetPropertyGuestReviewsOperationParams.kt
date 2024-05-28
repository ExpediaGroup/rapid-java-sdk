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

data class GetPropertyGuestReviewsOperationParams(
    val propertyId: kotlin.String,
    val customerSessionId: kotlin.String? = null,
    val language: kotlin.String,
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
        private var propertyId: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var language: kotlin.String? = null,
        private var billingTerms: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var platformName: kotlin.String? = null
    ) {
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun language(language: kotlin.String) = apply { this.language = language }

        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun build(): GetPropertyGuestReviewsOperationParams {
            validateNullity()

            return GetPropertyGuestReviewsOperationParams(
                propertyId = propertyId!!,
                customerSessionId = customerSessionId,
                language = language!!,
                billingTerms = billingTerms,
                paymentTerms = paymentTerms,
                partnerPointOfSale = partnerPointOfSale,
                platformName = platformName
            )
        }

        private fun validateNullity() {
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
            }
            if (language == null) {
                throw NullPointerException("Required parameter language is missing")
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
            billingTerms?.also { put("billing_terms", listOf(billingTerms.toString())) }
            paymentTerms?.also { put("payment_terms", listOf(paymentTerms.toString())) }
            partnerPointOfSale?.also { put("partner_point_of_sale", listOf(partnerPointOfSale.toString())) }
            platformName?.also { put("platform_name", listOf(platformName.toString())) }
        }
    }
}
