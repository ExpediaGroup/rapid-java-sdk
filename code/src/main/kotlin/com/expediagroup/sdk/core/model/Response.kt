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
@file:Suppress("unused")

package com.expediagroup.sdk.core.model

import java.util.stream.Collectors
import kotlin.collections.Map.Entry

/**
 * A Generic Response to represent the response from a service call.
 *
 * @property statusCode The HTTP status code of the response
 * @property data The body of the response
 * @property headers The headers of the response
 */
@Suppress("MemberVisibilityCanBePrivate")
open class Response<T>(
    val statusCode: Int,
    val data: T,
    val headers: Map<String, List<String>>
) {
    constructor(statusCode: Int, data: T, headers: Set<Entry<String, List<String>>>) : this(statusCode, data, toHeadersMap(headers))

    companion object {
        @JvmStatic
        fun toHeadersMap(headers: Set<Entry<String, List<String>>>): Map<String, List<String>> =
            headers.stream().collect(
                Collectors.toMap(
                    Entry<String, List<String>>::key,
                    Entry<String, List<String>>::value
                )
            )
    }

    override fun toString() = "Response(statusCode=$statusCode, data=$data, headers=$headers)"

    @Deprecated("Use getData() instead", replaceWith = ReplaceWith("getData()"))
    fun getBody() = data
}

class EmptyResponse(
    statusCode: Int,
    headers: Map<String, List<String>>
) : Response<Nothing>(statusCode, Nothing, headers) {
    constructor(statusCode: Int, headers: Set<Entry<String, List<String>>>) : this(statusCode, toHeadersMap(headers))

    override fun toString(): String = "EmptyResponse(statusCode=$statusCode, headers=$headers)"
}
