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
 * A map of links, including links to continue booking this rate or to shop for additional rates.  If this rate is still available for booking then a book link will be present if PSD2 is not a requirement for you or a payment_session link will be present if PSD2 is a requirement for you.
 * @param book
 * @param commit
 * @param paymentSession
 * @param additionalRates
 */
data class RoomPriceCheckLinks(
    @JsonProperty("book")
    @field:Valid
    val book: Link? = null,
    @JsonProperty("commit")
    @field:Valid
    val commit: Link? = null,
    @JsonProperty("payment_session")
    @field:Valid
    val paymentSession: Link? = null,
    @JsonProperty("additional_rates")
    @field:Valid
    val additionalRates: Link? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var book: Link? = null,
        private var commit: Link? = null,
        private var paymentSession: Link? = null,
        private var additionalRates: Link? = null
    ) {
        fun book(book: Link?) = apply { this.book = book }

        fun commit(commit: Link?) = apply { this.commit = commit }

        fun paymentSession(paymentSession: Link?) = apply { this.paymentSession = paymentSession }

        fun additionalRates(additionalRates: Link?) = apply { this.additionalRates = additionalRates }

        fun build(): RoomPriceCheckLinks {
            return RoomPriceCheckLinks(
                book = book,
                commit = commit,
                paymentSession = paymentSession,
                additionalRates = additionalRates
            )
        }
    }
}
