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
package com.expediagroup.sdk.core.model

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/** A model of "*.properties" file with some handy methods. */
class Properties(private val data: Map<String, String>) {
    companion object {
        /** Creates a new SdkProperties with the given data. */
        fun from(path: URL) =
            Properties(
                java.util.Properties().apply {
                    load(BufferedReader(InputStreamReader(path.openStream())))
                }.map { it.key.toString() to it.value.toString() }.toMap()
            )
    }

    /** Returns the data for a given [key]. */
    operator fun get(key: String): String? = data[key]
}
