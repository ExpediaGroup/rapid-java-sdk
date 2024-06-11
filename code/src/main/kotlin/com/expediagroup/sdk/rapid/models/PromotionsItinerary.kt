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
 * Promotions that apply to the booked room.
 * @param valueAdds Promotions provided by the property that add value to the stay, but don’t affect the booking price (i.e., ski lift tickets or premium wifi).
 */
data class PromotionsItinerary(
    // Promotions provided by the property that add value to the stay, but don’t affect the booking price (i.e., ski lift tickets or premium wifi).
    @JsonProperty("value_adds")
    @field:Valid
    val valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>? = null
    ) {
        fun valueAdds(valueAdds: kotlin.collections.Map<kotlin.String, ValueAdd>?) = apply { this.valueAdds = valueAdds }

        fun build(): PromotionsItinerary {
            return PromotionsItinerary(
                valueAdds = valueAdds
            )
        }
    }
}
