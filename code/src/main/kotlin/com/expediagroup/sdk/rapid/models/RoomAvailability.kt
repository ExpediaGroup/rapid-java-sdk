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
 * The room information.
 * @param id Unique Identifier for a room type.
 * @param roomName Name of the room type.
 * @param rates Array of objects containing rate information.
 */
data class RoomAvailability(
    // Unique Identifier for a room type.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Name of the room type.
    @JsonProperty("room_name")
    @field:Valid
    val roomName: kotlin.String? = null,
    // Array of objects containing rate information.
    @JsonProperty("rates")
    @field:Valid
    val rates: kotlin.collections.List<Rate>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var roomName: kotlin.String? = null,
        private var rates: kotlin.collections.List<Rate>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun roomName(roomName: kotlin.String?) = apply { this.roomName = roomName }

        fun rates(rates: kotlin.collections.List<Rate>?) = apply { this.rates = rates }

        fun build(): RoomAvailability {
            return RoomAvailability(
                id = id,
                roomName = roomName,
                rates = rates
            )
        }
    }
}
