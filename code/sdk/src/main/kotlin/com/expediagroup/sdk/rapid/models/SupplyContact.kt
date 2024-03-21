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
 * The supply contact information. Note that full details may not be displayed until a short time prior to checkin.
 * @param name The contact name.
 * @param phone
 * @param email Email address for the contact.
 * @param address
 */
data class SupplyContact(
    // The contact name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    @JsonProperty("phone")
    @field:Valid
    val phone: Phone? = null,
    // Email address for the contact.
    @JsonProperty("email")
    @field:Valid
    val email: kotlin.String? = null,
    @JsonProperty("address")
    @field:Valid
    val address: Address1? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var phone: Phone? = null,
        private var email: kotlin.String? = null,
        private var address: Address1? = null
    ) {
        fun name(name: kotlin.String?) = apply { this.name = name }

        fun phone(phone: Phone?) = apply { this.phone = phone }

        fun email(email: kotlin.String?) = apply { this.email = email }

        fun address(address: Address1?) = apply { this.address = address }

        fun build(): SupplyContact {
            return SupplyContact(
                name = name,
                phone = phone,
                email = email,
                address = address
            )
        }
    }
}
