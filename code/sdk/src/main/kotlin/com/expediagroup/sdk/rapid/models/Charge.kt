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
 * An object representing a charge. Information about the charge is provided in both the billable currency and the request currency.
 * @param billableCurrency
 * @param requestCurrency
 */
data class Charge(
    @JsonProperty("billable_currency")
    @field:Valid
    val billableCurrency: Amount? = null,
    @JsonProperty("request_currency")
    @field:Valid
    val requestCurrency: Amount? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var billableCurrency: Amount? = null,
        private var requestCurrency: Amount? = null
    ) {
        fun billableCurrency(billableCurrency: Amount?) = apply { this.billableCurrency = billableCurrency }

        fun requestCurrency(requestCurrency: Amount?) = apply { this.requestCurrency = requestCurrency }

        fun build(): Charge {
            return Charge(
                billableCurrency = billableCurrency,
                requestCurrency = requestCurrency
            )
        }
    }
}
