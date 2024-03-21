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
 * @param cavv Cryptographic element used to indicate Authentication was successfully performed
 * @param eci Electronic Commerce Indicator. The ECI is used in payer authentication to indicate the level of security used when the cardholder provided payment information to the merchant. Its value corresponds to the authentication result and the characteristics of the merchant checkout process. Each card network, e.g., Visa, MasterCard, JCB, has specific rules around the appropriate values and use of the ECI.
 * @param threeDsVersion Indicates what version of 3DS was used to authenticate the user.
 * @param dsTransactionId Directory Server Transaction Id. Returned during authentication and is used as an additional parameter to validate that transaction was authenticated.
 * @param paResStatus set only if PAResStatus value is received in the authentication response
 * @param veResStatus set this only if PAResStatus value is received in the authentication response if Authentication was Frictionless → AuthenticationResponse.PAResStatus, if Authentication was a successful challenge → \"C\" (This is the directory response for challenge)
 * @param xid String used by both Visa and MasterCard which identifies a specific transaction on the Directory This string value should remain consistent throughout a transaction's history.
 * @param cavvAlgorithm Used in some scenarios for 3DS 1.0.
 * @param ucafIndicator Only received for Mastercard transactions, else can be null. 0 - Non-SecureCode transaction, bypassed by the Merchant 1 - Merchant-Only SecureCode transaction 2 - Fully authenticated SecureCode transaction
 */
data class ThirdPartyAuthRequest(
    // Cryptographic element used to indicate Authentication was successfully performed
    @JsonProperty("cavv")
    @field:Valid
    val cavv: kotlin.String,
    // Electronic Commerce Indicator. The ECI is used in payer authentication to indicate the level of security used when the cardholder provided payment information to the merchant. Its value corresponds to the authentication result and the characteristics of the merchant checkout process. Each card network, e.g., Visa, MasterCard, JCB, has specific rules around the appropriate values and use of the ECI.
    @JsonProperty("eci")
    @field:Valid
    val eci: kotlin.String,
    // Indicates what version of 3DS was used to authenticate the user.
    @JsonProperty("three_ds_version")
    @field:Valid
    val threeDsVersion: kotlin.String,
    // Directory Server Transaction Id. Returned during authentication and is used as an additional parameter to validate that transaction was authenticated.
    @JsonProperty("ds_transaction_id")
    @field:Valid
    val dsTransactionId: kotlin.String,
    // set only if PAResStatus value is received in the authentication response
    @JsonProperty("pa_res_status")
    @field:Valid
    val paResStatus: kotlin.String? = null,
    // set this only if PAResStatus value is received in the authentication response if Authentication was Frictionless → AuthenticationResponse.PAResStatus, if Authentication was a successful challenge → \"C\" (This is the directory response for challenge)
    @JsonProperty("ve_res_status")
    @field:Valid
    val veResStatus: kotlin.String? = null,
    // String used by both Visa and MasterCard which identifies a specific transaction on the Directory This string value should remain consistent throughout a transaction's history.
    @JsonProperty("xid")
    @field:Valid
    val xid: kotlin.String? = null,
    // Used in some scenarios for 3DS 1.0.
    @JsonProperty("cavv_algorithm")
    @field:Valid
    val cavvAlgorithm: kotlin.String? = null,
    // Only received for Mastercard transactions, else can be null. 0 - Non-SecureCode transaction, bypassed by the Merchant 1 - Merchant-Only SecureCode transaction 2 - Fully authenticated SecureCode transaction
    @JsonProperty("ucaf_indicator")
    @field:Valid
    val ucafIndicator: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cavv: kotlin.String? = null,
        private var eci: kotlin.String? = null,
        private var threeDsVersion: kotlin.String? = null,
        private var dsTransactionId: kotlin.String? = null,
        private var paResStatus: kotlin.String? = null,
        private var veResStatus: kotlin.String? = null,
        private var xid: kotlin.String? = null,
        private var cavvAlgorithm: kotlin.String? = null,
        private var ucafIndicator: kotlin.String? = null
    ) {
        fun cavv(cavv: kotlin.String) = apply { this.cavv = cavv }

        fun eci(eci: kotlin.String) = apply { this.eci = eci }

        fun threeDsVersion(threeDsVersion: kotlin.String) = apply { this.threeDsVersion = threeDsVersion }

        fun dsTransactionId(dsTransactionId: kotlin.String) = apply { this.dsTransactionId = dsTransactionId }

        fun paResStatus(paResStatus: kotlin.String?) = apply { this.paResStatus = paResStatus }

        fun veResStatus(veResStatus: kotlin.String?) = apply { this.veResStatus = veResStatus }

        fun xid(xid: kotlin.String?) = apply { this.xid = xid }

        fun cavvAlgorithm(cavvAlgorithm: kotlin.String?) = apply { this.cavvAlgorithm = cavvAlgorithm }

        fun ucafIndicator(ucafIndicator: kotlin.String?) = apply { this.ucafIndicator = ucafIndicator }

        fun build(): ThirdPartyAuthRequest {
            // Check required params
            validateNullity()
            return ThirdPartyAuthRequest(
                cavv = cavv!!,
                eci = eci!!,
                threeDsVersion = threeDsVersion!!,
                dsTransactionId = dsTransactionId!!,
                paResStatus = paResStatus,
                veResStatus = veResStatus,
                xid = xid,
                cavvAlgorithm = cavvAlgorithm,
                ucafIndicator = ucafIndicator
            )
        }

        private fun validateNullity() {
            if (cavv == null) {
                throw NullPointerException("Required parameter cavv is missing")
            }
            if (eci == null) {
                throw NullPointerException("Required parameter eci is missing")
            }
            if (threeDsVersion == null) {
                throw NullPointerException("Required parameter threeDsVersion is missing")
            }
            if (dsTransactionId == null) {
                throw NullPointerException("Required parameter dsTransactionId is missing")
            }
        }
    }
}
