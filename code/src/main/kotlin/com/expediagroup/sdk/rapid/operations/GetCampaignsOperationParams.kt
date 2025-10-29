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
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br>**Currently only `en-US` is supported.** <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/)
 * @property blockoutDates Specify restrictions to blockout dates.  If this parameter is not supplied, campaigns with and without blockout dates will be included. * `true` - Include only campaigns that have blockout dates. * `false` - Do not include campaigns that have blockout dates.
 * @property bookableEnd Search for all campaigns with a `bookable_end` date equal to or before the specified date.
 * @property bookableStart Search for all campaigns with a `bookable_start` date equal to or after the specified date.
 * @property lifetime Search for campaigns that match the lifetime type.
 * @property campaignId Search for campaigns with matching `id` values. You can provide 0 to 250 `id` parameters with different values, which will include campaigns that match any of the requested values.
 * @property include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. All field names found at the top level of the response are valid values for inclusion.
 * @property minDiscount Search for campaigns that have a `min_discount` equal to or larger than the value.
 * @property stayEnd Search for all campaigns with a `stay_end` date equal to or before the specified date.
 * @property stayStart Search for all campaigns with a `stay_start` date equal to or after the specified date.
 * @property token Only used for requesting additional pages of data. Provided by the `next` URL in the `Link` response header.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetCampaignsOperationParams.Builder::class)
data class GetCampaignsOperationParams(
    @field:Valid
    val customerSessionId: kotlin.String? =
        null,
    @field:NotNull
    @field:Valid
    val language: kotlin.String,
    @field:Valid
    val blockoutDates: kotlin.Boolean? =
        null,
    @field:Valid
    val bookableEnd: kotlin.String? =
        null,
    @field:Valid
    val bookableStart: kotlin.String? =
        null,
    val lifetime: GetCampaignsOperationParams.Lifetime? =
        null,
    @field:Valid
    val campaignId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val include: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val minDiscount: kotlin.String? =
        null,
    @field:Valid
    val stayEnd: kotlin.String? =
        null,
    @field:Valid
    val stayStart: kotlin.String? =
        null,
    @field:Valid
    val token: kotlin.String? =
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

    enum class Lifetime(
        val value: kotlin.String
    ) {
        LIMITED_TIME_OFFER("limited_time_offer"),
        EVERGREEN("evergreen")
    }

    class Builder(
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("language") private var language: kotlin.String? = null,
        @JsonProperty("blockout_dates") private var blockoutDates: kotlin.Boolean? = null,
        @JsonProperty("bookable_end") private var bookableEnd: kotlin.String? = null,
        @JsonProperty("bookable_start") private var bookableStart: kotlin.String? = null,
        @JsonProperty("lifetime") private var lifetime: GetCampaignsOperationParams.Lifetime? = null,
        @JsonProperty("campaign_id") private var campaignId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("include") private var include: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("min_discount") private var minDiscount: kotlin.String? = null,
        @JsonProperty("stay_end") private var stayEnd: kotlin.String? = null,
        @JsonProperty("stay_start") private var stayStart: kotlin.String? = null,
        @JsonProperty("token") private var token: kotlin.String? = null,
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
         * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. <br>**Currently only `en-US` is supported.** <br><br>Reference: * [W3 Language Tags](https://www.w3.org/International/articles/language-tags/)
         */
        fun language(language: kotlin.String) = apply { this.language = language }

        /**
         * @param blockoutDates Specify restrictions to blockout dates.  If this parameter is not supplied, campaigns with and without blockout dates will be included. * `true` - Include only campaigns that have blockout dates. * `false` - Do not include campaigns that have blockout dates.
         */
        fun blockoutDates(blockoutDates: kotlin.Boolean) = apply { this.blockoutDates = blockoutDates }

        /**
         * @param bookableEnd Search for all campaigns with a `bookable_end` date equal to or before the specified date.
         */
        fun bookableEnd(bookableEnd: kotlin.String) = apply { this.bookableEnd = bookableEnd }

        /**
         * @param bookableStart Search for all campaigns with a `bookable_start` date equal to or after the specified date.
         */
        fun bookableStart(bookableStart: kotlin.String) = apply { this.bookableStart = bookableStart }

        /**
         * @param lifetime Search for campaigns that match the lifetime type.
         */
        fun lifetime(lifetime: GetCampaignsOperationParams.Lifetime) = apply { this.lifetime = lifetime }

        /**
         * @param campaignId Search for campaigns with matching `id` values. You can provide 0 to 250 `id` parameters with different values, which will include campaigns that match any of the requested values.
         */
        fun campaignId(
            campaignId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.campaignId = campaignId }

        /**
         * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. All field names found at the top level of the response are valid values for inclusion.
         */
        fun include(
            include: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.include = include }

        /**
         * @param minDiscount Search for campaigns that have a `min_discount` equal to or larger than the value.
         */
        fun minDiscount(minDiscount: kotlin.String) = apply { this.minDiscount = minDiscount }

        /**
         * @param stayEnd Search for all campaigns with a `stay_end` date equal to or before the specified date.
         */
        fun stayEnd(stayEnd: kotlin.String) = apply { this.stayEnd = stayEnd }

        /**
         * @param stayStart Search for all campaigns with a `stay_start` date equal to or after the specified date.
         */
        fun stayStart(stayStart: kotlin.String) = apply { this.stayStart = stayStart }

        /**
         * @param token Only used for requesting additional pages of data. Provided by the `next` URL in the `Link` response header.
         */
        fun token(token: kotlin.String) = apply { this.token = token }

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

        fun build(): GetCampaignsOperationParams {
            val params =
                GetCampaignsOperationParams(
                    customerSessionId = customerSessionId,
                    language = language!!,
                    blockoutDates = blockoutDates,
                    bookableEnd = bookableEnd,
                    bookableStart = bookableStart,
                    lifetime = lifetime,
                    campaignId = campaignId,
                    include = include,
                    minDiscount = minDiscount,
                    stayEnd = stayEnd,
                    stayStart = stayStart,
                    token = token,
                    billingTerms = billingTerms,
                    partnerPointOfSale = partnerPointOfSale,
                    paymentTerms = paymentTerms,
                    platformName = platformName
                )

            validate(params)

            return params
        }

        private fun validate(params: GetCampaignsOperationParams) {
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
            customerSessionId = customerSessionId,
            language = language,
            blockoutDates = blockoutDates,
            bookableEnd = bookableEnd,
            bookableStart = bookableStart,
            lifetime = lifetime,
            campaignId = campaignId,
            include = include,
            minDiscount = minDiscount,
            stayEnd = stayEnd,
            stayStart = stayStart,
            token = token,
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
            blockoutDates?.let {
                append("blockout_dates", it.toString())
            }
            bookableEnd?.let {
                append("bookable_end", it)
            }
            bookableStart?.let {
                append("bookable_start", it)
            }
            lifetime?.let {
                append("lifetime", it.value)
            }
            campaignId?.let {
                appendAll("campaign_id", it)
            }
            include?.let {
                appendAll("include", it)
            }
            minDiscount?.let {
                append("min_discount", it)
            }
            stayEnd?.let {
                append("stay_end", it)
            }
            stayStart?.let {
                append("stay_start", it)
            }
            token?.let {
                append("token", it)
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
        }
}
