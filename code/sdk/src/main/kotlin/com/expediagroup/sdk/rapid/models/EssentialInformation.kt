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
 * Essential information, including the supply contact information and any other essential information.
 * @param contact
 * @param essentials
 * @param updateAvailableDate The date and time when new essential information is available for retrieval, in extended ISO 8601 format, with ±hh:mm timezone offset.
 */
data class EssentialInformation(
    @JsonProperty("contact")
    @field:Valid
    val contact: SupplyContact? = null,
    @JsonProperty("essentials")
    @field:Valid
    val essentials: kotlin.collections.List<Essential>? = null,
    // The date and time when new essential information is available for retrieval, in extended ISO 8601 format, with ±hh:mm timezone offset.
    @JsonProperty("update_available_date")
    @field:Valid
    val updateAvailableDate: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var contact: SupplyContact? = null,
        private var essentials: kotlin.collections.List<Essential>? = null,
        private var updateAvailableDate: kotlin.String? = null
    ) {
        fun contact(contact: SupplyContact?) = apply { this.contact = contact }

        fun essentials(essentials: kotlin.collections.List<Essential>?) = apply { this.essentials = essentials }

        fun updateAvailableDate(updateAvailableDate: kotlin.String?) = apply { this.updateAvailableDate = updateAvailableDate }

        fun build(): EssentialInformation {
            return EssentialInformation(
                contact = contact,
                essentials = essentials,
                updateAvailableDate = updateAvailableDate
            )
        }
    }
}
