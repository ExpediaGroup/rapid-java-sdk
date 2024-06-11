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

import com.expediagroup.sdk.core.constant.LoggerName
import com.expediagroup.sdk.core.constant.provider.LoggingMessageProvider
import com.expediagroup.sdk.core.model.getTransactionId
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpSendPipeline
import io.ktor.http.content.OutputStreamContent
import io.ktor.util.AttributeKey
import io.ktor.util.pipeline.PipelineContext
import io.ktor.utils.io.writer
import kotlinx.coroutines.coroutineScope

internal class RequestBodyLogger {
    private val log = ExpediaGroupLoggerFactory.getLogger(javaClass)

    companion object Plugin : HttpClientPlugin<RequestBodyLoggerConfig, RequestBodyLogger> {
        override val key: AttributeKey<RequestBodyLogger> = AttributeKey(LoggerName.REQUEST_BODY_LOGGER)

        override fun install(
            plugin: RequestBodyLogger,
            scope: HttpClient
        ) {
            scope.sendPipeline.intercept(HttpSendPipeline.Monitoring) {
                val body: String = getBody()
                plugin.log.debug(LoggingMessageProvider.getRequestBodyMessage(body, context.headers.getTransactionId()))
                proceed()
            }
        }

        private suspend fun PipelineContext<Any, HttpRequestBuilder>.getBody(): String {
            val body = context.body
            if (body is OutputStreamContent) {
                return coroutineScope {
                    writer {
                        body.writeTo(channel)
                    }.channel.readRemaining().readText()
                }
            }
            return body.toString()
        }

        override fun prepare(block: RequestBodyLoggerConfig.() -> Unit): RequestBodyLogger {
            return RequestBodyLogger()
        }
    }
}

internal class RequestBodyLoggerConfig
