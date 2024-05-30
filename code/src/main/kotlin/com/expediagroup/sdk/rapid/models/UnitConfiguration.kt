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
 * A room configuration.
 * @param type Bed type.
 * @param description A description of the bed(s) in requested language.
 * @param quantity The number of beds of this size.
 */
data class UnitConfiguration(
    // Bed type.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // A description of the bed(s) in requested language.
    @JsonProperty("description")
    @field:Valid
    val description: kotlin.String? = null,
    // The number of beds of this size.
    @JsonProperty("quantity")
    @field:Valid
    val quantity: kotlin.Int? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var quantity: kotlin.Int? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun quantity(quantity: kotlin.Int?) = apply { this.quantity = quantity }

        fun build(): UnitConfiguration {
            return UnitConfiguration(
                type = type,
                description = description,
                quantity = quantity
            )
        }
    }
}
