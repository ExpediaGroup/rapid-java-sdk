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
 * @param start Start date of nonrefundable date range in ISO 8601 format.
 * @param end End date of nonrefundable date range in ISO 8601 format.
 */
data class NonrefundableDateRange(
    // Start date of nonrefundable date range in ISO 8601 format.
    @JsonProperty("start")
    @field:Valid
    val start: kotlin.String? = null,
    // End date of nonrefundable date range in ISO 8601 format.
    @JsonProperty("end")
    @field:Valid
    val end: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var start: kotlin.String? = null,
        private var end: kotlin.String? = null
    ) {
        fun start(start: kotlin.String?) = apply { this.start = start }

        fun end(end: kotlin.String?) = apply { this.end = end }

        fun build(): NonrefundableDateRange {
            return NonrefundableDateRange(
                start = start,
                end = end
            )
        }
    }
}
