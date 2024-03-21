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
 * An individual error.
 * @param type The error type.
 * @param message A human readable message giving details about this error.
 * @param fields Details about the specific fields that had an error.
 */
data class ErrorIndividual(
    // The error type.
    @JsonProperty("type")
    @field:Valid
    val type: kotlin.String? = null,
    // A human readable message giving details about this error.
    @JsonProperty("message")
    @field:Valid
    val message: kotlin.String? = null,
    // Details about the specific fields that had an error.
    @JsonProperty("fields")
    @field:Valid
    val fields: kotlin.collections.List<Field>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var type: kotlin.String? = null,
        private var message: kotlin.String? = null,
        private var fields: kotlin.collections.List<Field>? = null
    ) {
        fun type(type: kotlin.String?) = apply { this.type = type }

        fun message(message: kotlin.String?) = apply { this.message = message }

        fun fields(fields: kotlin.collections.List<Field>?) = apply { this.fields = fields }

        fun build(): ErrorIndividual {
            return ErrorIndividual(
                type = type,
                message = message,
                fields = fields
            )
        }
    }
}
