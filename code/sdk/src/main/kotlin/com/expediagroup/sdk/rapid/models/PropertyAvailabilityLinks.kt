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
 * A map of links, including links to request additional rates. Note that the recommendations feature has been retired and will return an error if the link is followed.
 * @param additionalRates
 * @param recommendations
 */
data class PropertyAvailabilityLinks(
    @JsonProperty("additional_rates")
    @field:Valid
    val additionalRates: Link? = null,
    @JsonProperty("recommendations")
    @field:Valid
    val recommendations: Link? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var additionalRates: Link? = null,
        private var recommendations: Link? = null
    ) {
        fun additionalRates(additionalRates: Link?) = apply { this.additionalRates = additionalRates }

        fun recommendations(recommendations: Link?) = apply { this.recommendations = recommendations }

        fun build(): PropertyAvailabilityLinks {
            return PropertyAvailabilityLinks(
                additionalRates = additionalRates,
                recommendations = recommendations
            )
        }
    }
}
