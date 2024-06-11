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

package com.expediagroup.sdk.rapid.models

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

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

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
    @field:Valid
    val version: kotlin.String,
    // The customer's browser accept header that was used in the booking request.
    @JsonProperty("browser_accept_header")
    @field:Valid
    val browserAcceptHeader: kotlin.String,
    // Encoded browser metadata, provided by the EgPayments.js library.
    @JsonProperty("encoded_browser_metadata")
    @field:Valid
    val encodedBrowserMetadata: kotlin.String,
    // The preferred window size that needs to be displayed to the customer. Following are the possible values of this field:   * `extra_small`: 250 x 400   * `small`: 390 x 400   * `medium`: 600 x 400   * `large`: 500 x 600   * `full_screen`: Full screen
    @JsonProperty("preferred_challenge_window_size")
    val preferredChallengeWindowSize: PaymentSessionsRequest.PreferredChallengeWindowSize,
    // Fully qualified URL of merchant website or customer care site.
    @JsonProperty("merchant_url")
    @field:Valid
    val merchantUrl: kotlin.String,
    @JsonProperty("customer_account_details")
    @field:Valid
    val customerAccountDetails: PaymentSessionsRequestCustomerAccountDetails,
    @JsonProperty("payments")
    @field:Valid
    val payments: kotlin.collections.List<PaymentRequest>
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

        fun preferredChallengeWindowSize(preferredChallengeWindowSize: PaymentSessionsRequest.PreferredChallengeWindowSize) = apply { this.preferredChallengeWindowSize = preferredChallengeWindowSize }

        fun merchantUrl(merchantUrl: kotlin.String) = apply { this.merchantUrl = merchantUrl }

        fun customerAccountDetails(customerAccountDetails: PaymentSessionsRequestCustomerAccountDetails) = apply { this.customerAccountDetails = customerAccountDetails }

        fun payments(payments: kotlin.collections.List<PaymentRequest>) = apply { this.payments = payments }

        fun build(): PaymentSessionsRequest {
            // Check required params
            validateNullity()
            return PaymentSessionsRequest(
                version = version!!,
                browserAcceptHeader = browserAcceptHeader!!,
                encodedBrowserMetadata = encodedBrowserMetadata!!,
                preferredChallengeWindowSize = preferredChallengeWindowSize!!,
                merchantUrl = merchantUrl!!,
                customerAccountDetails = customerAccountDetails!!,
                payments = payments!!
            )
        }

        private fun validateNullity() {
            if (version == null) {
                throw NullPointerException("Required parameter version is missing")
            }
            if (browserAcceptHeader == null) {
                throw NullPointerException("Required parameter browserAcceptHeader is missing")
            }
            if (encodedBrowserMetadata == null) {
                throw NullPointerException("Required parameter encodedBrowserMetadata is missing")
            }
            if (preferredChallengeWindowSize == null) {
                throw NullPointerException("Required parameter preferredChallengeWindowSize is missing")
            }
            if (merchantUrl == null) {
                throw NullPointerException("Required parameter merchantUrl is missing")
            }
            if (customerAccountDetails == null) {
                throw NullPointerException("Required parameter customerAccountDetails is missing")
            }
            if (payments == null) {
                throw NullPointerException("Required parameter payments is missing")
            }
        }
    }

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
