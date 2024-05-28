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
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun language(language: kotlin.String) = apply { this.language = language }

        fun supplySource(supplySource: kotlin.String) = apply { this.supplySource = supplySource }

        fun allInclusive(allInclusive: kotlin.collections.List<kotlin.String>) = apply { this.allInclusive = allInclusive }

        fun amenityId(amenityId: kotlin.collections.List<kotlin.String>) = apply { this.amenityId = amenityId }

        fun attributeId(attributeId: kotlin.collections.List<kotlin.String>) = apply { this.attributeId = attributeId }

        fun brandId(brandId: kotlin.collections.List<kotlin.String>) = apply { this.brandId = brandId }

        fun businessModel(businessModel: kotlin.collections.List<kotlin.String>) = apply { this.businessModel = businessModel }

        fun categoryId(categoryId: kotlin.collections.List<kotlin.String>) = apply { this.categoryId = categoryId }

        fun categoryIdExclude(categoryIdExclude: kotlin.collections.List<kotlin.String>) = apply { this.categoryIdExclude = categoryIdExclude }

        fun chainId(chainId: kotlin.collections.List<kotlin.String>) = apply { this.chainId = chainId }

        fun countryCode(countryCode: kotlin.collections.List<kotlin.String>) = apply { this.countryCode = countryCode }

        fun dateAddedEnd(dateAddedEnd: kotlin.String) = apply { this.dateAddedEnd = dateAddedEnd }

        fun dateAddedStart(dateAddedStart: kotlin.String) = apply { this.dateAddedStart = dateAddedStart }

        fun dateUpdatedEnd(dateUpdatedEnd: kotlin.String) = apply { this.dateUpdatedEnd = dateUpdatedEnd }

        fun dateUpdatedStart(dateUpdatedStart: kotlin.String) = apply { this.dateUpdatedStart = dateUpdatedStart }

        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun multiUnit(multiUnit: kotlin.Boolean) = apply { this.multiUnit = multiUnit }

        fun propertyId(propertyId: kotlin.collections.List<kotlin.String>) = apply { this.propertyId = propertyId }

        fun propertyRatingMax(propertyRatingMax: kotlin.String) = apply { this.propertyRatingMax = propertyRatingMax }

        fun propertyRatingMin(propertyRatingMin: kotlin.String) = apply { this.propertyRatingMin = propertyRatingMin }

        fun spokenLanguageId(spokenLanguageId: kotlin.collections.List<kotlin.String>) = apply { this.spokenLanguageId = spokenLanguageId }

        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

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
