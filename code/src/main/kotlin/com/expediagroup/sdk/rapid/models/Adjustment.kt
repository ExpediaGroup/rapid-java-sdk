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
 * Any price adjustments associated with this itinerary.
 * @param `value` The amount of the adjustment.
 * @param type The type of the adjustment.
 * @param currency The currency of the adjustment.
 */
data class Adjustment(
    // The amount of the adjustment.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The type of the adjustment.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // The currency of the adjustment.
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
        private var type: kotlin.String? = null,
        private var currency: kotlin.String? = null
    ) {
        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun build(): Adjustment {
            return Adjustment(
                `value` = `value`,
                type = type,
                currency = currency
            )
        }
    }
}
