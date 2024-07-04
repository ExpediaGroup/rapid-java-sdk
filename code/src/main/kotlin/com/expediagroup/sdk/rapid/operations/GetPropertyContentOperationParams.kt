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
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/) <br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
 * @property allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.
 * @property amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.
 * @property attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.
 * @property brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.
 * @property businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.
 * @property categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.
 * @property categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.
 * @property chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.
 * @property countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.
 * @property dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
 * @property include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.
 * @property multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.
 * @property propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
 * @property propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.
 * @property propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.
 * @property spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
data class GetPropertyContentOperationParams(
    val customerSessionId: kotlin.String? = null,
    val language: kotlin.String,
    val supplySource: kotlin.String,
    val allInclusive: kotlin.collections.List<kotlin.String>? = null,
    val amenityId: kotlin.collections.List<kotlin.String>? = null,
    val attributeId: kotlin.collections.List<kotlin.String>? = null,
    val brandId: kotlin.collections.List<kotlin.String>? = null,
    val businessModel: kotlin.collections.List<kotlin.String>? = null,
    val categoryId: kotlin.collections.List<kotlin.String>? = null,
    val categoryIdExclude: kotlin.collections.List<kotlin.String>? = null,
    val chainId: kotlin.collections.List<kotlin.String>? = null,
    val countryCode: kotlin.collections.List<kotlin.String>? = null,
    val dateAddedEnd: kotlin.String? = null,
    val dateAddedStart: kotlin.String? = null,
    val dateUpdatedEnd: kotlin.String? = null,
    val dateUpdatedStart: kotlin.String? = null,
    val include: kotlin.collections.List<kotlin.String>? = null,
    val multiUnit: kotlin.Boolean? = null,
    val propertyId: kotlin.collections.List<kotlin.String>? = null,
    val propertyRatingMax: kotlin.String? = null,
    val propertyRatingMin: kotlin.String? = null,
    val spokenLanguageId: kotlin.collections.List<kotlin.String>? = null,
    val billingTerms: kotlin.String? = null,
    val partnerPointOfSale: kotlin.String? = null,
    val paymentTerms: kotlin.String? = null,
    val platformName: kotlin.String? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var customerSessionId: kotlin.String? = null,
        private var language: kotlin.String? = null,
        private var supplySource: kotlin.String? = null,
        private var allInclusive: kotlin.collections.List<kotlin.String>? = null,
        private var amenityId: kotlin.collections.List<kotlin.String>? = null,
        private var attributeId: kotlin.collections.List<kotlin.String>? = null,
        private var brandId: kotlin.collections.List<kotlin.String>? = null,
        private var businessModel: kotlin.collections.List<kotlin.String>? = null,
        private var categoryId: kotlin.collections.List<kotlin.String>? = null,
        private var categoryIdExclude: kotlin.collections.List<kotlin.String>? = null,
        private var chainId: kotlin.collections.List<kotlin.String>? = null,
        private var countryCode: kotlin.collections.List<kotlin.String>? = null,
        private var dateAddedEnd: kotlin.String? = null,
        private var dateAddedStart: kotlin.String? = null,
        private var dateUpdatedEnd: kotlin.String? = null,
        private var dateUpdatedStart: kotlin.String? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null,
        private var multiUnit: kotlin.Boolean? = null,
        private var propertyId: kotlin.collections.List<kotlin.String>? = null,
        private var propertyRatingMax: kotlin.String? = null,
        private var propertyRatingMin: kotlin.String? = null,
        private var spokenLanguageId: kotlin.collections.List<kotlin.String>? = null,
        private var billingTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var platformName: kotlin.String? = null
    ) {
        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/) <br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
         */
        fun language(language: kotlin.String) = apply { this.language = language }

        /**
         * @param supplySource Options for which supply source you would like returned in the content response. This parameter may only be supplied once and will return all properties that match the requested supply source. An error is thrown if the parameter is provided multiple times.   * `expedia` - Standard Expedia supply.   * `vrbo` - VRBO supply - This option is restricted to partners who have VRBO supply enabled for their profile. See [Vacation Rentals](https://developers.expediagroup.com/docs/rapid/lodging/vacation-rentals) for more information.
         */
        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

        /**
         * @param allInclusive Search to include properties that have the requested `all_inclusive` values equal to true. If this parameter is not supplied, all `all_inclusive` scenarios are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested scenarios.   * `all_rate_plans` - Return properties where `all_inclusive.all_rate_plans` is true.   * `some_rate_plans` = Return properties where `all_inclusive.some_rate_plans` is true.
         */
        fun allInclusive(allInclusive: kotlin.collections.List<kotlin.String>) = apply { this.allInclusive = allInclusive }

        /**
         * @param amenityId The ID of the amenity you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested amenity IDs. This is currently only capable of searching for property level amenities. Room and rate level amenities cannot be searched on.
         */
        fun amenityId(amenityId: kotlin.collections.List<kotlin.String>) = apply { this.amenityId = amenityId }

        /**
         * @param attributeId The ID of the attribute you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested attribute IDs.
         */
        fun attributeId(attributeId: kotlin.collections.List<kotlin.String>) = apply { this.attributeId = attributeId }

        /**
         * @param brandId The ID of the brand you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested brand IDs.
         */
        fun brandId(brandId: kotlin.collections.List<kotlin.String>) = apply { this.brandId = brandId }

        /**
         * @param businessModel Search for properties with the requested business model enabled. This parameter can be supplied multiple times with different values, which will return all properties that match any of the requested business models. The value must be lower case.   * `expedia_collect` - Return only properties where the payment is collected by Expedia.   * `property_collect` - Return only properties where the payment is collected at the property.
         */
        fun businessModel(businessModel: kotlin.collections.List<kotlin.String>) = apply { this.businessModel = businessModel }

        /**
         * @param categoryId Search to include properties that have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested category IDs.
         */
        fun categoryId(categoryId: kotlin.collections.List<kotlin.String>) = apply { this.categoryId = categoryId }

        /**
         * @param categoryIdExclude Search to exclude properties that do not have the requested [category ID](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists). If this parameter is not supplied, all category IDs are included. This parameter can be supplied multiple times with different values, which will exclude properties that match any of the requested category IDs.
         */
        fun categoryIdExclude(categoryIdExclude: kotlin.collections.List<kotlin.String>) = apply { this.categoryIdExclude = categoryIdExclude }

        /**
         * @param chainId The ID of the chain you want to search for. These chain IDs can be positive and negative numbers. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested chain IDs.
         */
        fun chainId(chainId: kotlin.collections.List<kotlin.String>) = apply { this.chainId = chainId }

        /**
         * @param countryCode Search for properties with the requested country code, in ISO 3166-1 alpha-2 format. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested country codes.
         */
        fun countryCode(countryCode: kotlin.collections.List<kotlin.String>) = apply { this.countryCode = countryCode }

        /**
         * @param dateAddedEnd Search for properties added on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
         */
        fun dateAddedEnd(dateAddedEnd: kotlin.String) = apply { this.dateAddedEnd = dateAddedEnd }

        /**
         * @param dateAddedStart Search for properties added on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
         */
        fun dateAddedStart(dateAddedStart: kotlin.String) = apply { this.dateAddedStart = dateAddedStart }

        /**
         * @param dateUpdatedEnd Search for properties updated on or before the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
         */
        fun dateUpdatedEnd(dateUpdatedEnd: kotlin.String) = apply { this.dateUpdatedEnd = dateUpdatedEnd }

        /**
         * @param dateUpdatedStart Search for properties updated on or after the requested UTC date, in ISO 8601 format (YYYY-MM-DD)
         */
        fun dateUpdatedStart(dateUpdatedStart: kotlin.String) = apply { this.dateUpdatedStart = dateUpdatedStart }

        /**
         * @param include Each time this parameter is specified will add to the list of fields and associated objects returned in the response. All values and field names are lower case. The values `property_ids` and `catalog` will continue to behave as specified below for backwards compatibility. All other top level field names will add the specified field to the list of fields returned in the response. See the response schema for a full list of top level field names. Additionally, the field `property_id` will always be returned regardless of what include values are passed.<br><br> Possible values:  * `property_ids` - ***DEPRECATED*** - Please use `property_id` which matches the response field name.  * `catalog` - Include all property catalog fields. See     [Property Catalog File endpoint](https://developers.expediagroup.com/docs/rapid/resources/rapid-api#get-/files/properties/catalog) for a list of fields.  * `property_id` - Passing in the value `property_id` and no other values will limit the response to only      `property_id`. Not necessary to include in combination with other field name values, as it will always      be returned.  * All field names found at the top level of the property content response are now valid values for     inclusion.
         */
        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        /**
         * @param multiUnit Search for multi-unit properties. If this parameter is not supplied, both single-unit and multi-unit properties will be included.   * `true` - Include only properties that are multi-unit.   * `false` - Do not include properties that are multi-unit.
         */
        fun multiUnit(multiUnit: kotlin.Boolean) = apply { this.multiUnit = multiUnit }

        /**
         * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
         */
        fun propertyId(propertyId: kotlin.collections.List<kotlin.String>) = apply { this.propertyId = propertyId }

        /**
         * @param propertyRatingMax Search for properties with a property rating less than or equal to the requested rating. The highest property rating value is 5.0.
         */
        fun propertyRatingMax(propertyRatingMax: kotlin.String) = apply { this.propertyRatingMax = propertyRatingMax }

        /**
         * @param propertyRatingMin Search for properties with a property rating greater than or equal to the requested rating. The lowest property rating value is 0.0.
         */
        fun propertyRatingMin(propertyRatingMin: kotlin.String) = apply { this.propertyRatingMin = propertyRatingMin }

        /**
         * @param spokenLanguageId The id of the spoken language you want to search for. This parameter can be supplied multiple times with different values, which will include properties that match any of the requested spoken languages. The language code as a subset of BCP47 format.
         */
        fun spokenLanguageId(spokenLanguageId: kotlin.collections.List<kotlin.String>) = apply { this.spokenLanguageId = spokenLanguageId }

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

        fun build(): GetPropertyContentOperationParams {
            validateNullity()

            return GetPropertyContentOperationParams(
                customerSessionId = customerSessionId,
                language = language!!,
                supplySource = supplySource!!,
                allInclusive = allInclusive,
                amenityId = amenityId,
                attributeId = attributeId,
                brandId = brandId,
                businessModel = businessModel,
                categoryId = categoryId,
                categoryIdExclude = categoryIdExclude,
                chainId = chainId,
                countryCode = countryCode,
                dateAddedEnd = dateAddedEnd,
                dateAddedStart = dateAddedStart,
                dateUpdatedEnd = dateUpdatedEnd,
                dateUpdatedStart = dateUpdatedStart,
                include = include,
                multiUnit = multiUnit,
                propertyId = propertyId,
                propertyRatingMax = propertyRatingMax,
                propertyRatingMin = propertyRatingMin,
                spokenLanguageId = spokenLanguageId,
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
            customerSessionId?.also { put("Customer-Session-Id", customerSessionId) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            language?.also { put("language", listOf(language.toString())) }
            supplySource?.also { put("supply_source", listOf(supplySource.toString())) }
            allInclusive?.also { put("all_inclusive", allInclusive) }
            amenityId?.also { put("amenity_id", amenityId) }
            attributeId?.also { put("attribute_id", attributeId) }
            brandId?.also { put("brand_id", brandId) }
            businessModel?.also { put("business_model", businessModel) }
            categoryId?.also { put("category_id", categoryId) }
            categoryIdExclude?.also { put("category_id_exclude", categoryIdExclude) }
            chainId?.also { put("chain_id", chainId) }
            countryCode?.also { put("country_code", countryCode) }
            dateAddedEnd?.also { put("date_added_end", listOf(dateAddedEnd.toString())) }
            dateAddedStart?.also { put("date_added_start", listOf(dateAddedStart.toString())) }
            dateUpdatedEnd?.also { put("date_updated_end", listOf(dateUpdatedEnd.toString())) }
            dateUpdatedStart?.also { put("date_updated_start", listOf(dateUpdatedStart.toString())) }
            include?.also { put("include", include) }
            multiUnit?.also { put("multi_unit", listOf(multiUnit.toString())) }
            propertyId?.also { put("property_id", propertyId) }
            propertyRatingMax?.also { put("property_rating_max", listOf(propertyRatingMax.toString())) }
            propertyRatingMin?.also { put("property_rating_min", listOf(propertyRatingMin.toString())) }
            spokenLanguageId?.also { put("spoken_language_id", spokenLanguageId) }
            billingTerms?.also { put("billing_terms", listOf(billingTerms.toString())) }
            partnerPointOfSale?.also { put("partner_point_of_sale", listOf(partnerPointOfSale.toString())) }
            paymentTerms?.also { put("payment_terms", listOf(paymentTerms.toString())) }
            platformName?.also { put("platform_name", listOf(platformName.toString())) }
        }
    }
}
