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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.core.model.exception.client.PropertyConstraintViolationException
import com.expediagroup.sdk.rapid.models.PaymentSessionsLinks
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation

/**
 * The payment registration response.
 * @param paymentSessionId The registered payment session ID.
 * @param encodedInitConfig A base64 encoded object which contains configuration needed to perform device fingerprinting. It is used in conjunction with the provided Javascript library for PSD2.
 * @param links
 */
data class PaymentSessions(
    // The registered payment session ID.
    @JsonProperty("payment_session_id")
    @field:Valid
    val paymentSessionId: kotlin.String? = null,
    // A base64 encoded object which contains configuration needed to perform device fingerprinting. It is used in conjunction with the provided Javascript library for PSD2.
    @JsonProperty("encoded_init_config")
    @field:Valid
    val encodedInitConfig: kotlin.String? = null,
    @JsonProperty("links")
    @field:Valid
    val links: PaymentSessionsLinks? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var paymentSessionId: kotlin.String? = null,
        private var encodedInitConfig: kotlin.String? = null,
        private var links: PaymentSessionsLinks? = null
    ) {
        fun paymentSessionId(paymentSessionId: kotlin.String?) = apply { this.paymentSessionId = paymentSessionId }

        fun encodedInitConfig(encodedInitConfig: kotlin.String?) = apply { this.encodedInitConfig = encodedInitConfig }

        fun links(links: PaymentSessionsLinks?) = apply { this.links = links }

        fun build(): PaymentSessions {
            val instance =
                PaymentSessions(
                    paymentSessionId = paymentSessionId,
                    encodedInitConfig = encodedInitConfig,
                    links = links
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PaymentSessions) {
            val validator =
                Validation
                    .byDefaultProvider()
                    .configure()
                    .messageInterpolator(ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .validator

            val violations = validator.validate(instance)

            if (violations.isNotEmpty()) {
                throw PropertyConstraintViolationException(
                    constraintViolations = violations.map { "${it.propertyPath}: ${it.message}" }
                )
            }
        }
    }

    fun toBuilder() =
        Builder(
            paymentSessionId = paymentSessionId,
            encodedInitConfig = encodedInitConfig,
            links = links
        )
}
