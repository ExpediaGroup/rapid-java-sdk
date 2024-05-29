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
 * An individual bed group.
 * @param id Unique identifier for a bed group.
 * @param description This is a display ready description of a bed configuration for this room.
 * @param configuration An array of bed configurations for this room.
 */
data class BedGroup(
    // Unique identifier for a bed group.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // This is a display ready description of a bed configuration for this room.
    @JsonProperty("description")
    @field:Valid
    val description: kotlin.String? = null,
    // An array of bed configurations for this room.
    @JsonProperty("configuration")
    @field:Valid
    val configuration: kotlin.collections.List<BedGroupConfiguration>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var description: kotlin.String? = null,
        private var configuration: kotlin.collections.List<BedGroupConfiguration>? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun description(description: kotlin.String?) = apply { this.description = description }

        fun configuration(configuration: kotlin.collections.List<BedGroupConfiguration>?) = apply { this.configuration = configuration }

        fun build(): BedGroup {
            return BedGroup(
                id = id,
                description = description,
                configuration = configuration
            )
        }
    }
}
