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
 * The rate information associated with the itinerary.
 * @param id The id of the rate.
 * @param promotions
 * @param cancelPenalties The cancel penalties associated with the itinerary.
 * @param deposits
 * @param pricing
 */
data class RateHistory(
    // The id of the rate.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    @JsonProperty("promotions")
    @field:Valid
    val promotions: PromotionsItinerary? = null,
    // The cancel penalties associated with the itinerary.
    @JsonProperty("cancel_penalties")
    @field:Valid
    val cancelPenalties: kotlin.collections.List<CancelPenalty>? = null,
    @JsonProperty("deposits")
    @field:Valid
    val deposits: kotlin.collections.List<DepositItinerary>? = null,
    @JsonProperty("pricing")
    @field:Valid
    val pricing: PricingInformation? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var id: kotlin.String? = null,
        private var promotions: PromotionsItinerary? = null,
        private var cancelPenalties: kotlin.collections.List<CancelPenalty>? = null,
        private var deposits: kotlin.collections.List<DepositItinerary>? = null,
        private var pricing: PricingInformation? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun promotions(promotions: PromotionsItinerary?) = apply { this.promotions = promotions }

        fun cancelPenalties(cancelPenalties: kotlin.collections.List<CancelPenalty>?) = apply { this.cancelPenalties = cancelPenalties }

        fun deposits(deposits: kotlin.collections.List<DepositItinerary>?) = apply { this.deposits = deposits }

        fun pricing(pricing: PricingInformation?) = apply { this.pricing = pricing }

        fun build(): RateHistory {
            return RateHistory(
                id = id,
                promotions = promotions,
                cancelPenalties = cancelPenalties,
                deposits = deposits,
                pricing = pricing
            )
        }
    }
}
