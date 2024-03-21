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
 * The price check response.
 * @param status
 * @param occupancyPricing A map of room information by occupancy.
 * @param links
 * @param cardOnFileLimit
 * @param refundableDamageDeposit
 * @param deposits Array of deposits.
 * @param refund
 * @param amountOwed
 * @param penalty
 * @param traderInformation
 */
data class RoomPriceCheck(
    @JsonProperty("status")
    @field:Valid
    val status: StatusPriceCheck? = null,
    // A map of room information by occupancy.
    @JsonProperty("occupancy_pricing")
    @field:Valid
    val occupancyPricing: kotlin.collections.Map<kotlin.String, PricingInformation>? = null,
    @JsonProperty("links")
    @field:Valid
    val links: RoomPriceCheckLinks? = null,
    @JsonProperty("card_on_file_limit")
    @field:Valid
    val cardOnFileLimit: Amount? = null,
    @JsonProperty("refundable_damage_deposit")
    @field:Valid
    val refundableDamageDeposit: Amount? = null,
    // Array of deposits.
    @JsonProperty("deposits")
    @field:Valid
    val deposits: kotlin.collections.List<Deposit>? = null,
    @JsonProperty("refund")
    @field:Valid
    val refund: Charge? = null,
    @JsonProperty("amount_owed")
    @field:Valid
    val amountOwed: Charge? = null,
    @JsonProperty("penalty")
    @field:Valid
    val penalty: Charge? = null,
    @JsonProperty("trader_information")
    @field:Valid
    val traderInformation: TraderInformation? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var status: StatusPriceCheck? = null,
        private var occupancyPricing: kotlin.collections.Map<kotlin.String, PricingInformation>? = null,
        private var links: RoomPriceCheckLinks? = null,
        private var cardOnFileLimit: Amount? = null,
        private var refundableDamageDeposit: Amount? = null,
        private var deposits: kotlin.collections.List<Deposit>? = null,
        private var refund: Charge? = null,
        private var amountOwed: Charge? = null,
        private var penalty: Charge? = null,
        private var traderInformation: TraderInformation? = null
    ) {
        fun status(status: StatusPriceCheck?) = apply { this.status = status }

        fun occupancyPricing(occupancyPricing: kotlin.collections.Map<kotlin.String, PricingInformation>?) = apply { this.occupancyPricing = occupancyPricing }

        fun links(links: RoomPriceCheckLinks?) = apply { this.links = links }

        fun cardOnFileLimit(cardOnFileLimit: Amount?) = apply { this.cardOnFileLimit = cardOnFileLimit }

        fun refundableDamageDeposit(refundableDamageDeposit: Amount?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun deposits(deposits: kotlin.collections.List<Deposit>?) = apply { this.deposits = deposits }

        fun refund(refund: Charge?) = apply { this.refund = refund }

        fun amountOwed(amountOwed: Charge?) = apply { this.amountOwed = amountOwed }

        fun penalty(penalty: Charge?) = apply { this.penalty = penalty }

        fun traderInformation(traderInformation: TraderInformation?) = apply { this.traderInformation = traderInformation }

        fun build(): RoomPriceCheck {
            return RoomPriceCheck(
                status = status,
                occupancyPricing = occupancyPricing,
                links = links,
                cardOnFileLimit = cardOnFileLimit,
                refundableDamageDeposit = refundableDamageDeposit,
                deposits = deposits,
                refund = refund,
                amountOwed = amountOwed,
                penalty = penalty,
                traderInformation = traderInformation
            )
        }
    }
}
