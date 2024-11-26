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
 * @property itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
 * @property roomId Room ID of a property.<br>
 * @property customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
 * @property customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
 * @property test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
 * @property token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
 */
@JsonDeserialize(builder = ChangeRoomDetailsOperationParams.Builder::class)
data class ChangeRoomDetailsOperationParams
    internal constructor(
        @field:NotNull
        @field:Valid
        val itineraryId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val roomId: kotlin.String? = null,
        @field:NotNull
        @field:Valid
        val customerIp: kotlin.String? = null,
        @field:Valid
        val customerSessionId: kotlin.String? = null,
        val test: ChangeRoomDetailsOperationParams.Test? = null,
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
            itineraryId: kotlin.String,
            roomId: kotlin.String,
            customerIp: kotlin.String,
            customerSessionId: kotlin.String? =
                null,
            test: ChangeRoomDetailsOperationParams.Test? =
                null,
            token: kotlin.String
        ) : this(
            itineraryId = itineraryId,
            roomId = roomId,
            customerIp = customerIp,
            customerSessionId = customerSessionId,
            test = test,
            token = token,
            dummy = Unit
        )

        constructor(context: ChangeRoomDetailsOperationContext?) : this(
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

        class Builder(
            @JsonProperty("itinerary_id") private var itineraryId: kotlin.String? = null,
            @JsonProperty("room_id") private var roomId: kotlin.String? = null,
            @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null,
            @JsonProperty("Customer-Session-Id") private var customerSessionId: kotlin.String? = null,
            @JsonProperty("Test") private var test: ChangeRoomDetailsOperationParams.Test? = null,
            @JsonProperty("token") private var token: kotlin.String? = null
        ) {
            /**
             * @param itineraryId This parameter is used only to prefix the token value - no ID value is used.<br>
             */
            fun itineraryId(itineraryId: kotlin.String) = apply { this.itineraryId = itineraryId }

            /**
             * @param roomId Room ID of a property.<br>
             */
            fun roomId(roomId: kotlin.String) = apply { this.roomId = roomId }

            /**
             * @param customerIp IP address of the customer, as captured by your integration.<br> Ensure your integration passes the customer's IP, not your own. This value helps determine their location and assign the correct payment gateway.<br> Also used for fraud recovery and other important analytics.
             */
            fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

            /**
             * @param customerSessionId Insert your own unique value for each user session, beginning with the first API call. Continue to pass the same value for each subsequent API call during the user's session, using a new value for every new customer session.<br> Including this value greatly eases EPS's internal debugging process for issues with partner requests, as it explicitly links together request paths for individual user's session.
             */
            fun customerSessionId(customerSessionId: kotlin.String) = apply { this.customerSessionId = customerSessionId }

            /**
             * @param test The change call has a test header that can be used to return set responses with the following keywords:<br> * `standard` - Requires valid test booking. * `service_unavailable` * `unknown_internal_error`
             */
            fun test(test: ChangeRoomDetailsOperationParams.Test) = apply { this.test = test }

            /**
             * @param token Provided as part of the link object and used to maintain state across calls. This simplifies each subsequent call by limiting the amount of information required at each step and reduces the potential for errors. Token values cannot be viewed or changed.
             */
            fun token(token: kotlin.String) = apply { this.token = token }

            fun build(): ChangeRoomDetailsOperationParams {
                val params =
                    ChangeRoomDetailsOperationParams(
                        itineraryId = itineraryId!!,
                        roomId = roomId!!,
                        customerIp = customerIp!!,
                        customerSessionId = customerSessionId,
                        test = test,
                        token = token!!
                    )

                validate(params)

                return params
            }

            private fun validate(params: ChangeRoomDetailsOperationParams) {
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
                roomId = roomId,
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
            }

        override fun getQueryParams(): Parameters =
            Parameters.build {
                token?.let {
                    append("token", it)
                }
            }

        override fun getPathParams(): Map<String, String> =
            buildMap {
                itineraryId?.also {
                    put("itinerary_id", itineraryId)
                }
                roomId?.also {
                    put("room_id", roomId)
                }
            }
    }
