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
 *
 * @param givenName First/given name of the payment type account holder.
 * @param familyName Last/family name of the payment type account holder.
 * @param address
 */
data class BillingContact(
    // First/given name of the payment type account holder.
    @JsonProperty("given_name")
    @field:Valid
    val givenName: kotlin.String? = null,
    // Last/family name of the payment type account holder.
    @JsonProperty("family_name")
    @field:Valid
    val familyName: kotlin.String? = null,
    @JsonProperty("address")
    @field:Valid
    val address: Address1? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var givenName: kotlin.String? = null,
        private var familyName: kotlin.String? = null,
        private var address: Address1? = null
    ) {
        fun givenName(givenName: kotlin.String?) = apply { this.givenName = givenName }

        fun familyName(familyName: kotlin.String?) = apply { this.familyName = familyName }

        fun address(address: Address1?) = apply { this.address = address }

        fun build(): BillingContact {
            return BillingContact(
                givenName = givenName,
                familyName = familyName,
                address = address
            )
        }
    }
}
