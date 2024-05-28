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

data class GetAvailabilityOperationParams(
    val customerIp: kotlin.String? = null,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val checkin: kotlin.String,
    val checkout: kotlin.String,
    val currency: kotlin.String,
    val countryCode: kotlin.String,
    val language: kotlin.String,
    val occupancy: kotlin.collections.List<kotlin.String>,
    val propertyId: kotlin.collections.List<kotlin.String>,
    val ratePlanCount: java.math.BigDecimal,
    val salesChannel: kotlin.String,
    val salesEnvironment: kotlin.String,
    val amenityCategory: kotlin.collections.List<kotlin.String>? = null,
    val exclusion: kotlin.collections.List<kotlin.String>? = null,
    val filter: kotlin.collections.List<kotlin.String>? = null,
    val include: kotlin.collections.List<kotlin.String>? = null,
    val rateOption: kotlin.collections.List<kotlin.String>? = null,
    val travelPurpose: kotlin.String? = null,
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
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var checkin: kotlin.String? = null,
        private var checkout: kotlin.String? = null,
        private var currency: kotlin.String? = null,
        private var countryCode: kotlin.String? = null,
        private var language: kotlin.String? = null,
        private var occupancy: kotlin.collections.List<kotlin.String>? = null,
        private var propertyId: kotlin.collections.List<kotlin.String>? = null,
        private var ratePlanCount: java.math.BigDecimal? = null,
        private var salesChannel: kotlin.String? = null,
        private var salesEnvironment: kotlin.String? = null,
        private var amenityCategory: kotlin.collections.List<kotlin.String>? = null,
        private var exclusion: kotlin.collections.List<kotlin.String>? = null,
        private var filter: kotlin.collections.List<kotlin.String>? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null,
        private var rateOption: kotlin.collections.List<kotlin.String>? = null,
        private var travelPurpose: kotlin.String? = null,
        private var billingTerms: kotlin.String? = null,
        private var paymentTerms: kotlin.String? = null,
        private var partnerPointOfSale: kotlin.String? = null,
        private var platformName: kotlin.String? = null
    ) {
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        fun test(test: kotlin.String) = apply { this.test = test }

        fun checkin(checkin: kotlin.String) = apply { this.checkin = checkin }

        fun checkout(checkout: kotlin.String) = apply { this.checkout = checkout }

        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun countryCode(countryCode: kotlin.String) = apply { this.countryCode = countryCode }

        fun language(language: kotlin.String) = apply { this.language = language }

        fun occupancy(occupancy: kotlin.collections.List<kotlin.String>) = apply { this.occupancy = occupancy }

        fun propertyId(propertyId: kotlin.collections.List<kotlin.String>) = apply { this.propertyId = propertyId }

        fun ratePlanCount(ratePlanCount: java.math.BigDecimal) = apply { this.ratePlanCount = ratePlanCount }

        fun salesChannel(salesChannel: kotlin.String) = apply { this.salesChannel = salesChannel }

        fun salesEnvironment(salesEnvironment: kotlin.String) = apply { this.salesEnvironment = salesEnvironment }

        fun amenityCategory(amenityCategory: kotlin.collections.List<kotlin.String>) = apply { this.amenityCategory = amenityCategory }

        fun exclusion(exclusion: kotlin.collections.List<kotlin.String>) = apply { this.exclusion = exclusion }

        fun filter(filter: kotlin.collections.List<kotlin.String>) = apply { this.filter = filter }

        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        fun rateOption(rateOption: kotlin.collections.List<kotlin.String>) = apply { this.rateOption = rateOption }

        fun travelPurpose(travelPurpose: kotlin.String) = apply { this.travelPurpose = travelPurpose }

        fun billingTerms(billingTerms: kotlin.String) = apply { this.billingTerms = billingTerms }

        fun paymentTerms(paymentTerms: kotlin.String) = apply { this.paymentTerms = paymentTerms }

        fun partnerPointOfSale(partnerPointOfSale: kotlin.String) = apply { this.partnerPointOfSale = partnerPointOfSale }

        fun platformName(platformName: kotlin.String) = apply { this.platformName = platformName }

        fun build(): GetAvailabilityOperationParams {
            validateNullity()

            return GetAvailabilityOperationParams(
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                checkin = checkin!!,
                checkout = checkout!!,
                currency = currency!!,
                countryCode = countryCode!!,
                language = language!!,
                occupancy = occupancy!!,
                propertyId = propertyId!!,
                ratePlanCount = ratePlanCount!!,
                salesChannel = salesChannel!!,
                salesEnvironment = salesEnvironment!!,
                amenityCategory = amenityCategory,
                exclusion = exclusion,
                filter = filter,
                include = include,
                rateOption = rateOption,
                travelPurpose = travelPurpose,
                billingTerms = billingTerms,
                paymentTerms = paymentTerms,
                partnerPointOfSale = partnerPointOfSale,
                platformName = platformName
            )
        }

        private fun validateNullity() {
            if (checkin == null) {
                throw NullPointerException("Required parameter checkin is missing")
            }
            if (checkout == null) {
                throw NullPointerException("Required parameter checkout is missing")
            }
            if (currency == null) {
                throw NullPointerException("Required parameter currency is missing")
            }
            if (countryCode == null) {
                throw NullPointerException("Required parameter countryCode is missing")
            }
            if (language == null) {
                throw NullPointerException("Required parameter language is missing")
            }
            if (occupancy == null) {
                throw NullPointerException("Required parameter occupancy is missing")
            }
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
            }
            if (ratePlanCount == null) {
                throw NullPointerException("Required parameter ratePlanCount is missing")
            }
            if (salesChannel == null) {
                throw NullPointerException("Required parameter salesChannel is missing")
            }
            if (salesEnvironment == null) {
                throw NullPointerException("Required parameter salesEnvironment is missing")
            }
        }
    }

    override fun getHeaders(): Map<String, String> {
        return buildMap {
            customerIp?.also { put("Customer-Ip", customerIp) }
            customerSessionId?.also { put("Customer-Session-Id", customerSessionId) }
            test?.also { put("Test", test) }
        }
    }

    override fun getQueryParams(): Map<String, Iterable<String>> {
        return buildMap {
            checkin?.also { put("checkin", listOf(checkin.toString())) }
            checkout?.also { put("checkout", listOf(checkout.toString())) }
            currency?.also { put("currency", listOf(currency.toString())) }
            countryCode?.also { put("country_code", listOf(countryCode.toString())) }
            language?.also { put("language", listOf(language.toString())) }
            occupancy?.also { put("occupancy", occupancy) }
            propertyId?.also { put("property_id", propertyId) }
            ratePlanCount?.also { put("rate_plan_count", listOf(ratePlanCount.toString())) }
            salesChannel?.also { put("sales_channel", listOf(salesChannel.toString())) }
            salesEnvironment?.also { put("sales_environment", listOf(salesEnvironment.toString())) }
            amenityCategory?.also { put("amenity_category", amenityCategory) }
            exclusion?.also { put("exclusion", exclusion) }
            filter?.also { put("filter", filter) }
            include?.also { put("include", include) }
            rateOption?.also { put("rate_option", rateOption) }
            travelPurpose?.also { put("travel_purpose", listOf(travelPurpose.toString())) }
            billingTerms?.also { put("billing_terms", listOf(billingTerms.toString())) }
            paymentTerms?.also { put("payment_terms", listOf(paymentTerms.toString())) }
            partnerPointOfSale?.also { put("partner_point_of_sale", listOf(partnerPointOfSale.toString())) }
            platformName?.also { put("platform_name", listOf(platformName.toString())) }
        }
    }
}
