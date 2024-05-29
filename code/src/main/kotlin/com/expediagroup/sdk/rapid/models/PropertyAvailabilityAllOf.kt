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
 * The rooms and rates for a property.
 * @param rooms Array of objects containing room information.
 * @param links
 */
data class PropertyAvailabilityAllOf(
    // Array of objects containing room information.
    @JsonProperty("rooms")
    @field:Valid
    val rooms: kotlin.collections.List<RoomAvailability>? = null,
    @JsonProperty("links")
    @field:Valid
    val links: PropertyAvailabilityLinks? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var rooms: kotlin.collections.List<RoomAvailability>? = null,
        private var links: PropertyAvailabilityLinks? = null
    ) {
        fun rooms(rooms: kotlin.collections.List<RoomAvailability>?) = apply { this.rooms = rooms }

        fun links(links: PropertyAvailabilityLinks?) = apply { this.links = links }

        fun build(): PropertyAvailabilityAllOf {
            return PropertyAvailabilityAllOf(
                rooms = rooms,
                links = links
            )
        }
    }
}
