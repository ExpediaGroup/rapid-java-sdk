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
 * @param centerLongitude Center Longitude.
 * @param centerLatitude Center Latitude.
 * @param boundingPolygon
 */
data class CoordinatesRegion(
    // Center Longitude.
    @JsonProperty("center_longitude")
    @field:Valid
    val centerLongitude: java.math.BigDecimal? = null,
    // Center Latitude.
    @JsonProperty("center_latitude")
    @field:Valid
    val centerLatitude: java.math.BigDecimal? = null,
    @JsonProperty("bounding_polygon")
    @field:Valid
    val boundingPolygon: BoundingPolygon? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var centerLongitude: java.math.BigDecimal? = null,
        private var centerLatitude: java.math.BigDecimal? = null,
        private var boundingPolygon: BoundingPolygon? = null
    ) {
        fun centerLongitude(centerLongitude: java.math.BigDecimal?) = apply { this.centerLongitude = centerLongitude }

        fun centerLatitude(centerLatitude: java.math.BigDecimal?) = apply { this.centerLatitude = centerLatitude }

        fun boundingPolygon(boundingPolygon: BoundingPolygon?) = apply { this.boundingPolygon = boundingPolygon }

        fun build(): CoordinatesRegion {
            return CoordinatesRegion(
                centerLongitude = centerLongitude,
                centerLatitude = centerLatitude,
                boundingPolygon = boundingPolygon
            )
        }
    }
}
