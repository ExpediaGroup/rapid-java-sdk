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
 * An individual rate.
 * @param id Unique identifier for a rate.
 * @param amenities This lists all of the Amenities available with a specific rate, including value adds, such as breakfast. See our [amenities reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known amenity ID and name values.
 * @param specialOfferDescription A text description of any special offers for this rate.
 */
data class RateContent(
    // Unique identifier for a rate.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // This lists all of the Amenities available with a specific rate, including value adds, such as breakfast. See our [amenities reference](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for current known amenity ID and name values.
    @JsonProperty("amenities")
    @field:Valid
    val amenities: kotlin.collections.Map<kotlin.String, Amenity>? = null,
    // A text description of any special offers for this rate.
    @JsonProperty("special_offer_description")
    @field:Valid
    val specialOfferDescription: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var amenities: kotlin.collections.Map<kotlin.String, Amenity>? = null,
        private var specialOfferDescription: kotlin.String? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun amenities(amenities: kotlin.collections.Map<kotlin.String, Amenity>?) = apply { this.amenities = amenities }

        fun specialOfferDescription(specialOfferDescription: kotlin.String?) = apply { this.specialOfferDescription = specialOfferDescription }

        fun build(): RateContent {
            return RateContent(
                id = id,
                amenities = amenities,
                specialOfferDescription = specialOfferDescription
            )
        }
    }
}
