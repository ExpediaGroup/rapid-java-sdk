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
 * GeoJSON geometry
 * @param type The geometry type. The only supported type is `Polygon`
 * @param coordinates An array of linear ring coordinate arrays that combine to make up a single [Polygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.6) in geojson format. If there is more than one linear ring at this level, the first is the outer boundary and the remaining linear rings are interior rings or holes.
 */
data class PropertiesGeoJsonRequest(
    // The geometry type. The only supported type is `Polygon`
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String,
    // An array of linear ring coordinate arrays that combine to make up a single [Polygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.6) in geojson format. If there is more than one linear ring at this level, the first is the outer boundary and the remaining linear rings are interior rings or holes.
    @JsonProperty("coordinates")
    @field:Valid
    val coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>? = null
    ) {
        fun type(type: kotlin.String) = apply { this.type = type }

        fun coordinates(coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>) = apply { this.coordinates = coordinates }

        fun build(): PropertiesGeoJsonRequest {
            // Check required params
            validateNullity()
            return PropertiesGeoJsonRequest(
                type = type!!,
                coordinates = coordinates!!
            )
        }

        private fun validateNullity() {
            if (type == null) {
                throw NullPointerException("Required parameter type is missing")
            }
            if (coordinates == null) {
                throw NullPointerException("Required parameter coordinates is missing")
            }
        }
    }
}
