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
 * An individual statistic.
 * @param id The statistic definition ID for this statistic.
 * @param name Statistic name.
 * @param `value` Statistic value.
 */
data class Statistic(
    // The statistic definition ID for this statistic.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    // Statistic name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // Statistic value.
    @JsonProperty("value")
    @field:Valid
    val `value`: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var name: kotlin.String? = null,
        private var `value`: kotlin.String? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun name(name: kotlin.String?) = apply { this.name = name }

        fun `value`(`value`: kotlin.String?) = apply { this.`value` = `value` }

        fun build(): Statistic {
            return Statistic(
                id = id,
                name = name,
                `value` = `value`
            )
        }
    }
}
