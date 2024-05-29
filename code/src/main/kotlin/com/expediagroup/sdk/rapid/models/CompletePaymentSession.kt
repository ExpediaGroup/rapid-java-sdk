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
 * The payment session response.
 * @param itineraryId The itinerary id.
 * @param links
 * @param traderInformation
 */
data class CompletePaymentSession(
    // The itinerary id.
    @JsonProperty("itinerary_id")
    @field:Valid
    val itineraryId: kotlin.String? = null,
    @JsonProperty("links")
    @field:Valid
    val links: CompletePaymentSessionLinks? = null,
    @JsonProperty("trader_information")
    @field:Valid
    val traderInformation: TraderInformation? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var itineraryId: kotlin.String? = null,
        private var links: CompletePaymentSessionLinks? = null,
        private var traderInformation: TraderInformation? = null
    ) {
        fun itineraryId(itineraryId: kotlin.String?) = apply { this.itineraryId = itineraryId }

        fun links(links: CompletePaymentSessionLinks?) = apply { this.links = links }

        fun traderInformation(traderInformation: TraderInformation?) = apply { this.traderInformation = traderInformation }

        fun build(): CompletePaymentSession {
            return CompletePaymentSession(
                itineraryId = itineraryId,
                links = links,
                traderInformation = traderInformation
            )
        }
    }
}
