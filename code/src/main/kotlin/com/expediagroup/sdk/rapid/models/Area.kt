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
 * Information about the area of the room.
 * @param squareMeters The room's area, measured in square meters.
 * @param squareFeet The room's area, measured in square feet.
 */
data class Area(
    // The room's area, measured in square meters.
    @JsonProperty("square_meters")
    @field:Valid
    val squareMeters: java.math.BigDecimal? = null,
    // The room's area, measured in square feet.
    @JsonProperty("square_feet")
    @field:Valid
    val squareFeet: java.math.BigDecimal? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var squareMeters: java.math.BigDecimal? = null,
        private var squareFeet: java.math.BigDecimal? = null
    ) {
        fun squareMeters(squareMeters: java.math.BigDecimal?) = apply { this.squareMeters = squareMeters }

        fun squareFeet(squareFeet: java.math.BigDecimal?) = apply { this.squareFeet = squareFeet }

        fun build(): Area {
            return Area(
                squareMeters = squareMeters,
                squareFeet = squareFeet
            )
        }
    }
}
