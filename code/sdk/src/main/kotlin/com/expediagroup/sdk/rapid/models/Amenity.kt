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
 * An individual amenity.
 * @param id The amenity definition ID for this amenity.
 * @param name Amenity name.
 * @param `value` Amenity value.
 * @param categories This is an optional field and will be present only if the amenity falls into one or more of these amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.
 */
data class Amenity(
    // The amenity definition ID for this amenity.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Amenity name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // Amenity value.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null,
    // This is an optional field and will be present only if the amenity falls into one or more of these amenity categories.<br> See the Amenity Categories section of the [Content Reference Lists](https://developers.expediagroup.com/docs/rapid/lodging/content/content-reference-lists) for a list of values.
    @JsonProperty("categories")
    @field:Valid
    val categories: kotlin.collections.List<kotlin.String>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var `value`: kotlin.String? = null,
        private var categories: kotlin.collections.List<kotlin.String>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun categories(categories: kotlin.collections.List<kotlin.String>?) = apply { this.categories = categories }

        fun build(): Amenity {
            return Amenity(
                id = id,
                name = name,
                `value` = `value`,
                categories = categories
            )
        }
    }
}
