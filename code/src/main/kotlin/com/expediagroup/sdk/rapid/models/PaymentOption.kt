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
 * The payment option response.
 * @param affiliateCollect
 * @param creditCard
 */
data class PaymentOption(
    @JsonProperty("affiliate_collect")
    @field:Valid
    val affiliateCollect: AffiliateCollect? = null,
    @JsonProperty("credit_card")
    @field:Valid
    val creditCard: CreditCard? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var affiliateCollect: AffiliateCollect? = null,
        private var creditCard: CreditCard? = null
    ) {
        fun affiliateCollect(affiliateCollect: AffiliateCollect?) = apply { this.affiliateCollect = affiliateCollect }

        fun creditCard(creditCard: CreditCard?) = apply { this.creditCard = creditCard }

        fun build(): PaymentOption {
            return PaymentOption(
                affiliateCollect = affiliateCollect,
                creditCard = creditCard
            )
        }
    }
}
