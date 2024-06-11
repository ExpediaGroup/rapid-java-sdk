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
 * Maximum occupancy counts.
 * @param total Total maximum occupancy allowed.
 * @param children Maximum number of children allowed.
 * @param adults Maximum number of adults allowed.
 */
data class MaxAllowed(
    // Total maximum occupancy allowed.
    @JsonProperty("total")
    @field:Valid
    val total: java.math.BigDecimal? = null,
    // Maximum number of children allowed.
    @JsonProperty("children")
    @field:Valid
    val children: java.math.BigDecimal? = null,
    // Maximum number of adults allowed.
    @JsonProperty("adults")
    @field:Valid
    val adults: java.math.BigDecimal? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var total: java.math.BigDecimal? = null,
        private var children: java.math.BigDecimal? = null,
        private var adults: java.math.BigDecimal? = null
    ) {
        fun total(total: java.math.BigDecimal?) = apply { this.total = total }

        fun children(children: java.math.BigDecimal?) = apply { this.children = children }

        fun adults(adults: java.math.BigDecimal?) = apply { this.adults = adults }

        fun build(): MaxAllowed {
            return MaxAllowed(
                total = total,
                children = children,
                adults = adults
            )
        }
    }
}
