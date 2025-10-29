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
 * @property sortBy Sorts the returned promotions by the given rank value
 * @property blockoutDates Specify restrictions to blockout dates.  If this parameter is not supplied, promotions with and without blockout dates will be returned in the results. * `true` - Include only promotions that have blockout dates. * `false` - Include only promotions that do not have blockout dates.
 * @property bookableEnd Search for promotions with a `bookable_end` date equal to or before the specified date.
 * @property bookableStart Search for promotions with a `bookable_start` date equal to or after the specified date.
 * @property campaignId Search for promotions with matching `campaign_id` values. You can provide 0 to 250 `campaign_id` parameters with different values, which will include promotions that match any of the requested values.
 * @property categoryId Search for promotions associated with the specified `category_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
 * @property chainId Search for promotions associated with the specified `chain_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
 * @property lifetime Search for promotions that match the lifetime type.
 * @property promotionId Search for promotions with matching `id` values. You can provide 0 to 250 `id` parameters with different values, which will include promotions that match any of the requested values.
 * @property include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. All field names found at the top level of the response are valid values for inclusion.
 * @property marketRegion Search for promotions associated with the specified `market_region` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
 * @property membersOnly Specify restrictions to `members_only` values.  If this parameter is not supplied, promotions with true or false will be returned in the results. * `true` - Only results with `members_only` equal to true will be returned. * `false` - Only results with `members_only` equal to false will be returned.
 * @property minAdvancePurchaseDays Search for promotions that have a `min_advance_purchase_days` equal to the passed in value.
 * @property minGuestRating Search for promotions that have a `min_guest_rating` equal to or larger than the value.
 * @property minStarRating Search for promotions that have a `min_star_rating` equal to or larger than the value.
 * @property minStay Search for promotions that have a `min_stay` equal to the passed in value.
 * @property mobileOnly Specify restrictions to `mobile_only` values.  If this parameter is not supplied, promotions with true or false will be returned in the results. * `true` - Only results with `mobile_only` equal to true will be returned. * `false` - Only results with `mobile_only` equal to false will be returned.
 * @property productLine Search for promotions with a `product_line` that matches the value. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
 * @property promotionCriteria Search for promotions that meet the criteria provided. This criteria is defined as:   * type,value For now the only type available will be `percentage` but other types will be added in the future.
 * @property propertyId Search for promotions with matching `property_id` values. You can provide 0 to 250 `property_id` parameters with different values, which will include promotions that match any of the requested values.
 * @property regionId Search for promotions associated with the specified `region_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
 * @property stayEnd Search for all promotions with a `stay_end` date equal to or before the specified date.
 * @property stayStart Search for all promotions with a `stay_start` date equal to or after the specified date.
 * @property supplySource Search for promotions associated with the specified `supply_source` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
 * @property token Only used for requesting additional pages of data. Provided by the `next` URL in the `Link` response header.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetPromotionsOperationParams.Builder::class)
data class GetPromotionsOperationParams(
    @field:Valid
    val customerSessionId: kotlin.String? =
        null,
    @field:NotNull
    @field:Valid
    val language: kotlin.String,
    @field:NotNull
    val sortBy: GetPromotionsOperationParams.SortBy,
    @field:Valid
    val blockoutDates: kotlin.Boolean? =
        null,
    @field:Valid
    val bookableEnd: kotlin.String? =
        null,
    @field:Valid
    val bookableStart: kotlin.String? =
        null,
    @field:Valid
    val campaignId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val categoryId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val chainId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val lifetime: kotlin.String? =
        null,
    @field:Valid
    val promotionId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val include: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    val marketRegion: kotlin.collections.List<
        GetPromotionsOperationParams.MarketRegion
    >? =
        null,
    @field:Valid
    val membersOnly: kotlin.Boolean? =
        null,
    @field:Valid
    val minAdvancePurchaseDays: kotlin.String? =
        null,
    @field:Valid
    val minGuestRating: kotlin.String? =
        null,
    @field:Valid
    val minStarRating: kotlin.String? =
        null,
    @field:Valid
    val minStay: kotlin.String? =
        null,
    @field:Valid
    val mobileOnly: kotlin.Boolean? =
        null,
    @field:Valid
    val productLine: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val promotionCriteria: kotlin.String? =
        null,
    @field:Valid
    val propertyId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val regionId: kotlin.collections.List<
        kotlin.String
    >? =
        null,
    @field:Valid
    val stayEnd: kotlin.String? =
        null,
    @field:Valid
    val stayStart: kotlin.String? =
        null,
    @field:Valid
    val supplySource: kotlin.collections.List<
        kotlin.String
    >? =
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

    enum class SortBy(
        val value: kotlin.String
    ) {
        PROMOTION("promotion"),
        PROPERTY("property"),
        DESTINATION("destination")
    }

    enum class MarketRegion(
        val value: kotlin.String
    ) {
        AMER("amer"),
        APAC("apac"),
        EMEA("emea"),
        LATAM("latam")
    }

    class Builder(
        @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
        @JsonProperty("language") private var language: kotlin.String? = null,
        @JsonProperty("sort_by") private var sortBy: GetPromotionsOperationParams.SortBy? = null,
        @JsonProperty("blockout_dates") private var blockoutDates: kotlin.Boolean? = null,
        @JsonProperty("bookable_end") private var bookableEnd: kotlin.String? = null,
        @JsonProperty("bookable_start") private var bookableStart: kotlin.String? = null,
        @JsonProperty("campaign_id") private var campaignId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("category_id") private var categoryId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("chain_id") private var chainId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("lifetime") private var lifetime: kotlin.String? = null,
        @JsonProperty("promotion_id") private var promotionId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("include") private var include: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("market_region") private var marketRegion: kotlin.collections.List<
            GetPromotionsOperationParams.MarketRegion
        >? = null,
        @JsonProperty("members_only") private var membersOnly: kotlin.Boolean? = null,
        @JsonProperty("min_advance_purchase_days") private var minAdvancePurchaseDays: kotlin.String? = null,
        @JsonProperty("min_guest_rating") private var minGuestRating: kotlin.String? = null,
        @JsonProperty("min_star_rating") private var minStarRating: kotlin.String? = null,
        @JsonProperty("min_stay") private var minStay: kotlin.String? = null,
        @JsonProperty("mobile_only") private var mobileOnly: kotlin.Boolean? = null,
        @JsonProperty("product_line") private var productLine: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("promotion_criteria") private var promotionCriteria: kotlin.String? = null,
        @JsonProperty("property_id") private var propertyId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("region_id") private var regionId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @JsonProperty("stay_end") private var stayEnd: kotlin.String? = null,
        @JsonProperty("stay_start") private var stayStart: kotlin.String? = null,
        @JsonProperty("supply_source") private var supplySource: kotlin.collections.List<
            kotlin.String
        >? = null,
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
         * @param sortBy Sorts the returned promotions by the given rank value
         */
        fun sortBy(sortBy: GetPromotionsOperationParams.SortBy) = apply { this.sortBy = sortBy }

        /**
         * @param blockoutDates Specify restrictions to blockout dates.  If this parameter is not supplied, promotions with and without blockout dates will be returned in the results. * `true` - Include only promotions that have blockout dates. * `false` - Include only promotions that do not have blockout dates.
         */
        fun blockoutDates(blockoutDates: kotlin.Boolean) = apply { this.blockoutDates = blockoutDates }

        /**
         * @param bookableEnd Search for promotions with a `bookable_end` date equal to or before the specified date.
         */
        fun bookableEnd(bookableEnd: kotlin.String) = apply { this.bookableEnd = bookableEnd }

        /**
         * @param bookableStart Search for promotions with a `bookable_start` date equal to or after the specified date.
         */
        fun bookableStart(bookableStart: kotlin.String) = apply { this.bookableStart = bookableStart }

        /**
         * @param campaignId Search for promotions with matching `campaign_id` values. You can provide 0 to 250 `campaign_id` parameters with different values, which will include promotions that match any of the requested values.
         */
        fun campaignId(
            campaignId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.campaignId = campaignId }

        /**
         * @param categoryId Search for promotions associated with the specified `category_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
         */
        fun categoryId(
            categoryId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.categoryId = categoryId }

        /**
         * @param chainId Search for promotions associated with the specified `chain_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
         */
        fun chainId(
            chainId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.chainId = chainId }

        /**
         * @param lifetime Search for promotions that match the lifetime type.
         */
        fun lifetime(lifetime: kotlin.String) = apply { this.lifetime = lifetime }

        /**
         * @param promotionId Search for promotions with matching `id` values. You can provide 0 to 250 `id` parameters with different values, which will include promotions that match any of the requested values.
         */
        fun promotionId(
            promotionId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.promotionId = promotionId }

        /**
         * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. All field names found at the top level of the response are valid values for inclusion.
         */
        fun include(
            include: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.include = include }

        /**
         * @param marketRegion Search for promotions associated with the specified `market_region` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
         */
        fun marketRegion(
            marketRegion: kotlin.collections.List<
                GetPromotionsOperationParams.MarketRegion
            >
        ) = apply { this.marketRegion = marketRegion }

        /**
         * @param membersOnly Specify restrictions to `members_only` values.  If this parameter is not supplied, promotions with true or false will be returned in the results. * `true` - Only results with `members_only` equal to true will be returned. * `false` - Only results with `members_only` equal to false will be returned.
         */
        fun membersOnly(membersOnly: kotlin.Boolean) = apply { this.membersOnly = membersOnly }

        /**
         * @param minAdvancePurchaseDays Search for promotions that have a `min_advance_purchase_days` equal to the passed in value.
         */
        fun minAdvancePurchaseDays(minAdvancePurchaseDays: kotlin.String) = apply { this.minAdvancePurchaseDays = minAdvancePurchaseDays }

        /**
         * @param minGuestRating Search for promotions that have a `min_guest_rating` equal to or larger than the value.
         */
        fun minGuestRating(minGuestRating: kotlin.String) = apply { this.minGuestRating = minGuestRating }

        /**
         * @param minStarRating Search for promotions that have a `min_star_rating` equal to or larger than the value.
         */
        fun minStarRating(minStarRating: kotlin.String) = apply { this.minStarRating = minStarRating }

        /**
         * @param minStay Search for promotions that have a `min_stay` equal to the passed in value.
         */
        fun minStay(minStay: kotlin.String) = apply { this.minStay = minStay }

        /**
         * @param mobileOnly Specify restrictions to `mobile_only` values.  If this parameter is not supplied, promotions with true or false will be returned in the results. * `true` - Only results with `mobile_only` equal to true will be returned. * `false` - Only results with `mobile_only` equal to false will be returned.
         */
        fun mobileOnly(mobileOnly: kotlin.Boolean) = apply { this.mobileOnly = mobileOnly }

        /**
         * @param productLine Search for promotions with a `product_line` that matches the value. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
         */
        fun productLine(
            productLine: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.productLine = productLine }

        /**
         * @param promotionCriteria Search for promotions that meet the criteria provided. This criteria is defined as:   * type,value For now the only type available will be `percentage` but other types will be added in the future.
         */
        fun promotionCriteria(promotionCriteria: kotlin.String) = apply { this.promotionCriteria = promotionCriteria }

        /**
         * @param propertyId Search for promotions with matching `property_id` values. You can provide 0 to 250 `property_id` parameters with different values, which will include promotions that match any of the requested values.
         */
        fun propertyId(
            propertyId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.propertyId = propertyId }

        /**
         * @param regionId Search for promotions associated with the specified `region_id` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.
         */
        fun regionId(
            regionId: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.regionId = regionId }

        /**
         * @param stayEnd Search for all promotions with a `stay_end` date equal to or before the specified date.
         */
        fun stayEnd(stayEnd: kotlin.String) = apply { this.stayEnd = stayEnd }

        /**
         * @param stayStart Search for all promotions with a `stay_start` date equal to or after the specified date.
         */
        fun stayStart(stayStart: kotlin.String) = apply { this.stayStart = stayStart }

        /**
         * @param supplySource Search for promotions associated with the specified `supply_source` values. This parameter can be supplied multiple times with different values, which will include promotions that match any of the requested scenarios.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
         */
        fun supplySource(
            supplySource: kotlin.collections.List<
                kotlin.String
            >
        ) = apply { this.supplySource = supplySource }

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

        fun build(): GetPromotionsOperationParams {
            val params =
                GetPromotionsOperationParams(
                    customerSessionId = customerSessionId,
                    language = language!!,
                    sortBy = sortBy!!,
                    blockoutDates = blockoutDates,
                    bookableEnd = bookableEnd,
                    bookableStart = bookableStart,
                    campaignId = campaignId,
                    categoryId = categoryId,
                    chainId = chainId,
                    lifetime = lifetime,
                    promotionId = promotionId,
                    include = include,
                    marketRegion = marketRegion,
                    membersOnly = membersOnly,
                    minAdvancePurchaseDays = minAdvancePurchaseDays,
                    minGuestRating = minGuestRating,
                    minStarRating = minStarRating,
                    minStay = minStay,
                    mobileOnly = mobileOnly,
                    productLine = productLine,
                    promotionCriteria = promotionCriteria,
                    propertyId = propertyId,
                    regionId = regionId,
                    stayEnd = stayEnd,
                    stayStart = stayStart,
                    supplySource = supplySource,
                    token = token,
                    billingTerms = billingTerms,
                    partnerPointOfSale = partnerPointOfSale,
                    paymentTerms = paymentTerms,
                    platformName = platformName
                )

            validate(params)

            return params
        }

        private fun validate(params: GetPromotionsOperationParams) {
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
            sortBy = sortBy,
            blockoutDates = blockoutDates,
            bookableEnd = bookableEnd,
            bookableStart = bookableStart,
            campaignId = campaignId,
            categoryId = categoryId,
            chainId = chainId,
            lifetime = lifetime,
            promotionId = promotionId,
            include = include,
            marketRegion = marketRegion,
            membersOnly = membersOnly,
            minAdvancePurchaseDays = minAdvancePurchaseDays,
            minGuestRating = minGuestRating,
            minStarRating = minStarRating,
            minStay = minStay,
            mobileOnly = mobileOnly,
            productLine = productLine,
            promotionCriteria = promotionCriteria,
            propertyId = propertyId,
            regionId = regionId,
            stayEnd = stayEnd,
            stayStart = stayStart,
            supplySource = supplySource,
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
            sortBy?.let {
                append("sort_by", it.value)
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
            campaignId?.let {
                appendAll("campaign_id", it)
            }
            categoryId?.let {
                appendAll("category_id", it)
            }
            chainId?.let {
                appendAll("chain_id", it)
            }
            lifetime?.let {
                append("lifetime", it)
            }
            promotionId?.let {
                appendAll("promotion_id", it)
            }
            include?.let {
                appendAll("include", it)
            }
            marketRegion?.let {
                appendAll("market_region", it.map { it.value })
            }
            membersOnly?.let {
                append("members_only", it.toString())
            }
            minAdvancePurchaseDays?.let {
                append("min_advance_purchase_days", it)
            }
            minGuestRating?.let {
                append("min_guest_rating", it)
            }
            minStarRating?.let {
                append("min_star_rating", it)
            }
            minStay?.let {
                append("min_stay", it)
            }
            mobileOnly?.let {
                append("mobile_only", it.toString())
            }
            productLine?.let {
                appendAll("product_line", it)
            }
            promotionCriteria?.let {
                append("promotion_criteria", it)
            }
            propertyId?.let {
                appendAll("property_id", it)
            }
            regionId?.let {
                appendAll("region_id", it)
            }
            stayEnd?.let {
                append("stay_end", it)
            }
            stayStart?.let {
                append("stay_start", it)
            }
            supplySource?.let {
                appendAll("supply_source", it)
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
