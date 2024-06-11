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
package com.expediagroup.sdk.core.client

import com.expediagroup.sdk.core.constant.HeaderKey
import com.expediagroup.sdk.core.plugin.logging.ExpediaGroupLoggerFactory
import okhttp3.Call
import okhttp3.Connection
import okhttp3.EventListener
import okhttp3.Handshake
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy

object OkHttpEventListener : EventListener() {
    private val log = ExpediaGroupLoggerFactory.getLogger(this::class.java)

    fun Call.getTransactionId() = request().headers[HeaderKey.TRANSACTION_ID]

    override fun callStart(call: Call) {
        super.callStart(call)
        log.debug("Call start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun callEnd(call: Call) {
        super.callEnd(call)
        log.debug("Call end for transaction-id: [${call.getTransactionId()}]")
    }

    override fun callFailed(
        call: Call,
        ioe: IOException
    ) {
        super.callFailed(call, ioe)
        log.debug("Call failed for transaction-id: [${call.getTransactionId()}] with exception message: ${ioe.message}")
    }

    override fun canceled(call: Call) {
        super.canceled(call)
        log.debug("Call canceled for transaction-id: [${call.getTransactionId()}]")
    }

    override fun connectStart(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy
    ) {
        super.connectStart(call, inetSocketAddress, proxy)
        log.debug("Connect start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun connectEnd(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?
    ) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol)
        log.debug("Connect end for transaction-id: [${call.getTransactionId()}]")
    }

    override fun connectFailed(
        call: Call,
        inetSocketAddress: InetSocketAddress,
        proxy: Proxy,
        protocol: Protocol?,
        ioe: IOException
    ) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe)
        log.debug("Connect failed for transaction-id: [${call.getTransactionId()}] with exception message: ${ioe.message}")
    }

    override fun connectionAcquired(
        call: Call,
        connection: Connection
    ) {
        super.connectionAcquired(call, connection)
        log.debug("Connection acquired for transaction-id: [${call.getTransactionId()}]")
    }

    override fun connectionReleased(
        call: Call,
        connection: Connection
    ) {
        super.connectionReleased(call, connection)
        log.debug("Connection released for transaction-id: [${call.getTransactionId()}]")
    }

    override fun secureConnectStart(call: Call) {
        super.secureConnectStart(call)
        log.debug("Secure connect start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun secureConnectEnd(
        call: Call,
        handshake: Handshake?
    ) {
        super.secureConnectEnd(call, handshake)
        log.debug("Secure connect end for transaction-id: [${call.getTransactionId()}]")
    }

    override fun requestHeadersStart(call: Call) {
        super.requestHeadersStart(call)
        log.debug("Sending request headers start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun requestHeadersEnd(
        call: Call,
        request: Request
    ) {
        super.requestHeadersEnd(call, request)
        log.debug("Sending request headers end for transaction-id: [${call.getTransactionId()}]")
    }

    override fun requestBodyStart(call: Call) {
        super.requestBodyStart(call)
        log.debug("Sending request body start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun requestBodyEnd(
        call: Call,
        byteCount: Long
    ) {
        super.requestBodyEnd(call, byteCount)
        log.debug("Sending request body end for transaction-id: [${call.getTransactionId()}] with byte count: $byteCount")
    }

    override fun requestFailed(
        call: Call,
        ioe: IOException
    ) {
        super.requestFailed(call, ioe)
        log.debug("Request failed for transaction-id: [${call.getTransactionId()}] with exception message: ${ioe.message}")
    }

    override fun responseHeadersStart(call: Call) {
        super.responseHeadersStart(call)
        log.debug("Receiving response headers start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun responseHeadersEnd(
        call: Call,
        response: Response
    ) {
        super.responseHeadersEnd(call, response)
        log.debug("Receiving response headers end for transaction-id: [${call.getTransactionId()}]")
    }

    override fun responseBodyStart(call: Call) {
        super.responseBodyStart(call)
        log.debug("Receiving response body start for transaction-id: [${call.getTransactionId()}]")
    }

    override fun responseBodyEnd(
        call: Call,
        byteCount: Long
    ) {
        super.responseBodyEnd(call, byteCount)
        log.debug("Receiving response body end for transaction-id: [${call.getTransactionId()}] with byte count: $byteCount")
    }

    override fun responseFailed(
        call: Call,
        ioe: IOException
    ) {
        super.responseFailed(call, ioe)
        log.debug("Receiving response failed for transaction-id: [${call.getTransactionId()}] with exception message: ${ioe.message}")
    }
}
