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
 * The refund information for cancelling the itinerary.
 * @param amount The amount of the refund on cancelling the itinerary.
 * @param currency The currency of the refund amount.
 */
data class CancelRefund(
    // The amount of the refund on cancelling the itinerary.
    @JsonProperty("amount")
    @field:Valid
    val amount: kotlin.String? = null,
    // The currency of the refund amount.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var amount: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun amount(amount: kotlin.String?) = apply { this.amount = amount }

        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun build(): CancelRefund {
            return CancelRefund(
                amount = amount,
                currency = currency
            )
        }
    }
}
