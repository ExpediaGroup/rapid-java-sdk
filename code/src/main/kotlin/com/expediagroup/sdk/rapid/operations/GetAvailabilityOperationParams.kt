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
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`
 * @property checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.
 * @property checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.
 * @property currency Requested currency for the rates, in ISO 4217 format<br><br> Currency Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options](https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options)
 * @property countryCode The country code of the traveler's point of sale, in ISO 3166-1 alpha-2 format. This should represent the country where the shopping transaction is taking place.<br> For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
 * @property language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)<br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
 * @property occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
 * @property propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
 * @property ratePlanCount The number of rates to return per property. The rates with the best value will be returned, e.g. a rate_plan_count=4 will return the best 4 rates, but the rates are not ordered from lowest to highest or vice versa in the response. Generally lowest rates will be prioritized.<br><br> The value must be between 1 and 250.
 * @property salesChannel You must provide the sales channel for the display of rates. EPS dynamically provides the best content for optimal conversion on each sales channel. If you have a sales channel that is not currently supported in this list, please contact our support team.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
 * @property salesEnvironment You must provide the sales environment in which rates will be sold. EPS dynamically provides the best content for optimal conversion. If you have a sales environment that is not currently supported in this list, please contact our support team.<br> * `hotel_package` - Use when selling the hotel with a transport product, e.g. flight & hotel. * `hotel_only` - Use when selling the hotel as an individual product. * `loyalty` - Use when you are selling the hotel as part of a loyalty program and the price is converted to points.
 * @property amenityCategory Single amenity category. Send multiple instances of this parameter to request rates that match multiple amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.
 * @property exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.
 * @property filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
 * @property include Modify the response by including types of responses that are not provided by default.<br> * `unavailable_reason` - When a property is unavailable for an actionable reason, return a response with that reason - See [Unavailable Reason Codes](https://developers.expediagroup.com/docs/rapid/resources/reference/unavailable-reason-codes) for possible values. * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `room.rate.sale_scenario` section of the response. * `rooms.rates.marketing_fee_incentives` - When a rate has a marketing fee incentive applied, the response will include the `marketing_fee_incentives` array if this flag is provided in the request. * `rooms.rates.current_refundability` - Displays the current `refundability` of a rate.
 * @property rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
 * @property travelPurpose This parameter is to specify the travel purpose of the booking. This may impact available rate plans, pricing, or tax calculations. * `leisure` * `business`
 * @property billingTerms This parameter is to specify the terms of how a resulting booking should be billed. If this field is needed, the value for this will be provided to you separately.
 * @property partnerPointOfSale This parameter is to specify what point of sale is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 * @property paymentTerms This parameter is to specify what terms should be used when being paid for a resulting booking. If this field is needed, the value for this will be provided to you separately.
 * @property platformName This parameter is to specify what platform is being used to shop and book. If this field is needed, the value for this will be provided to you separately.
 */
@JsonDeserialize(builder = GetAvailabilityOperationParams.Builder::class)
data class GetAvailabilityOperationParams
    internal constructor(
        @field:Valid
        val customerIp: kotlin.String? = null,
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        val test: GetAvailabilityOperationParams.Test? = null,
        @field:NotNull
        @field:Valid
        val checkin: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val checkout: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val currency: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val countryCode: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val language: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val occupancy: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:NotNull
        @field:Valid
        val propertyId: kotlin.collections.List<
            kotlin.String
        >? = null,
        @field:NotNull
        @field:Valid
        val ratePlanCount: java.math.BigDecimal? = null,
        @field:NotNull
        @field:Valid
        val salesChannel: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val salesEnvironment: kotlin.String? = null,
        @field:Valid
        val amenityCategory: kotlin.collections.List<
            kotlin.String
        >? = null,
        val exclusion: kotlin.collections.List<
            GetAvailabilityOperationParams.Exclusion
        >? = null,
        val filter: kotlin.collections.List<
            GetAvailabilityOperationParams.Filter
        >? = null,
        val include: kotlin.collections.List<
            GetAvailabilityOperationParams.Include
        >? = null,
        val rateOption: kotlin.collections.List<
            GetAvailabilityOperationParams.RateOption
        >? = null,
        val travelPurpose: GetAvailabilityOperationParams.TravelPurpose? = null,
        @field:Valid
        val billingTerms: kotlin.String? = null,
        @field:Valid
        val partnerPointOfSale: kotlin.String? = null,
        @field:Valid
        val paymentTerms: kotlin.String? = null,
        @field:Valid
        val platformName: kotlin.String? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            customerIp: kotlin.String? =
                null,
            customerSessionId: kotlin.String? =
                null,
            test: GetAvailabilityOperationParams.Test? =
                null,
            checkin: kotlin.String,
            checkout: kotlin.String,
            currency: kotlin.String,
            countryCode: kotlin.String,
            language: kotlin.String,
            occupancy: kotlin.collections.List<
                kotlin.String
            >,
            propertyId: kotlin.collections.List<
                kotlin.String
            >,
            ratePlanCount: java.math.BigDecimal,
            salesChannel: kotlin.String,
            salesEnvironment: kotlin.String,
            amenityCategory: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            exclusion: kotlin.collections.List<
                GetAvailabilityOperationParams.Exclusion
            >? =
                null,
            filter: kotlin.collections.List<
                GetAvailabilityOperationParams.Filter
            >? =
                null,
            include: kotlin.collections.List<
                GetAvailabilityOperationParams.Include
            >? =
                null,
            rateOption: kotlin.collections.List<
                GetAvailabilityOperationParams.RateOption
            >? =
                null,
            travelPurpose: GetAvailabilityOperationParams.TravelPurpose? =
                null,
            billingTerms: kotlin.String? =
                null,
            partnerPointOfSale: kotlin.String? =
                null,
            paymentTerms: kotlin.String? =
                null,
            platformName: kotlin.String? =
                null
        ) : this(
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            checkin = checkin,
            checkout = checkout,
            currency = currency,
            countryCode = countryCode,
            language = language,
            occupancy = occupancy,
            propertyId = propertyId,
            ratePlanCount = ratePlanCount,
            salesChannel = salesChannel,
            salesEnvironment = salesEnvironment,
            amenityCategory = amenityCategory,
            exclusion = exclusion,
            filter = filter,
            include = include,
            rateOption = rateOption,
            travelPurpose = travelPurpose,
            billingTerms = billingTerms,
            partnerPointOfSale = partnerPointOfSale,
            paymentTerms = paymentTerms,
            platformName = platformName,
            dummy = Unit
        )

        constructor(context: GetAvailabilityOperationContext?) : this(
            customerIp = context?.customerIp,
            customerSessionId = context?.customerSessionId,
            test = context?.test,
            dummy = Unit
        )

        enum class Test(
            val value: kotlin.String
        ) {
            STANDARD("standard"),
            SERVICE_UNAVAILABLE("service_unavailable"),
            UNKNOWN_INTERNAL_ERROR("unknown_internal_error")
        }

        enum class Exclusion(
            val value: kotlin.String
        ) {
            REFUNDABLE_DAMAGE_DEPOSIT("refundable_damage_deposit"),
            CARD_ON_FILE("card_on_file")
        }

        enum class Filter(
            val value: kotlin.String
        ) {
            REFUNDABLE("refundable"),
            EXPEDIA_COLLECT("expedia_collect"),
            PROPERTY_COLLECT("property_collect")
        }

        enum class Include(
            val value: kotlin.String
        ) {
            UNAVAILABLE_REASON("unavailable_reason"),
            SALE_SCENARIO_PERIOD_MOBILE_PROMOTION("sale_scenario.mobile_promotion"),
            ROOMS_PERIOD_RATES_PERIOD_MARKETING_FEE_INCENTIVES("rooms.rates.marketing_fee_incentives"),
            ROOMS_PERIOD_RATES_PERIOD_CURRENT_REFUNDABILITY("rooms.rates.current_refundability")
        }

        enum class RateOption(
            val value: kotlin.String
        ) {
            MEMBER("member"),
            NET_RATES("net_rates"),
            CROSS_SELL("cross_sell")
        }

        enum class TravelPurpose(
            val value: kotlin.String
        ) {
            LEISURE("leisure"),
            BUSINESS("business")
        }

        class Builder(
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: GetAvailabilityOperationParams.Test? = null,
            @JsonProperty("checkin") private var checkin: kotlin.String? = null,
            @JsonProperty("checkout") private var checkout: kotlin.String? = null,
            @JsonProperty("currency") private var currency: kotlin.String? = null,
            @JsonProperty("country_code") private var countryCode: kotlin.String? = null,
            @JsonProperty("language") private var language: kotlin.String? = null,
            @JsonProperty("occupancy") private var occupancy: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("property_id") private var propertyId: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("rate_plan_count") private var ratePlanCount: java.math.BigDecimal? = null,
            @JsonProperty("sales_channel") private var salesChannel: kotlin.String? = null,
            @JsonProperty("sales_environment") private var salesEnvironment: kotlin.String? = null,
            @JsonProperty("amenity_category") private var amenityCategory: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("exclusion") private var exclusion: kotlin.collections.List<
                GetAvailabilityOperationParams.Exclusion
            >? = null,
            @JsonProperty("filter") private var filter: kotlin.collections.List<
                GetAvailabilityOperationParams.Filter
            >? = null,
            @JsonProperty("include") private var include: kotlin.collections.List<
                GetAvailabilityOperationParams.Include
            >? = null,
            @JsonProperty("rate_option") private var rateOption: kotlin.collections.List<
                GetAvailabilityOperationParams.RateOption
            >? = null,
            @JsonProperty("travel_purpose") private var travelPurpose: GetAvailabilityOperationParams.TravelPurpose? = null,
            @JsonProperty("billing_terms") private var billingTerms: kotlin.String? = null,
            @JsonProperty("partner_point_of_sale") private var partnerPointOfSale: kotlin.String? = null,
            @JsonProperty("payment_terms") private var paymentTerms: kotlin.String? = null,
            @JsonProperty("platform_name") private var platformName: kotlin.String? = null
        ) {
            /**
             * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
             */
            fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

            /**
             * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
             */
            fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

            /**
             * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error`
             */
            fun test(test: GetAvailabilityOperationParams.Test) = apply { this.test = test }

            /**
             * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.
             */
            fun checkin(checkin: kotlin.String) = apply { this.checkin = checkin }

            /**
             * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.
             */
            fun checkout(checkout: kotlin.String) = apply { this.checkout = checkout }

            /**
             * @param currency Requested currency for the rates, in ISO 4217 format<br><br> Currency Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options](https://developers.expediagroup.com/docs/rapid/resources/reference/currency-options)
             */
            fun currency(currency: kotlin.String) = apply { this.currency = currency }

            /**
             * @param countryCode The country code of the traveler's point of sale, in ISO 3166-1 alpha-2 format. This should represent the country where the shopping transaction is taking place.<br> For more information see: [https://www.iso.org/obp/ui/#search/code/](https://www.iso.org/obp/ui/#search/code/)
             */
            fun countryCode(countryCode: kotlin.String) = apply { this.countryCode = countryCode }

            /**
             * @param language Desired language for the response as a subset of BCP47 format that only uses hyphenated pairs of two-digit language and country codes. Use only ISO 639-1 alpha-2 language codes and ISO 3166-1 alpha-2 country codes. See [https://www.w3.org/International/articles/language-tags/](https://www.w3.org/International/articles/language-tags/)<br> Language Options: [https://developers.expediagroup.com/docs/rapid/resources/reference/language-options](https://developers.expediagroup.com/docs/rapid/resources/reference/language-options)
             */
            fun language(language: kotlin.String) = apply { this.language = language }

            /**
             * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
             */
            fun occupancy(
                occupancy: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.occupancy = occupancy }

            /**
             * @param propertyId The ID of the property you want to search for. You can provide 1 to 250 property_id parameters.
             */
            fun propertyId(
                propertyId: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.propertyId = propertyId }

            /**
             * @param ratePlanCount The number of rates to return per property. The rates with the best value will be returned, e.g. a rate_plan_count=4 will return the best 4 rates, but the rates are not ordered from lowest to highest or vice versa in the response. Generally lowest rates will be prioritized.<br><br> The value must be between 1 and 250.
             */
            fun ratePlanCount(ratePlanCount: java.math.BigDecimal) = apply { this.ratePlanCount = ratePlanCount }

            /**
             * @param salesChannel You must provide the sales channel for the display of rates. EPS dynamically provides the best content for optimal conversion on each sales channel. If you have a sales channel that is not currently supported in this list, please contact our support team.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
             */
            fun salesChannel(salesChannel: kotlin.String) = apply { this.salesChannel = salesChannel }

            /**
             * @param salesEnvironment You must provide the sales environment in which rates will be sold. EPS dynamically provides the best content for optimal conversion. If you have a sales environment that is not currently supported in this list, please contact our support team.<br> * `hotel_package` - Use when selling the hotel with a transport product, e.g. flight & hotel. * `hotel_only` - Use when selling the hotel as an individual product. * `loyalty` - Use when you are selling the hotel as part of a loyalty program and the price is converted to points.
             */
            fun salesEnvironment(salesEnvironment: kotlin.String) = apply { this.salesEnvironment = salesEnvironment }

            /**
             * @param amenityCategory Single amenity category. Send multiple instances of this parameter to request rates that match multiple amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.
             */
            fun amenityCategory(
                amenityCategory: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.amenityCategory = amenityCategory }

            /**
             * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.
             */
            fun exclusion(
                exclusion: kotlin.collections.List<
                    GetAvailabilityOperationParams.Exclusion
                >
            ) = apply { this.exclusion = exclusion }

            /**
             * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
             */
            fun filter(
                filter: kotlin.collections.List<
                    GetAvailabilityOperationParams.Filter
                >
            ) = apply { this.filter = filter }

            /**
             * @param include Modify the response by including types of responses that are not provided by default.<br> * `unavailable_reason` - When a property is unavailable for an actionable reason, return a response with that reason - See [Unavailable Reason Codes](https://developers.expediagroup.com/docs/rapid/resources/reference/unavailable-reason-codes) for possible values. * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `room.rate.sale_scenario` section of the response. * `rooms.rates.marketing_fee_incentives` - When a rate has a marketing fee incentive applied, the response will include the `marketing_fee_incentives` array if this flag is provided in the request. * `rooms.rates.current_refundability` - Displays the current `refundability` of a rate.
             */
            fun include(
                include: kotlin.collections.List<
                    GetAvailabilityOperationParams.Include
                >
            ) = apply { this.include = include }

            /**
             * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
             */
            fun rateOption(
                rateOption: kotlin.collections.List<
                    GetAvailabilityOperationParams.RateOption
                >
            ) = apply { this.rateOption = rateOption }

            /**
             * @param travelPurpose This parameter is to specify the travel purpose of the booking. This may impact available rate plans, pricing, or tax calculations. * `leisure` * `business`
             */
            fun travelPurpose(travelPurpose: GetAvailabilityOperationParams.TravelPurpose) = apply { this.travelPurpose = travelPurpose }

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

            fun build(): GetAvailabilityOperationParams {
                val params =
                    GetAvailabilityOperationParams(
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
                        partnerPointOfSale = partnerPointOfSale,
                        paymentTerms = paymentTerms,
                        platformName = platformName
                    )

                validate(params)

                return params
            }

            private fun validate(params: GetAvailabilityOperationParams) {
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
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                checkin = checkin,
                checkout = checkout,
                currency = currency,
                countryCode = countryCode,
                language = language,
                occupancy = occupancy,
                propertyId = propertyId,
                ratePlanCount = ratePlanCount,
                salesChannel = salesChannel,
                salesEnvironment = salesEnvironment,
                amenityCategory = amenityCategory,
                exclusion = exclusion,
                filter = filter,
                include = include,
                rateOption = rateOption,
                travelPurpose = travelPurpose,
                billingTerms = billingTerms,
                partnerPointOfSale = partnerPointOfSale,
                paymentTerms = paymentTerms,
                platformName = platformName
            )

        override fun getHeaders(): Headers =
            Headers.build {
                customerIp?.let {
                    append("Customer-Ip", it)
                }
                customerSessionId?.let {
                    append("Customer-Session-Id", it)
                }
                test?.let {
                    append("Test", it.value)
                }
                append("Accept", "application/json")
            }

        override fun getQueryParams(): Parameters =
            Parameters.build {
                checkin?.let {
                    append("checkin", it)
                }
                checkout?.let {
                    append("checkout", it)
                }
                currency?.let {
                    append("currency", it)
                }
                countryCode?.let {
                    append("country_code", it)
                }
                language?.let {
                    append("language", it)
                }
                occupancy?.let {
                    appendAll("occupancy", it)
                }
                propertyId?.let {
                    appendAll("property_id", it)
                }
                ratePlanCount?.let {
                    append("rate_plan_count", it.toString())
                }
                salesChannel?.let {
                    append("sales_channel", it)
                }
                salesEnvironment?.let {
                    append("sales_environment", it)
                }
                amenityCategory?.let {
                    appendAll("amenity_category", it)
                }
                exclusion?.let {
                    appendAll("exclusion", it.map { it.value })
                }
                filter?.let {
                    appendAll("filter", it.map { it.value })
                }
                include?.let {
                    appendAll("include", it.map { it.value })
                }
                rateOption?.let {
                    appendAll("rate_option", it.map { it.value })
                }
                travelPurpose?.let {
                    append("travel_purpose", it.value)
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
