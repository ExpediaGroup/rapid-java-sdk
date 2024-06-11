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
 * The confirmation ids.
 * @param expedia The expedia confirmation id.
 * @param `property` The property confirmation id.
 */
data class ConfirmationId(
    // The expedia confirmation id.
    @JsonProperty("expedia")
    @field:Valid
    val expedia: kotlin.String? = null,
    // The property confirmation id.
    @JsonProperty("property")
    @field:Valid
    val `property`: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var expedia: kotlin.String? = null,
        private var `property`: kotlin.String? = null
    ) {
        fun expedia(expedia: kotlin.String?) = apply { this.expedia = expedia }

        fun `property`(`property`: kotlin.String?) = apply { this.`property` = `property` }

        fun build(): ConfirmationId {
            return ConfirmationId(
                expedia = expedia,
                `property` = `property`
            )
        }
    }
}
