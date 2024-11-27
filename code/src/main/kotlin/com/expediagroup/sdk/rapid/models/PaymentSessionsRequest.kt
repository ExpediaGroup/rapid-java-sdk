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
import com.expediagroup.sdk.rapid.models.PaymentRequest
import com.expediagroup.sdk.rapid.models.PaymentSessionsRequestCustomerAccountDetails
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator
import javax.validation.Valid
import javax.validation.Validation
import javax.validation.constraints.NotNull

/**
 *
 * @param version The version of the EgPayments.js library.
 * @param browserAcceptHeader The customer's browser accept header that was used in the booking request.
 * @param encodedBrowserMetadata Encoded browser metadata, provided by the EgPayments.js library.
 * @param preferredChallengeWindowSize The preferred window size that needs to be displayed to the customer. Following are the possible values of this field:   * `extra_small`: 250 x 400   * `small`: 390 x 400   * `medium`: 600 x 400   * `large`: 500 x 600   * `full_screen`: Full screen
 * @param merchantUrl Fully qualified URL of merchant website or customer care site.
 * @param customerAccountDetails
 * @param payments
 */
data class PaymentSessionsRequest(
    // The version of the EgPayments.js library.
    @JsonProperty("version")
    @field:NotNull
    @field:Valid
    val version: kotlin.String,
    // The customer's browser accept header that was used in the booking request.
    @JsonProperty("browser_accept_header")
    @field:NotNull
    @field:Valid
    val browserAcceptHeader: kotlin.String,
    // Encoded browser metadata, provided by the EgPayments.js library.
    @JsonProperty("encoded_browser_metadata")
    @field:NotNull
    @field:Valid
    val encodedBrowserMetadata: kotlin.String,
    // The preferred window size that needs to be displayed to the customer. Following are the possible values of this field:   * `extra_small`: 250 x 400   * `small`: 390 x 400   * `medium`: 600 x 400   * `large`: 500 x 600   * `full_screen`: Full screen
    @JsonProperty("preferred_challenge_window_size")
    @field:NotNull
    val preferredChallengeWindowSize: PaymentSessionsRequest.PreferredChallengeWindowSize,
    // Fully qualified URL of merchant website or customer care site.
    @JsonProperty("merchant_url")
    @field:NotNull
    @field:Valid
    val merchantUrl: kotlin.String,
    @JsonProperty("customer_account_details")
    @field:NotNull
    @field:Valid
    val customerAccountDetails: PaymentSessionsRequestCustomerAccountDetails,
    @JsonProperty("payments")
    @field:NotNull
    @field:Valid
    val payments: kotlin.collections
        .List<
            PaymentRequest
        >
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var version: kotlin.String? = null,
        private var browserAcceptHeader: kotlin.String? = null,
        private var encodedBrowserMetadata: kotlin.String? = null,
        private var preferredChallengeWindowSize: PaymentSessionsRequest.PreferredChallengeWindowSize? = null,
        private var merchantUrl: kotlin.String? = null,
        private var customerAccountDetails: PaymentSessionsRequestCustomerAccountDetails? = null,
        private var payments: kotlin.collections.List<PaymentRequest>? = null
    ) {
        fun version(version: kotlin.String) = apply { this.version = version }

        fun browserAcceptHeader(browserAcceptHeader: kotlin.String) = apply { this.browserAcceptHeader = browserAcceptHeader }

        fun encodedBrowserMetadata(encodedBrowserMetadata: kotlin.String) = apply { this.encodedBrowserMetadata = encodedBrowserMetadata }

        fun preferredChallengeWindowSize(preferredChallengeWindowSize: PaymentSessionsRequest.PreferredChallengeWindowSize) =
            apply {
                this.preferredChallengeWindowSize =
                    preferredChallengeWindowSize
            }

        fun merchantUrl(merchantUrl: kotlin.String) = apply { this.merchantUrl = merchantUrl }

        fun customerAccountDetails(customerAccountDetails: PaymentSessionsRequestCustomerAccountDetails) = apply { this.customerAccountDetails = customerAccountDetails }

        fun payments(payments: kotlin.collections.List<PaymentRequest>) = apply { this.payments = payments }

        fun build(): PaymentSessionsRequest {
            val instance =
                PaymentSessionsRequest(
                    version = version!!,
                    browserAcceptHeader = browserAcceptHeader!!,
                    encodedBrowserMetadata = encodedBrowserMetadata!!,
                    preferredChallengeWindowSize = preferredChallengeWindowSize!!,
                    merchantUrl = merchantUrl!!,
                    customerAccountDetails = customerAccountDetails!!,
                    payments = payments!!
                )

            validate(instance)

            return instance
        }

        private fun validate(instance: PaymentSessionsRequest) {
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
            version = version!!,
            browserAcceptHeader = browserAcceptHeader!!,
            encodedBrowserMetadata = encodedBrowserMetadata!!,
            preferredChallengeWindowSize = preferredChallengeWindowSize!!,
            merchantUrl = merchantUrl!!,
            customerAccountDetails = customerAccountDetails!!,
            payments = payments!!
        )

    /**
     * The preferred window size that needs to be displayed to the customer. Following are the possible values of this field:   * `extra_small`: 250 x 400   * `small`: 390 x 400   * `medium`: 600 x 400   * `large`: 500 x 600   * `full_screen`: Full screen
     * Values: EXTRA_SMALL,SMALL,MEDIUM,LARGE,FULL_SCREEN
     */
    enum class PreferredChallengeWindowSize(val value: kotlin.String) {
        @JsonProperty("extra_small")
        EXTRA_SMALL("extra_small"),

        @JsonProperty("small")
        SMALL("small"),

        @JsonProperty("medium")
        MEDIUM("medium"),

        @JsonProperty("large")
        LARGE("large"),

        @JsonProperty("full_screen")
        FULL_SCREEN("full_screen")
    }
}
