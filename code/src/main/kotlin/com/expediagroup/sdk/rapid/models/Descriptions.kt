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
 * Descriptions of a property.
 * @param amenities Describes general building amenities at the property.
 * @param dining Describes dining accommodations at the property.
 * @param renovations Describes any recent room or property renovations.
 * @param nationalRatings States the source of the property's star rating (such as a regional or national tourism agency) and any other ratings claimed.
 * @param businessAmenities Describes any business-specific amenities at the property, e.g. conference rooms.
 * @param rooms Describes typical room amenities.
 * @param attractions Nearby attractions/areas, often including distances from the property.
 * @param location General location as entered by the property.
 * @param headline A headline description for the property.
 * @param general A general description of a vacation rental property.
 */
data class Descriptions(
    // Describes general building amenities at the property.
    @JsonProperty("amenities")
    @field:Valid
    val amenities: kotlin.String? = null,
    // Describes dining accommodations at the property.
    @JsonProperty("dining")
    @field:Valid
    val dining: kotlin.String? = null,
    // Describes any recent room or property renovations.
    @JsonProperty("renovations")
    @field:Valid
    val renovations: kotlin.String? = null,
    // States the source of the property's star rating (such as a regional or national tourism agency) and any other ratings claimed.
    @JsonProperty("national_ratings")
    @field:Valid
    val nationalRatings: kotlin.String? = null,
    // Describes any business-specific amenities at the property, e.g. conference rooms.
    @JsonProperty("business_amenities")
    @field:Valid
    val businessAmenities: kotlin.String? = null,
    // Describes typical room amenities.
    @JsonProperty("rooms")
    @field:Valid
    val rooms: kotlin.String? = null,
    // Nearby attractions/areas, often including distances from the property.
    @JsonProperty("attractions")
    @field:Valid
    val attractions: kotlin.String? = null,
    // General location as entered by the property.
    @JsonProperty("location")
    @field:Valid
    val location: kotlin.String? = null,
    // A headline description for the property.
    @JsonProperty("headline")
    @field:Valid
    val headline: kotlin.String? = null,
    // A general description of a vacation rental property.
    @JsonProperty("general")
    @field:Valid
    val general: kotlin.String? = null,
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var amenities: kotlin.String? = null,
        private var dining: kotlin.String? = null,
        private var renovations: kotlin.String? = null,
        private var nationalRatings: kotlin.String? = null,
        private var businessAmenities: kotlin.String? = null,
        private var rooms: kotlin.String? = null,
        private var attractions: kotlin.String? = null,
        private var location: kotlin.String? = null,
        private var headline: kotlin.String? = null,
        private var general: kotlin.String? = null,
    ) {
        fun amenities(amenities: kotlin.String?) = apply { this.amenities = amenities }

        fun dining(dining: kotlin.String?) = apply { this.dining = dining }

        fun renovations(renovations: kotlin.String?) = apply { this.renovations = renovations }

        fun nationalRatings(nationalRatings: kotlin.String?) = apply { this.nationalRatings = nationalRatings }

        fun businessAmenities(businessAmenities: kotlin.String?) = apply { this.businessAmenities = businessAmenities }

        fun rooms(rooms: kotlin.String?) = apply { this.rooms = rooms }

        fun attractions(attractions: kotlin.String?) = apply { this.attractions = attractions }

        fun location(location: kotlin.String?) = apply { this.location = location }

        fun headline(headline: kotlin.String?) = apply { this.headline = headline }

        fun general(general: kotlin.String?) = apply { this.general = general }

        fun build(): Descriptions {
            return Descriptions(
                amenities = amenities,
                dining = dining,
                renovations = renovations,
                nationalRatings = nationalRatings,
                businessAmenities = businessAmenities,
                rooms = rooms,
                attractions = attractions,
                location = location,
                headline = headline,
                general = general,
            )
        }
    }
}
