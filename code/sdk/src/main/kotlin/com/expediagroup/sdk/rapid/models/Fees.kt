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
 * Information related to a property's fees.
 * @param mandatory Describes resort fees and other mandatory taxes or charges. May describe which services are covered by any fees, such as fitness centers or internet access.
 * @param optional Describes additional optional fees for items such as breakfast, wifi, parking, pets etc.
 */
data class Fees(
    // Describes resort fees and other mandatory taxes or charges. May describe which services are covered by any fees, such as fitness centers or internet access.
    @JsonProperty("mandatory")
    @field:Valid
    val mandatory: kotlin.String? = null,
    // Describes additional optional fees for items such as breakfast, wifi, parking, pets etc.
    @JsonProperty("optional")
    @field:Valid
    val optional: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var mandatory: kotlin.String? = null,
        private var optional: kotlin.String? = null
    ) {
        fun mandatory(mandatory: kotlin.String?) = apply { this.mandatory = mandatory }

        fun optional(optional: kotlin.String?) = apply { this.optional = optional }

        fun build(): Fees {
            return Fees(
                mandatory = mandatory,
                optional = optional
            )
        }
    }
}
