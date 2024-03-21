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
 * A deposit policy associated with the rate.
 * @param `value` The amount that must be paid as a deposit.
 * @param due The due date in ISO 8601 format.
 * @param currency The currency for the deposit amount.
 */
data class Deposit(
    // The amount that must be paid as a deposit.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The due date in ISO 8601 format.
    @JsonProperty("due")
    @field:Valid
    val due: kotlin.String? = null,
    // The currency for the deposit amount.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `value`: kotlin.String? = null,
        private var due: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun due(due: kotlin.String?) = apply { this.due = due }

        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun build(): Deposit {
            return Deposit(
                `value` = `value`,
                due = due,
                currency = currency
            )
        }
    }
}
