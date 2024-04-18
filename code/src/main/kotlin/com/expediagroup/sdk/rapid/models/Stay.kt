/*
 * Copyright (C) 2024 Expedia, Inc.
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
 * @param type
 * @param `value` The value of the amount object. Decimal point inline with correct precision.
 * @param currency Currency of the amount object.
 */
data class Stay(
    @JsonProperty("type")
    @field:Valid
    val type: StayType? = null,
    // The value of the amount object. Decimal point inline with correct precision.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // Currency of the amount object.
    @JsonProperty("currency")
    @field:Valid
    val currency: kotlin.String? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: StayType? = null,
        private var `value`: kotlin.String? = null,
        private var currency: kotlin.String? = null,
    ) {
        fun type(type: StayType?) = apply { this.type = type }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun currency(currency: kotlin.String?) = apply { this.currency = currency }

        fun build(): Stay {
            return Stay(
                type = type,
                `value` = `value`,
                currency = currency,
            )
        }
    }
}
