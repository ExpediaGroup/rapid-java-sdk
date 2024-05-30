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
 * An itinerary history change event, representing a charge or refund made to the itinerary and not a specific room.
 * @param historyId Itinerary history id for particular change.
 * @param eventTimestamp Date and time in UTC of the change event, in extended ISO 8601 format.
 * @param eventType
 * @param amount
 * @param agentId An agent user id number associated with a modification.
 */
data class ItineraryHistoryItem(
    // Itinerary history id for particular change.
    @JsonProperty("history_id")
    @field:Valid
    val historyId: kotlin.String? = null,
    // Date and time in UTC of the change event, in extended ISO 8601 format.
    @JsonProperty("event_timestamp")
    @field:Valid
    val eventTimestamp: kotlin.String? = null,
    @JsonProperty("event_type")
    val eventType: ItineraryHistoryItem.EventType? = null,
    @JsonProperty("amount")
    @field:Valid
    val amount: Amount? = null,
    // An agent user id number associated with a modification.
    @JsonProperty("agent_id")
    @field:Valid
    val agentId: kotlin.Int? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var historyId: kotlin.String? = null,
        private var eventTimestamp: kotlin.String? = null,
        private var eventType: ItineraryHistoryItem.EventType? = null,
        private var amount: Amount? = null,
        private var agentId: kotlin.Int? = null
    ) {
        fun historyId(historyId: kotlin.String?) = apply { this.historyId = historyId }

        fun eventTimestamp(eventTimestamp: kotlin.String?) = apply { this.eventTimestamp = eventTimestamp }

        fun eventType(eventType: ItineraryHistoryItem.EventType?) = apply { this.eventType = eventType }

        fun amount(amount: Amount?) = apply { this.amount = amount }

        fun agentId(agentId: kotlin.Int?) = apply { this.agentId = agentId }

        fun build(): ItineraryHistoryItem {
            return ItineraryHistoryItem(
                historyId = historyId,
                eventTimestamp = eventTimestamp,
                eventType = eventType,
                amount = amount,
                agentId = agentId
            )
        }
    }

    /**
     *
     * Values: ADJUSTMENT,COUPON
     */
    enum class EventType(val value: kotlin.String) {
        @JsonProperty("adjustment")
        ADJUSTMENT("adjustment"),

        @JsonProperty("coupon")
        COUPON("coupon")
    }
}
