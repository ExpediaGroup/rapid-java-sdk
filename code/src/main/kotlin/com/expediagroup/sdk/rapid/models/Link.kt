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
 * An individual link.
 * @param method The request method used to access the link.
 * @param href The URL for the link. This can be absolute or relative.
 * @param expires If the link expires, this will be the UTC date the link will expire, in ISO 8601 format.
 */
data class Link(
    // The request method used to access the link.
    @JsonProperty("method")
    @field:Valid
    val method: kotlin.String? = null,
    // The URL for the link. This can be absolute or relative.
    @JsonProperty("href")
    @field:Valid
    val href: kotlin.String? = null,
    // If the link expires, this will be the UTC date the link will expire, in ISO 8601 format.
    @JsonProperty("expires")
    @field:Valid
    val expires: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var method: kotlin.String? = null,
        private var href: kotlin.String? = null,
        private var expires: kotlin.String? = null
    ) {
        fun method(method: kotlin.String?) = apply { this.method = method }

        fun href(href: kotlin.String?) = apply { this.href = href }

        fun expires(expires: kotlin.String?) = apply { this.expires = expires }

        fun build(): Link {
            return Link(
                method = method,
                href = href,
                expires = expires
            )
        }
    }
}
