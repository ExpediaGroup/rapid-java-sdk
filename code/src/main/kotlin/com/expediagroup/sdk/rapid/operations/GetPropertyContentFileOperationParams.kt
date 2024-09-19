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
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetPropertyContentFileOperationParams.Builder::class)
data class GetPropertyContentFileOperationParams(
    val customerSessionId: kotlin.String? =
        null,
    val language: kotlin.String,
    val supplySource: kotlin.String,
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
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("language") private var language: kotlin.String? = null,
        @JsonProperty("supply_source") private var supplySource: kotlin.String? = null,
        @JsonProperty("billing_terms") private var billingTerms: kotlin.String? = null,
        @JsonProperty("partner_point_of_sale") private var partnerPointOfSale: kotlin.String? = null,
        @JsonProperty("payment_terms") private var paymentTerms: kotlin.String? = null,
        @JsonProperty("platform_name") private var platformName: kotlin.String? = null
    ) {
        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
         */
        fun language(language: kotlin.String) = apply { this.language = language }

        /**
         * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
         */
        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

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

        fun build(): GetPropertyContentFileOperationParams {
            validateNullity()

            return GetPropertyContentFileOperationParams(
                customerSessionId = customerSessionId,
                language = language!!,
                supplySource = supplySource!!,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )
        }

        private fun validateNullity() {
            if (language == null) {
                throw NullPointerException("Required parameter language is missing")
            }
            if (supplySource == null) {
                throw NullPointerException("Required parameter supplySource is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerSessionId?.also {
                put("Customer-Session-Id", customerSessionId)
            }
            put("Accept", "application/json")
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            language?.also {
                put(
                    "language",
                    listOf(language)
                )
            }
            supplySource?.also {
                put(
                    "supply_source",
                    listOf(supplySource)
                )
            }
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
