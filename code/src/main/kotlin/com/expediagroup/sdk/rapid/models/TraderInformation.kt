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
 * The professional entity or entities that sells the property inventory or related services.
 * @param termsAndConditions The url linking to the full text terms and conditions.
 * @param traders An array of traders.
 */
data class TraderInformation(
    // The url linking to the full text terms and conditions.
    @JsonProperty("terms_and_conditions")
    @field:Valid
    val termsAndConditions: kotlin.String,
    // An array of traders.
    @JsonProperty("traders")
    @field:Valid
    val traders: kotlin.collections.List<TraderDetailsInner>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var termsAndConditions: kotlin.String? = null,
        private var traders: kotlin.collections.List<TraderDetailsInner>? = null
    ) {
        fun termsAndConditions(termsAndConditions: kotlin.String) = apply { this.termsAndConditions = termsAndConditions }

        fun traders(traders: kotlin.collections.List<TraderDetailsInner>?) = apply { this.traders = traders }

        fun build(): TraderInformation {
            // Check required params
            validateNullity()
            return TraderInformation(
                termsAndConditions = termsAndConditions!!,
                traders = traders
            )
        }

        private fun validateNullity() {
            if (termsAndConditions == null) {
                throw NullPointerException("Required parameter termsAndConditions is missing")
            }
        }
    }
}
