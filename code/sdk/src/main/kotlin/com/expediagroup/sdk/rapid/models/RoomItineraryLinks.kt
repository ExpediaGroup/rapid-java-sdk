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
 * A map of links - * `cancel` - Cancel the booking for this room * `change` - A PUT call to modify the details of the booking for this room (soft change) * `shop_for_change` - Shop for rates to evaluate for possible rebooking. This shop call will show the estimated financial impact of the change.<br>   Current parameters supported in shop for change: `checkin`, `checkout`, `occupancy` See: [additional rates](#get-/properties/-property_id-/availability)
 * @param cancel
 * @param change
 * @param shopForChange
 */
data class RoomItineraryLinks(
    @JsonProperty("cancel")
    @field:Valid
    val cancel: Link? = null,
    @JsonProperty("change")
    @field:Valid
    val change: Link? = null,
    @JsonProperty("shop_for_change")
    @field:Valid
    val shopForChange: Link? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var cancel: Link? = null,
        private var change: Link? = null,
        private var shopForChange: Link? = null
    ) {
        fun cancel(cancel: Link?) = apply { this.cancel = cancel }

        fun change(change: Link?) = apply { this.change = change }

        fun shopForChange(shopForChange: Link?) = apply { this.shopForChange = shopForChange }

        fun build(): RoomItineraryLinks {
            return RoomItineraryLinks(
                cancel = cancel,
                change = change,
                shopForChange = shopForChange
            )
        }
    }
}
