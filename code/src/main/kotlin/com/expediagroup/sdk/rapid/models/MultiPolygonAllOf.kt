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
 *
 * @param coordinates An array of multiple polygon(s) that combine to make a full [MultiPolygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.7) in geojson format.
 */
data class MultiPolygonAllOf(
    // An array of multiple polygon(s) that combine to make a full [MultiPolygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.7) in geojson format.
    @JsonProperty("coordinates")
    @field:Valid
    val coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>? = null
    ) {
        fun coordinates(coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>?) =
            apply { this.coordinates = coordinates }

        fun build(): MultiPolygonAllOf {
            return MultiPolygonAllOf(
                coordinates = coordinates
            )
        }
    }
}
