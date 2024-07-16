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
/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.rapid.models.BoundingPolygon
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 *
 * @param coordinates An array of multiple polygon(s) that combine to make a full [MultiPolygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.7) in geojson format.
 */
data class MultiPolygon(
    // An array of multiple polygon(s) that combine to make a full [MultiPolygon](https://www.rfc-editor.org/rfc/rfc7946#section-3.1.7) in geojson format.
    @JsonProperty("coordinates")
    @field:Valid
    val coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>? = null
) : BoundingPolygon {
    @JsonProperty("type")
    override val type: kotlin.String = "MULTIPOLYGON"

    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>? = null
    ) {
        fun coordinates(coordinates: kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<kotlin.collections.List<java.math.BigDecimal>>>>?) =
            apply { this.coordinates = coordinates }

        fun build(): MultiPolygon {
            return MultiPolygon(
                coordinates = coordinates
            )
        }
    }
}
