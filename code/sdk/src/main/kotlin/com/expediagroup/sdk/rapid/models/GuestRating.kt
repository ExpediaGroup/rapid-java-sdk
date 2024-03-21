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
 * Rating information provided by guests who stayed at this property.
 * @param count A count of all of the guest review ratings which currently exist for this property.
 * @param overall The overall rating for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param cleanliness The cleanliness rating for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param service The rating of the staff's service for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param comfort The comfort rating of the rooms, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param condition The rating for the property's condition, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param location The rating for how convinent the location of the property is, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param neighborhood The rating for how satisfying the neighborhood of the property is, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param quality The quality rating of the rooms, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param `value` The rating for how much value the property provided for the cost of the stay, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param amenities The rating for the amenities provided by the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
 * @param recommendationPercent The percent of guests who recommend staying at this property.
 */
data class GuestRating(
    // A count of all of the guest review ratings which currently exist for this property.
    @JsonProperty("count")
    @field:Valid
    val count: java.math.BigDecimal? = null,
    // The overall rating for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("overall")
    @field:Valid
    val overall: kotlin.String? = null,
    // The cleanliness rating for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("cleanliness")
    @field:Valid
    val cleanliness: kotlin.String? = null,
    // The rating of the staff's service for the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("service")
    @field:Valid
    val service: kotlin.String? = null,
    // The comfort rating of the rooms, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("comfort")
    @field:Valid
    val comfort: kotlin.String? = null,
    // The rating for the property's condition, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("condition")
    @field:Valid
    val condition: kotlin.String? = null,
    // The rating for how convinent the location of the property is, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("location")
    @field:Valid
    val location: kotlin.String? = null,
    // The rating for how satisfying the neighborhood of the property is, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("neighborhood")
    @field:Valid
    val neighborhood: kotlin.String? = null,
    // The quality rating of the rooms, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("quality")
    @field:Valid
    val quality: kotlin.String? = null,
    // The rating for how much value the property provided for the cost of the stay, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // The rating for the amenities provided by the property, averaged from all guest reviews. Returns a value between 1.0 and 5.0.
    @JsonProperty("amenities")
    @field:Valid
    val amenities: kotlin.String? = null,
    // The percent of guests who recommend staying at this property.
    @JsonProperty("recommendation_percent")
    @field:Valid
    val recommendationPercent: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var count: java.math.BigDecimal? = null,
        private var overall: kotlin.String? = null,
        private var cleanliness: kotlin.String? = null,
        private var service: kotlin.String? = null,
        private var comfort: kotlin.String? = null,
        private var condition: kotlin.String? = null,
        private var location: kotlin.String? = null,
        private var neighborhood: kotlin.String? = null,
        private var quality: kotlin.String? = null,
        private var `value`: kotlin.String? = null,
        private var amenities: kotlin.String? = null,
        private var recommendationPercent: kotlin.String? = null
    ) {
        fun count(count: java.math.BigDecimal?) = apply { this.count = count }

        fun overall(overall: kotlin.String?) = apply { this.overall = overall }

        fun cleanliness(cleanliness: kotlin.String?) = apply { this.cleanliness = cleanliness }

        fun service(service: kotlin.String?) = apply { this.service = service }

        fun comfort(comfort: kotlin.String?) = apply { this.comfort = comfort }

        fun condition(condition: kotlin.String?) = apply { this.condition = condition }

        fun location(location: kotlin.String?) = apply { this.location = location }

        fun neighborhood(neighborhood: kotlin.String?) = apply { this.neighborhood = neighborhood }

        fun quality(quality: kotlin.String?) = apply { this.quality = quality }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun amenities(amenities: kotlin.String?) = apply { this.amenities = amenities }

        fun recommendationPercent(recommendationPercent: kotlin.String?) = apply { this.recommendationPercent = recommendationPercent }

        fun build(): GuestRating {
            return GuestRating(
                count = count,
                overall = overall,
                cleanliness = cleanliness,
                service = service,
                comfort = comfort,
                condition = condition,
                location = location,
                neighborhood = neighborhood,
                quality = quality,
                `value` = `value`,
                amenities = amenities,
                recommendationPercent = recommendationPercent
            )
        }
    }
}
