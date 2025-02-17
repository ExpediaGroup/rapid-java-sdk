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

/**
 * @property customerIp IP address of the customer, as captured by your integration. Ensure your integration passes the customer's IP, not your own. This value helps determine their location for ad relevancy. Also used for fraud recovery and other important analytics.
 */
@JsonDeserialize(builder = GetAdsOperationParams.Builder::class)
data class GetAdsOperationParams(
    @field:Valid
    val customerIp: kotlin.String? =
        null
) :
    OperationParams {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        @JsonProperty("Customer-Ip") private var customerIp: kotlin.String? = null
    ) {
        /**
         * @param customerIp IP address of the customer, as captured by your integration. Ensure your integration passes the customer's IP, not your own. This value helps determine their location for ad relevancy. Also used for fraud recovery and other important analytics.
         */
        fun customerIp(customerIp: kotlin.String) = apply { this.customerIp = customerIp }

        fun build(): GetAdsOperationParams {
            val params =
                GetAdsOperationParams(
                    customerIp = customerIp
                )

            validate(params)

            return params
        }

        private fun validate(params: GetAdsOperationParams) {
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
            customerIp = customerIp
        )

    override fun getHeaders(): Headers =
        Headers.build {
            customerIp?.let {
                append("Customer-Ip", it)
            }
            append("Accept", "application/json")
        }

    override fun getQueryParams(): Parameters =
        Parameters.build {
        }

    override fun getPathParams(): Map<String, String> =
        buildMap {
        }
}
