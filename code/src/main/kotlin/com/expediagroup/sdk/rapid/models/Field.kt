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
 * An individual field that had an error.
 * @param name The field that had an error.
 * @param type The type of the field that had an error.
 * @param `value` The value of the field that had an error.
 */
data class Field(
    // The field that had an error.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // The type of the field that had an error.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // The value of the field that had an error.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var type: kotlin.String? = null,
        private var `value`: kotlin.String? = null
    ) {
        fun name(name: kotlin.String?) = apply { this.name = name }

        fun type(type: kotlin.String?) = apply { this.type = type }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun build(): Field {
            return Field(
                name = name,
                type = type,
                `value` = `value`
            )
        }
    }
}
