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
 * An individual bed configuration.
 * @param type The type of this bed configuration in the room.
 * @param propertySize The size of this bed configuration in the room.
 * @param quantity The number of this bed configuration in the room.
 */
data class BedGroupConfiguration(
    // The type of this bed configuration in the room.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // The size of this bed configuration in the room.
    @JsonProperty("size")
    @field:Valid
    val propertySize: kotlin.String? = null,
    // The number of this bed configuration in the room.
    @JsonProperty("quantity")
    @field:Valid
    val quantity: java.math.BigDecimal? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var propertySize: kotlin.String? = null,
        private var quantity: java.math.BigDecimal? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun propertySize(propertySize: kotlin.String?) = apply { this.propertySize = propertySize }

        fun quantity(quantity: java.math.BigDecimal?) = apply { this.quantity = quantity }

        fun build(): BedGroupConfiguration {
            return BedGroupConfiguration(
                type = type,
                propertySize = propertySize,
                quantity = quantity
            )
        }
    }
}
