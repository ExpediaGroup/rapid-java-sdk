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
import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.ktor.http.Headers
import io.ktor.http.Parameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property propertyId Expedia Property ID.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property filter Single filter value. Send multiple instances of this parameter to request multiple filters. * `language` - Filters reviews to only those that match the specified `language` parameter value. Without   this filter, the matching language will be preferred, but other language results can be returned.   Specifying this filter could produce an error when there are no matching results.
 * @property tripReason Desired reason provided for the reviewer's trip that you wish to display. This parameter can be supplied multiple times with different values, which will include reviews that match any of the requested trip reasons.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetPropertyGuestReviewsOperationParams.Builder::class)
data class GetPropertyGuestReviewsOperationParams(
    @field:NotNull
    @field:Valid
    val propertyId: kotlin.String,
    @field:Valid
    val customerSessionId: kotlin.String? =
        null,
    @field:NotNull
    @field:Valid
    val language: kotlin.String,
    val filter: kotlin.collections.List<
        GetPropertyGuestReviewsOperationParams.Filter
    >? =
        null,
    val tripReason: kotlin.collections.List<
        GetPropertyGuestReviewsOperationParams.TripReason
    >? =
        null,
    @field:Valid
    val billingTerms: kotlin.String? =
        null,
    @field:Valid
    val partnerPointOfSale: kotlin.String? =
        null,
    @field:Valid
    val paymentTerms: kotlin.String? =
        null,
    @field:Valid
    val platformName: kotlin.String? =
        null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    enum class Filter(
        val value: kotlin.String
    ) {
        LANGUAGE("language")
    }

    enum class TripReason(
        val value: kotlin.String
    ) {
        BUSINESS("business"),
        LEISURE("leisure"),
        FRIENDS_AND_FAMILY("friends_and_family"),
        BUSINESS_AND_LEISURE("business_and_leisure")
    }

    class Builder(
        @JsonProperty("property_id") private var propertyId: kotlin.String? = null,
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("language") private var language: kotlin.String? = null,
        @JsonProperty("filter") private var filter: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.Filter
        >? = null,
        @JsonProperty("trip_reason") private var tripReason: kotlin.collections.List<
            GetPropertyGuestReviewsOperationParams.TripReason
        >? = null,
        @JsonProperty("billing_terms") private var billingTerms: kotlin.String? = null,
        @JsonProperty("partner_point_of_sale") private var partnerPointOfSale: kotlin.String? = null,
        @JsonProperty("payment_terms") private var paymentTerms: kotlin.String? = null,
        @JsonProperty("platform_name") private var platformName: kotlin.String? = null
    ) {
        /**
         * @param propertyId Expedia Property ID.
         */
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/) * [Language Options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
         */
        fun language(language: kotlin.String) = apply { this.language = language }

        /**
         * @param filter Single filter value. Send multiple instances of this parameter to request multiple filters. * `language` - Filters reviews to only those that match the specified `language` parameter value. Without   this filter, the matching language will be preferred, but other language results can be returned.   Specifying this filter could produce an error when there are no matching results.
         */
        fun filter(
            filter: kotlin.collections.List<
                GetPropertyGuestReviewsOperationParams.Filter
            >
        ) = apply { this.filter = filter }

        /**
         * @param tripReason Desired reason provided for the reviewer's trip that you wish to display. This parameter can be supplied multiple times with different values, which will include reviews that match any of the requested trip reasons.
         */
        fun tripReason(
            tripReason: kotlin.collections.List<
                GetPropertyGuestReviewsOperationParams.TripReason
            >
        ) = apply { this.tripReason = tripReason }

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

        fun build(): GetPropertyGuestReviewsOperationParams {
            val params =
                GetPropertyGuestReviewsOperationParams(
                    propertyId = propertyId!!,
                    customerSessionId = customerSessionId,
                    language = language!!,
                    filter = filter,
                    tripReason = tripReason,
                    billingTerms = billingTerms,
                    partnerPointOfSale = partnerPointOfSale,
                    paymentTerms = paymentTerms,
                    platformName = platformName
                )

            validate(params)

            return params
        }

        private fun validate(params: GetPropertyGuestReviewsOperationParams) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(params)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            propertyId = propertyId,
            customerSessionId = customerSessionId,
            language = language,
            filter = filter,
            tripReason = tripReason,
            billingTerms = billingTerms,
            partnerPointOfSale = partnerPointOfSale,
            paymentTerms = paymentTerms,
            platformName = platformName
        )

    override fun getHeaders(): Headers =
        Headers.build {
            customerSessionId?.let {
                append("Customer-Session-Id", it)
            }
            append("Accept", "application/json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
            language?.let {
                append("language", it)
            }
            filter?.let {
                appendAll("filter", it.map { it.value })
            }
            tripReason?.let {
                appendAll("trip_reason", it.map { it.value })
            }
            billingTerms?.let {
                append("billing_terms", it)
            }
            partnerPointOfSale?.let {
                append("partner_point_of_sale", it)
            }
            paymentTerms?.let {
                append("payment_terms", it)
            }
            platformName?.let {
                append("platform_name", it)
            }
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
            propertyId?.also {
                put("property_id", propertyId)
            }
        }
}
