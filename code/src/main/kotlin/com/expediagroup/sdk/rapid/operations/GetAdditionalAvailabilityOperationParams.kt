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
import io.ktor.http.parseUrlEncodedParameters
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import java.net.URI
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 * @property propertyId Expedia Property ID.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test Shop calls have a test header that can be used to return set responses with the following keywords:<br> * `standard` * `service_unavailable` * `unknown_internal_error` * `no_availability` * `forbidden`
 * @property token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the `additional_rates` link from the shop response, or the `shop` link on a `sold_out` price check response. It is also provided from the `shop_for_change` link on an itinerary retrieve.
 * @property checkin Check-in date, in ISO 8601 format (YYYY-MM-DD). This can be up to 365 days in the future. Some partner configurations may extend this up to 500 days.<br> Note: Only needed for hard change if desired check-in date is different than original booking. If specified must also specify `checkout`.
 * @property checkout Check-out date, in ISO 8601 format (YYYY-MM-DD). Total length of stay cannot exceed 28 nights or 365 nights depending on Vacation Rental configurations.<br> Note: Only needed for hard change if desired check-out date is different than original booking. If specified must also specify `checkin`.<br>
 * @property exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.
 * @property filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
 * @property include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.
 * @property occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
 * @property rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
 * @property salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
 * @property currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases
 */
@JsonDeserialize(builder = GetAdditionalAvailabilityOperationParams.Builder::class)
data class GetAdditionalAvailabilityOperationParams
    internal constructor(
        @field:NotNull
        @field:Valid
        val propertyId: kotlin.String? = null,
        @field:Valid
        val customerIp: kotlin.String? = null,
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        val test: GetAdditionalAvailabilityOperationParams.Test? = null,
        @field:NotNull
        @field:Valid
        val token: kotlin.String? = null,
        @field:Valid
        val checkin: kotlin.String? = null,
        @field:Valid
        val checkout: kotlin.String? = null,
        val exclusion: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Exclusion
        >? = null,
        val filter: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Filter
        >? = null,
        val include: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.Include
        >? = null,
        @field:Valid
        val occupancy: kotlin.collections.List<
            kotlin.String
        >? = null,
        val rateOption: kotlin.collections.List<
            GetAdditionalAvailabilityOperationParams.RateOption
        >? = null,
        @field:Valid
        val salesChannel: kotlin.String? = null,
        @field:Valid
        val currency: kotlin.String? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            propertyId: kotlin.String,
            customerIp: kotlin.String? =
                null,
            customerSessionId: kotlin.String? =
                null,
            test: GetAdditionalAvailabilityOperationParams.Test? =
                null,
            token: kotlin.String,
            checkin: kotlin.String? =
                null,
            checkout: kotlin.String? =
                null,
            exclusion: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Exclusion
            >? =
                null,
            filter: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Filter
            >? =
                null,
            include: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Include
            >? =
                null,
            occupancy: kotlin.collections.List<
                kotlin.String
            >? =
                null,
            rateOption: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.RateOption
            >? =
                null,
            salesChannel: kotlin.String? =
                null,
            currency: kotlin.String? =
                null
        ) : this(
            propertyId = propertyId,
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            checkin = checkin,
            checkout = checkout,
            exclusion = exclusion,
            filter = filter,
            include = include,
            occupancy = occupancy,
            rateOption = rateOption,
            salesChannel = salesChannel,
            currency = currency,
            dummy = Unit
        )

        constructor(context: GetAdditionalAvailabilityOperationContext?) : this(
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

            UNKNOWN_INTERNAL_ERROR("unknown_internal_error"),

            NO_AVAILABILITY("no_availability"),

            FORBIDDEN("forbidden")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        enum class Exclusion(
            val value: kotlin.String
        ) {
            REFUNDABLE_DAMAGE_DEPOSIT("refundable_damage_deposit"),

            CARD_ON_FILE("card_on_file")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        enum class Filter(
            val value: kotlin.String
        ) {
            REFUNDABLE("refundable"),

            EXPEDIA_COLLECT("expedia_collect"),

            PROPERTY_COLLECT("property_collect")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        enum class Include(
            val value: kotlin.String
        ) {
            SALE_SCENARIO_PERIOD_MOBILE_PROMOTION("sale_scenario.mobile_promotion")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        enum class RateOption(
            val value: kotlin.String
        ) {
            MEMBER("member"),

            NET_RATES("net_rates"),

            CROSS_SELL("cross_sell")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        class Builder(
            @JsonProperty("property_id") private var propertyId: kotlin.String? = null,
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: GetAdditionalAvailabilityOperationParams.Test? = null,
            @JsonProperty("token") private var token: kotlin.String? = null,
            @JsonProperty("checkin") private var checkin: kotlin.String? = null,
            @JsonProperty("checkout") private var checkout: kotlin.String? = null,
            @JsonProperty("exclusion") private var exclusion: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Exclusion
            >? = null,
            @JsonProperty("filter") private var filter: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Filter
            >? = null,
            @JsonProperty("include") private var include: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.Include
            >? = null,
            @JsonProperty("occupancy") private var occupancy: kotlin.collections.List<
                kotlin.String
            >? = null,
            @JsonProperty("rate_option") private var rateOption: kotlin.collections.List<
                GetAdditionalAvailabilityOperationParams.RateOption
            >? = null,
            @JsonProperty("sales_channel") private var salesChannel: kotlin.String? = null,
            @JsonProperty("currency") private var currency: kotlin.String? = null
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
            fun test(test: GetAdditionalAvailabilityOperationParams.Test) = apply { this.test = test }

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
             * @param exclusion Single exclusion type. Send multiple instances of this parameter to request multiple exclusions.<br> Note: Optional parameter for use with hard change requests. <br> * `refundable_damage_deposit` - Excludes Rapid supplied Vrbo rates with refundable damage deposits from the response. * `card_on_file` - Excludes Rapid supplied Vrbo rates with card-on-file damage collection from the response.
             */
            fun exclusion(
                exclusion: kotlin.collections.List<
                    GetAdditionalAvailabilityOperationParams.Exclusion
                >
            ) = apply { this.exclusion = exclusion }

            /**
             * @param filter Single filter type. Send multiple instances of this parameter to request multiple filters.<br> Note: Optional parameter for use with hard change requests.<br> This parameter cannot be set to `property_collect` if the existing booking is `expedia_collect` and vice versa.<br> * `refundable` - Filters results to only show fully refundable rates. * `expedia_collect` - Filters results to only show rates where payment is collected by Expedia at the time of booking. These properties can be eligible for payments via Expedia Affiliate Collect(EAC). * `property_collect` - Filters results to only show rates where payment is collected by the property after booking. This can include rates that require a deposit by the property, dependent upon the deposit policies. * `loyalty` - Filters results to only show rates that are eligible for loyalty points.
             */
            fun filter(
                filter: kotlin.collections.List<
                    GetAdditionalAvailabilityOperationParams.Filter
                >
            ) = apply { this.filter = filter }

            /**
             * @param include Modify the response by including types of responses that are not provided by default.<br> * `sale_scenario.mobile_promotion` - Enable the `mobile_promotion` flag under the `sale_scenario` section of the response.
             */
            fun include(
                include: kotlin.collections.List<
                    GetAdditionalAvailabilityOperationParams.Include
                >
            ) = apply { this.include = include }

            /**
             * @param occupancy Defines the requested occupancy for a single room. Each room must have at least 1 adult occupant.<br> Format: `numberOfAdults[-firstChildAge[,nextChildAge]]`<br> To request multiple rooms (of the same type), include one instance of occupancy for each room requested. Up to 8 rooms may be requested or booked at once.<br> Note: Only needed for hard change if desired occupancy is different than original booking.<br> Examples: * 2 adults, one 9-year-old and one 4-year-old would be represented by `occupancy=2-9,4`.<br> * A multi-room request to lodge an additional 2 adults would be represented by `occupancy=2-9,4&occupancy=2`
             */
            fun occupancy(
                occupancy: kotlin.collections.List<
                    kotlin.String
                >
            ) = apply { this.occupancy = occupancy }

            /**
             * @param rateOption Request specific rate options for each property. Send multiple instances of this parameter to request multiple rate options. Note: Optional parameter for use with hard change requests.<br> Accepted values:<br> * `member` - Return member rates for each property. This feature must be enabled and requires a user to be logged in to request these rates. * `net_rates` - Return net rates for each property. This feature must be enabled to request these rates. * `cross_sell` - Identify if the traffic is coming from a cross sell booking. Where the traveler has booked another service (flight, car, activities...) before hotel.
             */
            fun rateOption(
                rateOption: kotlin.collections.List<
                    GetAdditionalAvailabilityOperationParams.RateOption
                >
            ) = apply { this.rateOption = rateOption }

            /**
             * @param salesChannel Provide the sales channel if you wish to override the sales_channel provided in the previous call. EPS dynamically provides the best content for optimal conversion on each sales channel.<br> Note: Must specify this value for hard change requests.<br> * `website` - Standard website accessed from the customer's computer * `agent_tool` - Your own agent tool used by your call center or retail store agent * `mobile_app` - An application installed on a phone or tablet device * `mobile_web` - A web browser application on a phone or tablet device * `meta` - Rates will be passed to and displayed on a 3rd party comparison website * `cache` - Rates will be used to populate a local cache
             */
            fun salesChannel(salesChannel: kotlin.String) = apply { this.salesChannel = salesChannel }

            /**
             * @param currency Determines the returned currency type throughout the response <br> Note: This parameter is only valid for hard change requests and is ignored in all other cases
             */
            fun currency(currency: kotlin.String) = apply { this.currency = currency }

            companion object {
                @JvmStatic
                fun from(link: GetAdditionalAvailabilityOperationLink): Builder {
                    val uri = link.href?.let { URI(it) }
                    val params = uri?.query?.parseUrlEncodedParameters()

                    val builder = Builder()

                    val propertyId =
                        params?.get("propertyId")

                    propertyId?.let {
                        builder.propertyId(
                            it
                        )
                    }
                    val customerIp =
                        params?.get("customerIp")

                    customerIp?.let {
                        builder.customerIp(
                            it
                        )
                    }
                    val customerSessionId =
                        params?.get("customerSessionId")

                    customerSessionId?.let {
                        builder.customerSessionId(
                            it
                        )
                    }
                    val test =
                        params?.get("test")
                            ?.let { Test.from(it) }

                    test?.let {
                        builder.test(
                            it
                        )
                    }
                    val token =
                        params?.get("token")

                    token?.let {
                        builder.token(
                            it
                        )
                    }
                    val checkin =
                        params?.get("checkin")

                    checkin?.let {
                        builder.checkin(
                            it
                        )
                    }
                    val checkout =
                        params?.get("checkout")

                    checkout?.let {
                        builder.checkout(
                            it
                        )
                    }
                    val exclusion =
                        params?.getAll("exclusion")
                            ?.mapNotNull { Exclusion.from(it) }
                    params?.get("exclusion")
                        ?.let { Exclusion.from(it) }

                    exclusion?.let {
                        builder.exclusion(
                            it
                        )
                    }
                    val filter =
                        params?.getAll("filter")
                            ?.mapNotNull { Filter.from(it) }
                    params?.get("filter")
                        ?.let { Filter.from(it) }

                    filter?.let {
                        builder.filter(
                            it
                        )
                    }
                    val include =
                        params?.getAll("include")
                            ?.mapNotNull { Include.from(it) }
                    params?.get("include")
                        ?.let { Include.from(it) }

                    include?.let {
                        builder.include(
                            it
                        )
                    }
                    val occupancy =
                        params?.getAll("occupancy")
                    params?.get("occupancy")

                    occupancy?.let {
                        builder.occupancy(
                            it
                        )
                    }
                    val rateOption =
                        params?.getAll("rateOption")
                            ?.mapNotNull { RateOption.from(it) }
                    params?.get("rateOption")
                        ?.let { RateOption.from(it) }

                    rateOption?.let {
                        builder.rateOption(
                            it
                        )
                    }
                    val salesChannel =
                        params?.get("salesChannel")

                    salesChannel?.let {
                        builder.salesChannel(
                            it
                        )
                    }
                    val currency =
                        params?.get("currency")

                    currency?.let {
                        builder.currency(
                            it
                        )
                    }

                    return builder
                }
            }

            fun build(): GetAdditionalAvailabilityOperationParams {
                val params =
                    GetAdditionalAvailabilityOperationParams(
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

                validate(params)

                return params
            }

            private fun validate(params: GetAdditionalAvailabilityOperationParams) {
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
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
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
                token?.let {
                    append("token", it)
                }
                checkin?.let {
                    append("checkin", it)
                }
                checkout?.let {
                    append("checkout", it)
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
                occupancy?.let {
                    appendAll("occupancy", it)
                }
                rateOption?.let {
                    appendAll("rate_option", it.map { it.value })
                }
                salesChannel?.let {
                    append("sales_channel", it)
                }
                currency?.let {
                    append("currency", it)
                }
            }

        override fun getPathParams(): Map<String, String> =
            buildMap {
                propertyId?.also {
                    put("property_id", propertyId)
                }
            }
    }
