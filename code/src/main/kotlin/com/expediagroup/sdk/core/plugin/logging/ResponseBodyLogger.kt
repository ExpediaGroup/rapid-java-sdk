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
package com.expediagroup.sdk.core.plugin.logging

import com.expediagroup.sdk.core.constant.HeaderValue
import com.expediagroup.sdk.core.constant.LoggerName
import com.expediagroup.sdk.core.constant.provider.LoggingMessageProvider
import com.expediagroup.sdk.core.model.getTransactionId
import com.expediagroup.sdk.core.plugin.logging.GZipEncoder.decode
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.compression.ContentEncoder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.util.AttributeKey
import io.ktor.util.Encoder
import io.ktor.util.GZip
import io.ktor.util.InternalAPI
import io.ktor.utils.io.ByteReadChannel

class ResponseBodyLogger {
    private val log = ExpediaGroupLoggerFactory.getLogger(javaClass)

    companion object Plugin : HttpClientPlugin<ResponseBodyLoggerConfig, ResponseBodyLogger> {
        override val key: AttributeKey<ResponseBodyLogger> = AttributeKey(LoggerName.RESPONSE_BODY_LOGGER)

        @OptIn(InternalAPI::class)
        override fun install(
            plugin: ResponseBodyLogger,
            scope: HttpClient
        ) {
            scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
                val response: HttpResponse = context.response
                val byteReadChannel: ByteReadChannel = if (response.contentEncoding().equals(HeaderValue.GZIP)) scope.decode(response.content) else response.content
                val body: String = byteReadChannel.readRemaining().readText()
                plugin.log.debug(LoggingMessageProvider.getResponseBodyMessage(body, response.request.headers.getTransactionId()))
                proceed()
            }
        }

        override fun prepare(block: ResponseBodyLoggerConfig.() -> Unit): ResponseBodyLogger {
            return ResponseBodyLogger()
        }
    }
}

fun HttpResponse.contentEncoding(): String? = headers[HttpHeaders.ContentEncoding]

internal object GZipEncoder : ContentEncoder, Encoder by GZip {
    override val name: String = HeaderValue.GZIP
}

class ResponseBodyLoggerConfig
