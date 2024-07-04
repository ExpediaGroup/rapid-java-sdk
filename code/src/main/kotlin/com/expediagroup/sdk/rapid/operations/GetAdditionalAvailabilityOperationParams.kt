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
 * @property propertyId Expedia Property ID.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error` * `no_availability` * `forbidden`
 * @property token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the `additional_rates` link from the shop response, or the `shop` link on a `sold_out` price check response. It is also provided from the `shop_for_change` link on an itinerary retrieve.
 * @property checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.<br> Note: Only needed for hard change if desired check-in date is different than original booking. If specified must also specify `checkout`.
 * @property checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.<br> Note: Only needed for hard change if desired check-out date is different than original booking. If specified must also specify `checkin`.<br>
 * @property exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes rates with refundable damage deposits from the response.
 * @property filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
 * @property include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.
 * @property occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
 * @property rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
 * @property salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
 * @property currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases
 */
data class GetAdditionalAvailabilityOperationParams(
    val propertyId: kotlin.String,
    val customerIp: kotlin.String? = null,
    val customerSessionId: kotlin.String? = null,
    val test: kotlin.String? = null,
    val token: kotlin.String,
    val checkin: kotlin.String? = null,
    val checkout: kotlin.String? = null,
    val exclusion: kotlin.collections.List<kotlin.String>? = null,
    val filter: kotlin.collections.List<kotlin.String>? = null,
    val include: kotlin.collections.List<kotlin.String>? = null,
    val occupancy: kotlin.collections.List<kotlin.String>? = null,
    val rateOption: kotlin.collections.List<kotlin.String>? = null,
    val salesChannel: kotlin.String? = null,
    val currency: kotlin.String? = null
) : OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var propertyId: kotlin.String? = null,
        private var customerIp: kotlin.String? = null,
        private var customerSessionId: kotlin.String? = null,
        private var test: kotlin.String? = null,
        private var token: kotlin.String? = null,
        private var checkin: kotlin.String? = null,
        private var checkout: kotlin.String? = null,
        private var exclusion: kotlin.collections.List<kotlin.String>? = null,
        private var filter: kotlin.collections.List<kotlin.String>? = null,
        private var include: kotlin.collections.List<kotlin.String>? = null,
        private var occupancy: kotlin.collections.List<kotlin.String>? = null,
        private var rateOption: kotlin.collections.List<kotlin.String>? = null,
        private var salesChannel: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        /**
         * @param propertyId Expedia Property ID.<br>
         */
        fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

        /**
         * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
         */
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        /**
         * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
         */
        fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

        /**
         * @param test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error` * `no_availability` * `forbidden`
         */
        fun test(test: kotlin.String) = apply { this.test = test }

        /**
         * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the `additional_rates` link from the shop response, or the `shop` link on a `sold_out` price check response. It is also provided from the `shop_for_change` link on an itinerary retrieve.
         */
        fun token(token: kotlin.String) = apply { this.token = token }

        /**
         * @param checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.<br> Note: Only needed for hard change if desired check-in date is different than original booking. If specified must also specify `checkout`.
         */
        fun checkin(checkin: kotlin.String) = apply { this.checkin = checkin }

        /**
         * @param checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.<br> Note: Only needed for hard change if desired check-out date is different than original booking. If specified must also specify `checkin`.<br>
         */
        fun checkout(checkout: kotlin.String) = apply { this.checkout = checkout }

        /**
         * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes rates with refundable damage deposits from the response.
         */
        fun exclusion(exclusion: kotlin.collections.List<kotlin.String>) = apply { this.exclusion = exclusion }

        /**
         * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
         */
        fun filter(filter: kotlin.collections.List<kotlin.String>) = apply { this.filter = filter }

        /**
         * @param include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.
         */
        fun include(include: kotlin.collections.List<kotlin.String>) = apply { this.include = include }

        /**
         * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
         */
        fun occupancy(occupancy: kotlin.collections.List<kotlin.String>) = apply { this.occupancy = occupancy }

        /**
         * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
         */
        fun rateOption(rateOption: kotlin.collections.List<kotlin.String>) = apply { this.rateOption = rateOption }

        /**
         * @param salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
         */
        fun salesChannel(salesChannel: kotlin.String) = apply { this.salesChannel = salesChannel }

        /**
         * @param currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases
         */
        fun currency(currency: kotlin.String) = apply { this.currency = currency }

        fun build(): GetAdditionalAvailabilityOperationParams {
            validateNullity()

            return GetAdditionalAvailabilityOperationParams(
                propertyId = propertyId!!,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token!!,
                checkin = checkin,
                checkout = checkout,
                exclusion = exclusion,
                filter = filter,
                include = include,
                occupancy = occupancy,
                rateOption = rateOption,
                salesChannel = salesChannel,
                currency = currency
            )
        }

        private fun validateNullity() {
            if (propertyId == null) {
                throw NullPointerException("Required parameter propertyId is missing")
            }
            if (token == null) {
                throw NullPointerException("Required parameter token is missing")
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
            token?.also { put("token", listOf(token.toString())) }
            checkin?.also { put("checkin", listOf(checkin.toString())) }
            checkout?.also { put("checkout", listOf(checkout.toString())) }
            exclusion?.also { put("exclusion", exclusion) }
            filter?.also { put("filter", filter) }
            include?.also { put("include", include) }
            occupancy?.also { put("occupancy", occupancy) }
            rateOption?.also { put("rate_option", rateOption) }
            salesChannel?.also { put("sales_channel", listOf(salesChannel.toString())) }
            currency?.also { put("currency", listOf(currency.toString())) }
        }
    }
}
