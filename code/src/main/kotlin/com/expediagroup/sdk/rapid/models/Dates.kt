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
 * Dates about the property.
 * @param added The UTC date the property’s content was added to EPS, in ISO 8601 format
 * @param updated The UTC date the property’s content was updated by EPS, in ISO 8601 format.
 */
data class Dates(
    // The UTC date the property’s content was added to EPS, in ISO 8601 format
    @JsonProperty("added")
    @field:Valid
    val added: kotlin.String? = null,
    // The UTC date the property’s content was updated by EPS, in ISO 8601 format.
    @JsonProperty("updated")
    @field:Valid
    val updated: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var added: kotlin.String? = null,
        private var updated: kotlin.String? = null
    ) {
        fun added(added: kotlin.String?) = apply { this.added = added }

        fun updated(updated: kotlin.String?) = apply { this.updated = updated }

        fun build(): Dates {
            return Dates(
                added = added,
                updated = updated
            )
        }
    }
}
