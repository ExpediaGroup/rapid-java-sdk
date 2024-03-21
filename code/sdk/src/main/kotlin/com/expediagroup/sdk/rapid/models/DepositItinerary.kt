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
 * The deposit policy associated with the itinerary.
 * @param currency The currency of the deposit.
 * @param `value` The amount required as deposit.
 * @param due The due date/time of the deposit.
 */
data class DepositItinerary(
    // The currency of the deposit.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null,
    // The amount required as deposit.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The due date/time of the deposit.
    @JsonProperty("due")
    @field:Valid
    val due: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var currency: kotlin.String? = null,
        private var `value`: kotlin.String? = null,
        private var due: kotlin.String? = null
    ) {
        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun due(due: kotlin.String?) = apply { this.due = due }

        fun build(): DepositItinerary {
            return DepositItinerary(
                currency = currency,
                `value` = `value`,
                due = due
            )
        }
    }
}
