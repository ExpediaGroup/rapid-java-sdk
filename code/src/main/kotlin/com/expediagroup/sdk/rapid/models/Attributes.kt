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
    "UnusedImport",
)

package com.expediagroup.sdk.rapid.models

import com.expediagroup.sdk.rapid.models.Attribute
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Attributes about the property. See our [attributes reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known attribute ID and name values.
 * @param general Lists all of the general attributes about the property.
 * @param pets Lists all of the pet attributes about the property.
 */
data class Attributes(
    // Lists all of the general attributes about the property.
    @JsonProperty("general")
    @field:Valid
    val general: kotlin.collections.Map<kotlin.String, Attribute>? = null,
    // Lists all of the pet attributes about the property.
    @JsonProperty("pets")
    @field:Valid
    val pets: kotlin.collections.Map<kotlin.String, Attribute>? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var general: kotlin.collections.Map<kotlin.String, Attribute>? = null,
        private var pets: kotlin.collections.Map<kotlin.String, Attribute>? = null,
    ) {
        fun general(general: kotlin.collections.Map<kotlin.String, Attribute>?) = apply { this.general = general }

        fun pets(pets: kotlin.collections.Map<kotlin.String, Attribute>?) = apply { this.pets = pets }

        fun build(): Attributes {
            return Attributes(
                general = general,
                pets = pets,
            )
        }
    }

    fun toBuilder() =
        Builder(
            general = general,
            pets = pets,
        )
}
