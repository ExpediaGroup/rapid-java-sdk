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
 * @property roomId Room ID of a property.<br>
 * @property rateId Rate ID of a room.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`
 * @property token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
 */
@JsonDeserialize(builder = PriceCheckOperationParams.Builder::class)
data class PriceCheckOperationParams
    internal constructor(
        @field:NotNull
        @field:Valid
        val propertyId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val roomId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val rateId: kotlin.String? = null,
        @field:Valid
        val customerIp: kotlin.String? = null,
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        val test: PriceCheckOperationParams.Test? = null,
        @field:NotNull
        @field:Valid
        val token: kotlin.String? = null,
        private val dummy: Unit
    ) :
    OperationParams {
        companion object {
            @JvmStatic
            fun builder() = Builder()
        }

        constructor(
            propertyId: kotlin.String,
            roomId: kotlin.String,
            rateId: kotlin.String,
            customerIp: kotlin.String? =
                null,
            customerSessionId: kotlin.String? =
                null,
            test: PriceCheckOperationParams.Test? =
                null,
            token: kotlin.String
        ) : this(
            propertyId = propertyId,
            roomId = roomId,
            rateId = rateId,
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            dummy = Unit
        )

        constructor(context: PriceCheckOperationContext?) : this(
            customerIp = context?.customerIp,
            customerSessionId = context?.customerSessionId,
            test = context?.test,
            dummy = Unit
        )

        enum class Test(
            val value: kotlin.String
        ) {
            AVAILABLE("available"),

            PRICE_CHANGED("price_changed"),

            SOLD_OUT("sold_out"),

            SERVICE_UNAVAILABLE("service_unavailable"),

            UNKNOWN_INTERNAL_ERROR("unknown_internal_error")

            ;

            companion object {
                private val map = entries.associateBy { it.value }

                infix fun from(value: kotlin.String) = map[value]
            }
        }

        class Builder(
            @JsonProperty("property_id") private var propertyId: kotlin.String? = null,
            @JsonProperty("room_id") private var roomId: kotlin.String? = null,
            @JsonProperty("rate_id") private var rateId: kotlin.String? = null,
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: PriceCheckOperationParams.Test? = null,
            @JsonProperty("token") private var token: kotlin.String? = null
        ) {
            /**
             * @param propertyId Expedia Property ID.<br>
             */
            fun propertyId(propertyId: kotlin.String) = apply { this.propertyId = propertyId }

            /**
             * @param roomId Room ID of a property.<br>
             */
            fun roomId(roomId: kotlin.String) = apply { this.roomId = roomId }

            /**
             * @param rateId Rate ID of a room.<br>
             */
            fun rateId(rateId: kotlin.String) = apply { this.rateId = rateId }

            /**
             * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
             */
            fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

            /**
             * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
             */
            fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

            /**
             * @param test Price check calls have a test header that can be used to return set responses with the following keywords:   * `available`   * `price_changed`   * `sold_out`   * `service_unavailable`   * `unknown_internal_error`
             */
            fun test(test: PriceCheckOperationParams.Test) = apply { this.test = test }

            /**
             * @param token A hashed collection of query parameters. Used to maintain state across calls. This token is provided as part of the price check link from the shop response.
             */
            fun token(token: kotlin.String) = apply { this.token = token }

            companion object {
                @JvmStatic
                fun from(link: PriceCheckOperationLink): Builder {
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
                    val roomId =
                        params?.get("roomId")

                    roomId?.let {
                        builder.roomId(
                            it
                        )
                    }
                    val rateId =
                        params?.get("rateId")

                    rateId?.let {
                        builder.rateId(
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

                    return builder
                }
            }

            fun build(): PriceCheckOperationParams {
                val params =
                    PriceCheckOperationParams(
                        propertyId = propertyId!!,
                        roomId = roomId!!,
                        rateId = rateId!!,
                        customerIp = customerIp,
                        customerSessionId = customerSessionId,
                        test = test,
                        token = token!!
                    )

                validate(params)

                return params
            }

            private fun validate(params: PriceCheckOperationParams) {
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
                roomId = roomId,
                rateId = rateId,
                customerIp = customerIp,
                customerSessionId = customerSessionId,
                test = test,
                token = token
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
            }

        override fun getPathParams(): Map<String, String> =
            buildMap {
                propertyId?.also {
                    put("property_id", propertyId)
                }
                roomId?.also {
                    put("room_id", roomId)
                }
                rateId?.also {
                    put("rate_id", rateId)
                }
            }
    }
