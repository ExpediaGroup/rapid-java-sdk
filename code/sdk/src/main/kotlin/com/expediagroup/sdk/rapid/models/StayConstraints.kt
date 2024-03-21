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

/**
 *
 * @param minStay The minimum number of days for a stay.
 * @param maxStay The maximum number of days for a stay.
 */
data class StayConstraints(
    // The minimum number of days for a stay.
    @JsonProperty("min_stay")
    val minStay: kotlin.Int? = null,
    // The maximum number of days for a stay.
    @JsonProperty("max_stay")
    val maxStay: kotlin.Int? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var minStay: kotlin.Int? = null,
        private var maxStay: kotlin.Int? = null
    ) {
        fun minStay(minStay: kotlin.Int?) = apply { this.minStay = minStay }

        fun maxStay(maxStay: kotlin.Int?) = apply { this.maxStay = maxStay }

        fun build(): StayConstraints {
            return StayConstraints(
                minStay = minStay,
                maxStay = maxStay
            )
        }
    }
}
