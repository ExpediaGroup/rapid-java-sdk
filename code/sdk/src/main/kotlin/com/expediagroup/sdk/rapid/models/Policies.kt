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
 * Information about property policies that guests need to be aware of.
 * @param knowBeforeYouGo Description of information that may be helpful when planning a trip to this property.
 */
data class Policies(
    // Description of information that may be helpful when planning a trip to this property.
    @JsonProperty("know_before_you_go")
    @field:Valid
    val knowBeforeYouGo: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var knowBeforeYouGo: kotlin.String? = null
    ) {
        fun knowBeforeYouGo(knowBeforeYouGo: kotlin.String?) = apply { this.knowBeforeYouGo = knowBeforeYouGo }

        fun build(): Policies {
            return Policies(
                knowBeforeYouGo = knowBeforeYouGo
            )
        }
    }
}
