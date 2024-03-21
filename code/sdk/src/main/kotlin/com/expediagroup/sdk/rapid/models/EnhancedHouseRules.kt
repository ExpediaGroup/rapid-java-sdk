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
 * @param rule Describes the house rule.
 * @param additionalInformation List of strings detailing further information about the rule.
 */
data class EnhancedHouseRules(
    // Describes the house rule.
    @JsonProperty("rule")
    @field:Valid
    val rule: kotlin.String? = null,
    // List of strings detailing further information about the rule.
    @JsonProperty("additional_information")
    @field:Valid
    val additionalInformation: kotlin.collections.List<kotlin.String>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var rule: kotlin.String? = null,
        private var additionalInformation: kotlin.collections.List<kotlin.String>? = null
    ) {
        fun rule(rule: kotlin.String?) = apply { this.rule = rule }

        fun additionalInformation(additionalInformation: kotlin.collections.List<kotlin.String>?) = apply { this.additionalInformation = additionalInformation }

        fun build(): EnhancedHouseRules {
            return EnhancedHouseRules(
                rule = rule,
                additionalInformation = additionalInformation
            )
        }
    }
}
