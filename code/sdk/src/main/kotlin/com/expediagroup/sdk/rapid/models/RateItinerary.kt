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
 * @param merchantOfRecord
 * @param refundable Indicates whether the itinerary is refundable or not.
 * @param cancelRefund
 * @param amenities
 * @param promotions
 * @param cancelPenalties The cancel penalties associated with the itinerary.
 * @param nonrefundableDateRanges A list of date exceptions. Dates within these ranges provide no refund on cancellation, regardless of cancel penalty windows. Nonrefundable range begins at 00:00:00 on the start date, and ends at 23:59:59 on the end date, in the local time zone of the property.
 * @param deposits
 * @param cardOnFileLimit
 * @param refundableDamageDeposit
 * @param pricing
 */
data class RateItinerary(
    // The id of the rate.
    @JsonProperty("id")
    @field:Valid
    val id: kotlin.String? = null,
    @JsonProperty("merchant_of_record")
    @field:Valid
    val merchantOfRecord: MerchantOfRecord? = null,
    // Indicates whether the itinerary is refundable or not.
    @JsonProperty("refundable")
    @field:Valid
    val refundable: kotlin.Boolean? = null,
    @JsonProperty("cancel_refund")
    @field:Valid
    val cancelRefund: CancelRefund? = null,
    @JsonProperty("amenities")
    @field:Valid
    val amenities: kotlin.collections.List<kotlin.String>? = null,
    @JsonProperty("promotions")
    @field:Valid
    val promotions: PromotionsItinerary? = null,
    // The cancel penalties associated with the itinerary.
    @JsonProperty("cancel_penalties")
    @field:Valid
    val cancelPenalties: kotlin.collections.List<CancelPenalty>? = null,
    // A list of date exceptions. Dates within these ranges provide no refund on cancellation, regardless of cancel penalty windows. Nonrefundable range begins at 00:00:00 on the start date, and ends at 23:59:59 on the end date, in the local time zone of the property.
    @JsonProperty("nonrefundable_date_ranges")
    @field:Valid
    val nonrefundableDateRanges: kotlin.collections.List<NonrefundableDateRange>? = null,
    @JsonProperty("deposits")
    @field:Valid
    val deposits: kotlin.collections.List<DepositItinerary>? = null,
    @JsonProperty("card_on_file_limit")
    @field:Valid
    val cardOnFileLimit: Amount? = null,
    @JsonProperty("refundable_damage_deposit")
    @field:Valid
    val refundableDamageDeposit: Amount? = null,
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
        private var merchantOfRecord: MerchantOfRecord? = null,
        private var refundable: kotlin.Boolean? = null,
        private var cancelRefund: CancelRefund? = null,
        private var amenities: kotlin.collections.List<kotlin.String>? = null,
        private var promotions: PromotionsItinerary? = null,
        private var cancelPenalties: kotlin.collections.List<CancelPenalty>? = null,
        private var nonrefundableDateRanges: kotlin.collections.List<NonrefundableDateRange>? = null,
        private var deposits: kotlin.collections.List<DepositItinerary>? = null,
        private var cardOnFileLimit: Amount? = null,
        private var refundableDamageDeposit: Amount? = null,
        private var pricing: PricingInformation? = null
    ) {
        fun id(id: kotlin.String?) = apply { this.id = id }

        fun merchantOfRecord(merchantOfRecord: MerchantOfRecord?) = apply { this.merchantOfRecord = merchantOfRecord }

        fun refundable(refundable: kotlin.Boolean?) = apply { this.refundable = refundable }

        fun cancelRefund(cancelRefund: CancelRefund?) = apply { this.cancelRefund = cancelRefund }

        fun amenities(amenities: kotlin.collections.List<kotlin.String>?) = apply { this.amenities = amenities }

        fun promotions(promotions: PromotionsItinerary?) = apply { this.promotions = promotions }

        fun cancelPenalties(cancelPenalties: kotlin.collections.List<CancelPenalty>?) = apply { this.cancelPenalties = cancelPenalties }

        fun nonrefundableDateRanges(nonrefundableDateRanges: kotlin.collections.List<NonrefundableDateRange>?) = apply { this.nonrefundableDateRanges = nonrefundableDateRanges }

        fun deposits(deposits: kotlin.collections.List<DepositItinerary>?) = apply { this.deposits = deposits }

        fun cardOnFileLimit(cardOnFileLimit: Amount?) = apply { this.cardOnFileLimit = cardOnFileLimit }

        fun refundableDamageDeposit(refundableDamageDeposit: Amount?) = apply { this.refundableDamageDeposit = refundableDamageDeposit }

        fun pricing(pricing: PricingInformation?) = apply { this.pricing = pricing }

        fun build(): RateItinerary {
            return RateItinerary(
                id = id,
                merchantOfRecord = merchantOfRecord,
                refundable = refundable,
                cancelRefund = cancelRefund,
                amenities = amenities,
                promotions = promotions,
                cancelPenalties = cancelPenalties,
                nonrefundableDateRanges = nonrefundableDateRanges,
                deposits = deposits,
                cardOnFileLimit = cardOnFileLimit,
                refundableDamageDeposit = refundableDamageDeposit,
                pricing = pricing
            )
        }
    }
}
