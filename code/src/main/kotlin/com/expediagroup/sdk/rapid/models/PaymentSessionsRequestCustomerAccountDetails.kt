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
 * @param authenticationMethod Mechanism used by the cardholder to authenticate to the merchant.
 * @param authenticationTimestamp Date and time in UTC of the cardholder authentication, in extended ISO 8601 format.
 * @param createDate Date the cardholder opened the account with the merchant, in ISO 8601 format (YYYY-MM-DD).
 * @param changeDate Date the cardholder’s account with the merchant was last changed, including Billing or Shipping address, new payment account, or new user(s) added, in ISO 8601 format (YYYY-MM-DD).
 * @param passwordChangeDate Date the cardholder’s account with the merchant had a password change or account reset, in ISO 8601 format (YYYY-MM-DD).
 * @param addCardAttempts Number of add card attempts in the last 24 hours.
 * @param accountPurchases Number of purchases with this cardholder's account during the previous six months.
 */
data class PaymentSessionsRequestCustomerAccountDetails(
    // Mechanism used by the cardholder to authenticate to the merchant.
    @JsonProperty("authentication_method")
    val authenticationMethod: PaymentSessionsRequestCustomerAccountDetails.AuthenticationMethod? = null,
    // Date and time in UTC of the cardholder authentication, in extended ISO 8601 format.
    @JsonProperty("authentication_timestamp")
    @field:Valid
    val authenticationTimestamp: kotlin.String? = null,
    // Date the cardholder opened the account with the merchant, in ISO 8601 format (YYYY-MM-DD).
    @JsonProperty("create_date")
    @field:Valid
    val createDate: kotlin.String? = null,
    // Date the cardholder’s account with the merchant was last changed, including Billing or Shipping address, new payment account, or new user(s) added, in ISO 8601 format (YYYY-MM-DD).
    @JsonProperty("change_date")
    @field:Valid
    val changeDate: kotlin.String? = null,
    // Date the cardholder’s account with the merchant had a password change or account reset, in ISO 8601 format (YYYY-MM-DD).
    @JsonProperty("password_change_date")
    @field:Valid
    val passwordChangeDate: kotlin.String? = null,
    // Number of add card attempts in the last 24 hours.
    @JsonProperty("add_card_attempts")
    @field:Valid
    val addCardAttempts: java.math.BigDecimal? = null,
    // Number of purchases with this cardholder's account during the previous six months.
    @JsonProperty("account_purchases")
    @field:Valid
    val accountPurchases: java.math.BigDecimal? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var authenticationMethod: PaymentSessionsRequestCustomerAccountDetails.AuthenticationMethod? = null,
        private var authenticationTimestamp: kotlin.String? = null,
        private var createDate: kotlin.String? = null,
        private var changeDate: kotlin.String? = null,
        private var passwordChangeDate: kotlin.String? = null,
        private var addCardAttempts: java.math.BigDecimal? = null,
        private var accountPurchases: java.math.BigDecimal? = null
    ) {
        fun authenticationMethod(authenticationMethod: PaymentSessionsRequestCustomerAccountDetails.AuthenticationMethod?) = apply { this.authenticationMethod = authenticationMethod }

        fun authenticationTimestamp(authenticationTimestamp: kotlin.String?) = apply { this.authenticationTimestamp = authenticationTimestamp }

        fun createDate(createDate: kotlin.String?) = apply { this.createDate = createDate }

        fun changeDate(changeDate: kotlin.String?) = apply { this.changeDate = changeDate }

        fun passwordChangeDate(passwordChangeDate: kotlin.String?) = apply { this.passwordChangeDate = passwordChangeDate }

        fun addCardAttempts(addCardAttempts: java.math.BigDecimal?) = apply { this.addCardAttempts = addCardAttempts }

        fun accountPurchases(accountPurchases: java.math.BigDecimal?) = apply { this.accountPurchases = accountPurchases }

        fun build(): PaymentSessionsRequestCustomerAccountDetails {
            return PaymentSessionsRequestCustomerAccountDetails(
                authenticationMethod = authenticationMethod,
                authenticationTimestamp = authenticationTimestamp,
                createDate = createDate,
                changeDate = changeDate,
                passwordChangeDate = passwordChangeDate,
                addCardAttempts = addCardAttempts,
                accountPurchases = accountPurchases
            )
        }
    }

    /**
     * Mechanism used by the cardholder to authenticate to the merchant.
     * Values: GUEST,OWN_CREDENTIALS,FEDERATED_ID,ISSUER_CREDENTIALS,THIRD_PARTY_AUTHENTICATION,FIDO_AUTHENTICATION
     */
    enum class AuthenticationMethod(val value: kotlin.String) {
        @JsonProperty("guest")
        GUEST("guest"),

        @JsonProperty("own_credentials")
        OWN_CREDENTIALS("own_credentials"),

        @JsonProperty("federated_id")
        FEDERATED_ID("federated_id"),

        @JsonProperty("issuer_credentials")
        ISSUER_CREDENTIALS("issuer_credentials"),

        @JsonProperty("third_party_authentication")
        THIRD_PARTY_AUTHENTICATION("third_party_authentication"),

        @JsonProperty("fido_authentication")
        FIDO_AUTHENTICATION("fido_authentication")
    }
}
