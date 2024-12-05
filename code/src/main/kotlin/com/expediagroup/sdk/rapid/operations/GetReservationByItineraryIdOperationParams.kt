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
 * @property itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 * @property email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>
 * @property include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.
 */
@JsonDeserialize(builder = GetReservationByItineraryIdOperationParams.Builder::class)
data class GetReservationByItineraryIdOperationParams
    internal constructor(
        @field:NotNull
        @field:Valid
        val itineraryId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val customerIp: kotlin.String? = null,
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        val test: GetReservationByItineraryIdOperationParams.Test? = null,
        @field:Valid
        val token: kotlin.String? = null,
        @field:Valid
        val email: kotlin.String? = null,
        val include: kotlin.collections.List<
            GetReservationByItineraryIdOperationParams.Include
        >? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            itineraryId: kotlin.String,
            customerIp: kotlin.String,
            customerSessionId: kotlin.String? =
                null,
            test: GetReservationByItineraryIdOperationParams.Test? =
                null,
            token: kotlin.String? =
                null,
            email: kotlin.String? =
                null,
            include: kotlin.collections.List<
                GetReservationByItineraryIdOperationParams.Include
            >? =
                null
        ) : this(
            itineraryId = itineraryId,
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            email = email,
            include = include,
            dummy = Unit
        )

        constructor(context: GetReservationByItineraryIdOperationContext?) : this(
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

            INTERNAL_SERVER_ERROR("internal_server_error")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        enum class Include(
            val value: kotlin.String
        ) {
            HISTORY("history")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        class Builder(
            @JsonProperty("itinerary_id") private var itineraryId: kotlin.String? = null,
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: GetReservationByItineraryIdOperationParams.Test? = null,
            @JsonProperty("token") private var token: kotlin.String? = null,
            @JsonProperty("email") private var email: kotlin.String? = null,
            @JsonProperty("include") private var include: kotlin.collections.List<
                GetReservationByItineraryIdOperationParams.Include
            >? = null
        ) {
            /**
             * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
             */
            fun itineraryId(itineraryId: kotlin.String) = apply { this.itineraryId = itineraryId }

            /**
             * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
             */
            fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

            /**
             * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
             */
            fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

            /**
             * @param test The retrieve call has a test header that can be used to return set responses. Passing standard in the Test header will retrieve a test booking, and passing any of the errors listed below will return a stubbed error response that you can use to test your error handling code. Additionally, refer to the Test Request documentation for more details on how these header values are used. * `standard` - Requires valid test booking. * `service_unavailable` * `internal_server_error`
             */
            fun test(test: GetReservationByItineraryIdOperationParams.Test) = apply { this.test = test }

            /**
             * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
             */
            fun token(token: kotlin.String) = apply { this.token = token }

            /**
             * @param email Email associated with the booking. Special characters in the local part or domain should be encoded. (Email is required if the token is not provided the request) <br>
             */
            fun email(email: kotlin.String) = apply { this.email = email }

            /**
             * @param include Options for which information to return in the response. The value must be lower case. * `history` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `other` in the response.   * `history_v2` - Include itinerary history, showing details of the changes made to this itinerary. Changes from the property/supplier have an event_source equal to `supplier` in the response. See the [Itinerary history](https://developers.expediagroup.com/docs/rapid/lodging/manage-booking/itinerary-history#overview) for details.
             */
            fun include(
                include: kotlin.collections.List<
                    GetReservationByItineraryIdOperationParams.Include
                >
            ) = apply { this.include = include }

            companion object {
                @JvmStatic
                fun from(link: GetReservationByItineraryIdOperationLink): Builder {
                    val uri = link.href?.let { URI(it) }
                    val params = uri?.query?.parseUrlEncodedParameters()

                    val builder = Builder()

                    val itineraryId =
                        params?.get("itineraryId")

                    itineraryId?.let {
                        builder.itineraryId(
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
                    val email =
                        params?.get("email")

                    email?.let {
                        builder.email(
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

                    return builder
                }
            }

            fun build(): GetReservationByItineraryIdOperationParams {
                val params =
                    GetReservationByItineraryIdOperationParams(
                        itineraryId = itineraryId!!,
                        customerIp = customerIp!!,
                        customerSessionId = customerSessionId,
                        test = test,
                        token = token,
                        email = email,
                        include = include
                    )

                validate(params)

                return params
            }

            private fun validate(params: GetReservationByItineraryIdOperationParams) {
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
                itineraryId = itineraryId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token,
                email = email,
                include = include
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
                email?.let {
                    append("email", it)
                }
                include?.let {
                    appendAll("include", it.map { it.value })
                }
            }

        override fun getPathParams(): Map<String, String> =
            buildMap {
                itineraryId?.also {
                    put("itinerary_id", itineraryId)
                }
            }
    }
