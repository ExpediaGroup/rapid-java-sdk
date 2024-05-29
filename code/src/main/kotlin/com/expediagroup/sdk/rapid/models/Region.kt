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
 * @param id Region Id.
 * @param type Region type.
 * @param name Region name.
 * @param nameFull Full region name.
 * @param descriptor Specific information about the region e.g. whether it covers surrounding areas for a city. Only present when relevant for a region. See our [region descriptors reference](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists) for current known descriptor values.
 * @param iataAirportCode 3-character IATA airport code.
 * @param iataAirportMetroCode 3-character IATA airport metropolitan code of the metropolitan airport area.
 * @param countryCode Region country code (ISO 3166-1 alpha-2).
 * @param countrySubdivisionCode Region country subdivision code (ISO 3166-2).
 * @param coordinates
 * @param associations A map of region types to a sorted array of region ids with a touristic association to the region.
 * @param ancestors An array of the region's ancestors.
 * @param descendants A map of region types to an array of region ids. The region ids are direct descendants of the region.
 * @param propertyIds An array of associated property ids for the region.
 * @param propertyIdsExpanded An array of associated property ids within an expanded radius for the region.
 * @param categories A list of regional categories.
 * @param tags A list of regional tags.
 */
data class Region(
    // Region Id.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Region type.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // Region name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // Full region name.
    @JsonProperty("name_full")
    @field:Valid
    val nameFull: kotlin.String? = null,
    // Specific information about the region e.g. whether it covers surrounding areas for a city. Only present when relevant for a region. See our [region descriptors reference](https://developers.expediagroup.com/docs/rapid/lodging/geography/geography-reference-lists) for current known descriptor values.
    @JsonProperty("descriptor")
    @field:Valid
    val descriptor: kotlin.String? = null,
    // 3-character IATA airport code.
    @JsonProperty("iata_airport_code")
    @field:Valid
    val iataAirportCode: kotlin.String? = null,
    // 3-character IATA airport metropolitan code of the metropolitan airport area.
    @JsonProperty("iata_airport_metro_code")
    @field:Valid
    val iataAirportMetroCode: kotlin.String? = null,
    // Region country code (ISO 3166-1 alpha-2).
    @JsonProperty("country_code")
    @field:Valid
    val countryCode: kotlin.String? = null,
    // Region country subdivision code (ISO 3166-2).
    @JsonProperty("country_subdivision_code")
    @field:Valid
    val countrySubdivisionCode: kotlin.String? = null,
    @JsonProperty("coordinates")
    @field:Valid
    val coordinates: CoordinatesRegion? = null,
    // A map of region types to a sorted array of region ids with a touristic association to the region.
    @JsonProperty("associations")
    @field:Valid
    val associations: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // An array of the region's ancestors.
    @JsonProperty("ancestors")
    @field:Valid
    val ancestors: kotlin.collections.List<Ancestors>? = null,
    // A map of region types to an array of region ids. The region ids are direct descendants of the region.
    @JsonProperty("descendants")
    @field:Valid
    val descendants: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
    // An array of associated property ids for the region.
    @JsonProperty("property_ids")
    @field:Valid
    val propertyIds: kotlin.collections.List<kotlin.String>? = null,
    // An array of associated property ids within an expanded radius for the region.
    @JsonProperty("property_ids_expanded")
    @field:Valid
    val propertyIdsExpanded: kotlin.collections.List<kotlin.String>? = null,
    // A list of regional categories.
    @JsonProperty("categories")
    @field:Valid
    val categories: kotlin.collections.List<kotlin.String>? = null,
    // A list of regional tags.
    @JsonProperty("tags")
    @field:Valid
    val tags: kotlin.collections.List<kotlin.String>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var type: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var nameFull: kotlin.String? = null,
        private var descriptor: kotlin.String? = null,
        private var iataAirportCode: kotlin.String? = null,
        private var iataAirportMetroCode: kotlin.String? = null,
        private var countryCode: kotlin.String? = null,
        private var countrySubdivisionCode: kotlin.String? = null,
        private var coordinates: CoordinatesRegion? = null,
        private var associations: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var ancestors: kotlin.collections.List<Ancestors>? = null,
        private var descendants: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>? = null,
        private var propertyIds: kotlin.collections.List<kotlin.String>? = null,
        private var propertyIdsExpanded: kotlin.collections.List<kotlin.String>? = null,
        private var categories: kotlin.collections.List<kotlin.String>? = null,
        private var tags: kotlin.collections.List<kotlin.String>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun nameFull(nameFull: kotlin.String?) = apply { this.nameFull = nameFull }

        fun descriptor(descriptor: kotlin.String?) = apply { this.descriptor = descriptor }

        fun iataAirportCode(iataAirportCode: kotlin.String?) = apply { this.iataAirportCode = iataAirportCode }

        fun iataAirportMetroCode(iataAirportMetroCode: kotlin.String?) = apply { this.iataAirportMetroCode = iataAirportMetroCode }

        fun countryCode(countryCode: kotlin.String?) = apply { this.countryCode = countryCode }

        fun countrySubdivisionCode(countrySubdivisionCode: kotlin.String?) = apply { this.countrySubdivisionCode = countrySubdivisionCode }

        fun coordinates(coordinates: CoordinatesRegion?) = apply { this.coordinates = coordinates }

        fun associations(associations: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) = apply { this.associations = associations }

        fun ancestors(ancestors: kotlin.collections.List<Ancestors>?) = apply { this.ancestors = ancestors }

        fun descendants(descendants: kotlin.collections.Map<kotlin.String, kotlin.collections.List<kotlin.String>>?) = apply { this.descendants = descendants }

        fun propertyIds(propertyIds: kotlin.collections.List<kotlin.String>?) = apply { this.propertyIds = propertyIds }

        fun propertyIdsExpanded(propertyIdsExpanded: kotlin.collections.List<kotlin.String>?) = apply { this.propertyIdsExpanded = propertyIdsExpanded }

        fun categories(categories: kotlin.collections.List<kotlin.String>?) = apply { this.categories = categories }

        fun tags(tags: kotlin.collections.List<kotlin.String>?) = apply { this.tags = tags }

        fun build(): Region {
            return Region(
                id = id,
                type = type,
                name = name,
                nameFull = nameFull,
                descriptor = descriptor,
                iataAirportCode = iataAirportCode,
                iataAirportMetroCode = iataAirportMetroCode,
                countryCode = countryCode,
                countrySubdivisionCode = countrySubdivisionCode,
                coordinates = coordinates,
                associations = associations,
                ancestors = ancestors,
                descendants = descendants,
                propertyIds = propertyIds,
                propertyIdsExpanded = propertyIdsExpanded,
                categories = categories,
                tags = tags
            )
        }
    }
}
