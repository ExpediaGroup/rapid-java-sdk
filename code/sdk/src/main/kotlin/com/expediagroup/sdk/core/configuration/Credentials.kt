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
package com.expediagroup.sdk.core.configuration

/**
 * A pair of key-secret.
 *
 * @property key the client key
 * @property secret the client secret
 */
internal data class Credentials(
    val key: String,
    val secret: String
) {
    /**
     * A factory of [Credentials].
     */
    companion object Factory {
        /**
         * Create a [Credentials] object.
         *
         * @param key Client key.
         * @param secret Client secret.
         * @return ClientCredentials object.
         */
        @JvmStatic
        fun from(
            key: String,
            secret: String
        ): Credentials = Credentials(key, secret)
    }
}
