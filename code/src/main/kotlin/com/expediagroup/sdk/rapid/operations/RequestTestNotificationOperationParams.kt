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
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
data class RequestTestNotificationOperationParams(
    val billingTerms: kotlin.String? =
        null,
    val partnerPointOfSale: kotlin.String? =
        null,
    val paymentTerms: kotlin.String? =
        null,
    val platformName: kotlin.String? =
        null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var billingTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var platformName: kotlin.String? = null
    ) {
        /**
         * @param billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
         */
        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        /**
         * @param partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
         */
        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        /**
         * @param paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
         */
        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        /**
         * @param platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
         */
        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun build(): RequestTestNotificationOperationParams {
            validateNullity()

            return RequestTestNotificationOperationParams(
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )
        }

        private fun validateNullity() {
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            billingTerms?.also {
                put(
                    "billing_terms",
                    listOf(billingTerms)
                )
            }
            partnerPointOfSale?.also {
                put(
                    "partner_point_of_sale",
                    listOf(partnerPointOfSale)
                )
            }
            paymentTerms?.also {
                put(
                    "payment_terms",
                    listOf(paymentTerms)
                )
            }
            platformName?.also {
                put(
                    "platform_name",
                    listOf(platformName)
                )
            }
        }
    }

    override fun getPathParams(): Map<String, String> {
        return buildMap {
        }
    }
}
