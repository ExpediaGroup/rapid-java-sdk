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
 * An age category.
 * @param name Age category name.
 * @param minimumAge Age category minimum age.
 */
data class CategoryAge(
    // Age category name.
    @JsonProperty("name")
    @field:Valid
    val name: kotlin.String? = null,
    // Age category minimum age.
    @JsonProperty("minimum_age")
    @field:Valid
    val minimumAge: java.math.BigDecimal? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var name: kotlin.String? = null,
        private var minimumAge: java.math.BigDecimal? = null
    ) {
        fun name(name: kotlin.String?) = apply { this.name = name }

        fun minimumAge(minimumAge: java.math.BigDecimal?) = apply { this.minimumAge = minimumAge }

        fun build(): CategoryAge {
            return CategoryAge(
                name = name,
                minimumAge = minimumAge
            )
        }
    }
}
