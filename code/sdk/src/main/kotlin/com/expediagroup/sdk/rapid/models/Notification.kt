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
 * A notification.
 * @param eventId Unique identifier for each message
 * @param eventType An indication of what event caused the notification. This value can be used for message handling and routing. Refer to the list of event types for more information.
 * @param eventTime Timestamp of the event notification, in UTC
 * @param itineraryId The Itinerary ID of the affected booking
 * @param email The customer e-mail address associated with the affected itinerary
 * @param message Description of event notification
 * @param affiliateReferenceId The Affiliate Reference ID of the affected booking
 */
data class Notification(
    // Unique identifier for each message
    @JsonProperty("event_id")
    @field:Valid
    val eventId: kotlin.String? = null,
    // An indication of what event caused the notification. This value can be used for message handling and routing. Refer to the list of event types for more information.
    @JsonProperty("event_type")
    @field:Valid
    val eventType: kotlin.String? = null,
    // Timestamp of the event notification, in UTC
    @JsonProperty("event_time")
    @field:Valid
    val eventTime: kotlin.String? = null,
    // The Itinerary ID of the affected booking
    @JsonProperty("itinerary_id")
    @field:Valid
    val itineraryId: kotlin.String? = null,
    // The customer e-mail address associated with the affected itinerary
    @JsonProperty("email")
    @field:Valid
    val email: kotlin.String? = null,
    // Description of event notification
    @JsonProperty("message")
    @field:Valid
    val message: kotlin.String? = null,
    // The Affiliate Reference ID of the affected booking
    @JsonProperty("affiliate_reference_id")
    @field:Valid
    val affiliateReferenceId: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var eventId: kotlin.String? = null,
        private var eventType: kotlin.String? = null,
        private var eventTime: kotlin.String? = null,
        private var itineraryId: kotlin.String? = null,
        private var email: kotlin.String? = null,
        private var message: kotlin.String? = null,
        private var affiliateReferenceId: kotlin.String? = null
    ) {
        fun eventId(eventId: kotlin.String?) = apply { this.eventId = eventId }

        fun eventType(eventType: kotlin.String?) = apply { this.eventType = eventType }

        fun eventTime(eventTime: kotlin.String?) = apply { this.eventTime = eventTime }

        fun itineraryId(itineraryId: kotlin.String?) = apply { this.itineraryId = itineraryId }

        fun email(email: kotlin.String?) = apply { this.email = email }

        fun message(message: kotlin.String?) = apply { this.message = message }

        fun affiliateReferenceId(affiliateReferenceId: kotlin.String?) = apply { this.affiliateReferenceId = affiliateReferenceId }

        fun build(): Notification {
            return Notification(
                eventId = eventId,
                eventType = eventType,
                eventTime = eventTime,
                itineraryId = itineraryId,
                email = email,
                message = message,
                affiliateReferenceId = affiliateReferenceId
            )
        }
    }
}
