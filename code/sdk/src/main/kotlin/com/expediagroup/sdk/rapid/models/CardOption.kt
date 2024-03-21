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
 * @param name Brand name for the accepted credit or debit card. Use this value to determine which cards to display on your checkout page.
 * @param processingCountry The country in which the payment will be processed.
 */
data class CardOption(
    // Brand name for the accepted credit or debit card. Use this value to determine which cards to display on your checkout page.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // The country in which the payment will be processed.
    @JsonProperty("processing_country")
    @field:Valid
    val processingCountry: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var processingCountry: kotlin.String? = null
    ) {
        fun name(name: kotlin.String?) = apply { this.name = name }

        fun processingCountry(processingCountry: kotlin.String?) = apply { this.processingCountry = processingCountry }

        fun build(): CardOption {
            return CardOption(
                name = name,
                processingCountry = processingCountry
            )
        }
    }
}
