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
 * @param changeReferenceId Your optional identifier for the change being executed. Only unique per itinerary. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
 * @param payments
 */
data class CommitChangeRoomRequestBody(
    // Your optional identifier for the change being executed. Only unique per itinerary. Special characters (\"<\", \">\", \"(\", \")\", and \"&\") entered in this field will be re-encoded.
    @JsonProperty("change_reference_id")
    @field:Valid
    val changeReferenceId: kotlin.String? = null,
    @JsonProperty("payments")
    @field:Valid
    val payments: kotlin.collections.List<PaymentRequestWithPhone>? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var changeReferenceId: kotlin.String? = null,
        private var payments: kotlin.collections.List<PaymentRequestWithPhone>? = null
    ) {
        fun changeReferenceId(changeReferenceId: kotlin.String?) = apply { this.changeReferenceId = changeReferenceId }

        fun payments(payments: kotlin.collections.List<PaymentRequestWithPhone>?) = apply { this.payments = payments }

        fun build(): CommitChangeRoomRequestBody {
            return CommitChangeRoomRequestBody(
                changeReferenceId = changeReferenceId,
                payments = payments
            )
        }
    }
}
