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
 * The property's location information.
 * @param coordinates
 * @param obfuscatedCoordinates
 * @param obfuscationRequired When this field is true, the `obfuscated_coordinates` must be used to display approximate location instead of the precise location of `coordinates`.
 */
data class Location(
    @JsonProperty("coordinates")
    @field:Valid
    val coordinates: Coordinates? = null,
    @JsonProperty("obfuscated_coordinates")
    @field:Valid
    val obfuscatedCoordinates: Coordinates? = null,
    // When this field is true, the `obfuscated_coordinates` must be used to display approximate location instead of the precise location of `coordinates`.
    @JsonProperty("obfuscation_required")
    @field:Valid
    val obfuscationRequired: kotlin.Boolean? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var coordinates: Coordinates? = null,
        private var obfuscatedCoordinates: Coordinates? = null,
        private var obfuscationRequired: kotlin.Boolean? = null
    ) {
        fun coordinates(coordinates: Coordinates?) = apply { this.coordinates = coordinates }

        fun obfuscatedCoordinates(obfuscatedCoordinates: Coordinates?) = apply { this.obfuscatedCoordinates = obfuscatedCoordinates }

        fun obfuscationRequired(obfuscationRequired: kotlin.Boolean?) = apply { this.obfuscationRequired = obfuscationRequired }

        fun build(): Location {
            return Location(
                coordinates = coordinates,
                obfuscatedCoordinates = obfuscatedCoordinates,
                obfuscationRequired = obfuscationRequired
            )
        }
    }
}
