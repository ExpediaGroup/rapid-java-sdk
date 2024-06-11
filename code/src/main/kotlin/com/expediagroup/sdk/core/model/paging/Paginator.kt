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
package com.expediagroup.sdk.core.model.paging

import com.expediagroup.sdk.core.client.Client
import com.expediagroup.sdk.core.constant.HeaderKey.LINK
import com.expediagroup.sdk.core.constant.HeaderKey.PAGINATION_TOTAL_RESULTS
import com.expediagroup.sdk.core.model.Response
import io.ktor.client.statement.HttpResponse

sealed class BasePaginator<R, T>(
    private val client: Client,
    firstResponse: Response<T>,
    private val fallbackBody: T,
    private val getBody: suspend (HttpResponse) -> T
) : Iterator<R> {
    private var state: ResponseState<T> = DefaultResponseState(firstResponse)
    val paginationTotalResults: Long = firstResponse.headers[PAGINATION_TOTAL_RESULTS]?.getOrNull(0)?.toLongOrNull() ?: 0

    override fun hasNext(): Boolean = state.hasNext()

    private fun extractLink(headers: Map<String, List<String>>): String? {
        return headers[LINK]?.getOrNull(0)?.split(";")?.let {
            if (it.isNotEmpty()) it[0] else null
        }?.let {
            it.substring(it.indexOf("<") + 1, it.indexOf(">"))
        }
    }

    protected fun nextResponse(): Response<T> {
        val response = state.getNextResponse()
        state = ResponseStateFactory.getState(extractLink(response.headers), client, fallbackBody, getBody)
        return response
    }
}

/**
 * Paginator that returns the body of the response.
 *
 * @param client The client to use to fetch the next response
 * @param firstResponse The first response to start the paginator with
 * @param getBody A function to extract the body from the response
 */
class Paginator<T>(
    client: Client,
    firstResponse: Response<T>,
    fallbackBody: T,
    getBody: suspend (HttpResponse) -> T
) : BasePaginator<T, T>(client, firstResponse, fallbackBody, getBody) {
    /**
     * Returns the body of the next response.
     *
     * @throws NoSuchElementException if the iteration has no next element.
     */
    override fun next(): T = nextResponse().data
}

/**
 * Paginator that returns the full response.
 *
 * @param client The client to use to fetch the next response
 * @param firstResponse The first response to start the paginator with
 * @param getBody A function to extract the body from the response
 */
class ResponsePaginator<T>(
    client: Client,
    firstResponse: Response<T>,
    fallbackBody: T,
    getBody: suspend (HttpResponse) -> T
) : BasePaginator<Response<T>, T>(client, firstResponse, fallbackBody, getBody) {
    /**
     * Returns the next response.
     *
     * @throws NoSuchElementException if the iteration has no next element.
     */
    override fun next(): Response<T> = nextResponse()
}
