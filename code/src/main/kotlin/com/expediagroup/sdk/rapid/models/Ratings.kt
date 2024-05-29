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
 * Various types of ratings for this property.
 * @param `property`
 * @param guest
 */
data class Ratings(
    @JsonProperty("property")
    @field:Valid
    val `property`: PropertyRating? = null,
    @JsonProperty("guest")
    @field:Valid
    val guest: GuestRating? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var `property`: PropertyRating? = null,
        private var guest: GuestRating? = null
    ) {
        fun `property`(`property`: PropertyRating?) = apply { this.`property` = `property` }

        fun guest(guest: GuestRating?) = apply { this.guest = guest }

        fun build(): Ratings {
            return Ratings(
                `property` = `property`,
                guest = guest
            )
        }
    }
}
