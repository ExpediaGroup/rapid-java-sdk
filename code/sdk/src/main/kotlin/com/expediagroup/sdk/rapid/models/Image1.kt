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
 * @param url The url of the image.
 * @param width The width of the image.
 * @param height The height of the image.
 */
data class Image1(
    // The url of the image.
    @JsonProperty("url")
    @field:Valid
    val url: kotlin.String? = null,
    // The width of the image.
    @JsonProperty("width")
    @field:Valid
    val width: kotlin.String? = null,
    // The height of the image.
    @JsonProperty("height")
    @field:Valid
    val height: kotlin.String? = null
) {
    companion object {
        @JvmStatic
        fun builder() = Builder()
    }

    class Builder(
        private var url: kotlin.String? = null,
        private var width: kotlin.String? = null,
        private var height: kotlin.String? = null
    ) {
        fun url(url: kotlin.String?) = apply { this.url = url }

        fun width(width: kotlin.String?) = apply { this.width = width }

        fun height(height: kotlin.String?) = apply { this.height = height }

        fun build(): Image1 {
            return Image1(
                url = url,
                width = width,
                height = height
            )
        }
    }
}
