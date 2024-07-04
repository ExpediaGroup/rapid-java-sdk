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
 * A reply from the property management to the review left by the traveler.
 * @param text The text of the management response itself.
 * @param date When this management response was made, in ISO 8601 format.
 */
data class ManagementResponse(
    // The text of the management response itself.
    @JsonProperty("text")
    @field:Valid
    val text: kotlin.String? = null,
    // When this management response was made, in ISO 8601 format.
    @JsonProperty("date")
    @field:Valid
    val date: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var text: kotlin.String? = null,
        private var date: kotlin.String? = null
    ) {
        fun text(text: kotlin.String?) = apply { this.text = text }

        fun date(date: kotlin.String?) = apply { this.date = date }

        fun build(): ManagementResponse {
            return ManagementResponse(
                text = text,
                date = date
            )
        }
    }
}
