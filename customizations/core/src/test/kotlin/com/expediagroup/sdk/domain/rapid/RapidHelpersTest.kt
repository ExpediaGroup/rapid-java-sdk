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
package com.expediagroup.sdk.domain.rapid

import com.expediagroup.sdk.core.client.BaseRapidClient
import com.expediagroup.sdk.core.configuration.RapidClientConfiguration
import com.expediagroup.sdk.core.model.Response
import io.ktor.client.statement.HttpResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RapidHelpersTest {
    companion object {
        private val rapidClient =
            object : BaseRapidClient("dummy", RapidClientConfiguration()) {
                override suspend fun throwServiceException(
                    response: HttpResponse,
                    operationId: String
                ) {
                    throw UnsupportedOperationException()
                }
            }

        val rapidHelpers = RapidHelpers(rapidClient)
    }

    @Nested
    inner class TestExtractToken {
        @Test
        fun `Verify extractToken returns token when present`() {
            assertEquals("12345", rapidHelpers.extractToken("https://www.example.com?token=12345"))
        }

        @Test
        fun `Verify extractToken returns null when no token is present`() {
            assertNull(rapidHelpers.extractToken("https://www.example.com"))
        }

        @Test
        fun `Verify extractToken returns an empty string when token is empty`() {
            assertEquals("", rapidHelpers.extractToken("https://www.example.com?token="))
        }

        @Test
        fun `Verify extractToken returns token when it is not the first parameter`() {
            assertEquals("12345", rapidHelpers.extractToken("https://www.example.com?foo=bar&token=12345"))
        }

        @Test
        fun `Verify extractToken returns null when token is not provided but other parameters are`() {
            assertNull(rapidHelpers.extractToken("https://www.example.com?foo=bar"))
        }

        @Test
        fun `Verify extractToken returns null when token is not provided but multiple other parameters are`() {
            assertNull(rapidHelpers.extractToken("https://www.example.com?foo=bar&baz=qux"))
        }

        @Test
        fun `Verify extractToken returns it when token is not the last parameter`() {
            assertEquals("12345", rapidHelpers.extractToken("https://www.example.com?token=12345&foo=bar"))
        }

        @Test
        fun `extractToken should handle multiple parameters and return the correct token`() {
            assertEquals("xyz456", rapidHelpers.extractToken("https://example.com/page?param1=value1&token=xyz456&param2=value2"))
        }

        @Test
        fun `extractToken should handle URL-encoded characters in the token`() {
            assertEquals("abc%20456", rapidHelpers.extractToken("https://example.com/page?token=abc%20456&param=value"))
        }

        @Test
        fun `extractToken should handle different token parameter names`() {
            assertEquals("abcd1234", rapidHelpers.extractToken("https://example.com/page?access_token=abcd1234"))
            assertEquals("efgh5678", rapidHelpers.extractToken("https://example.com/page?api_token=efgh5678"))
        }

        @Test
        fun `extractToken should handle multiple tokens`() {
            assertEquals("abcd1234", rapidHelpers.extractToken("https://example.com/page?token=abcd1234&token=efgh5678"))
            assertEquals("efgh5678", rapidHelpers.extractToken("https://example.com/page?api_token=efgh5678&access_token=abcd1234"))
        }

        @Test
        fun `extractToken should get only the query param token`() {
            assertNull(rapidHelpers.extractToken("https://example.com/tokenPage?query=tokenValue"))
        }

        @Test
        fun `extractToken should return an empty string when token is empty and other parameters are present`() {
            assertEquals("", rapidHelpers.extractToken("https://www.example.com?foo=bar&token="))
        }

        @Test
        fun `extractToken should return an empty string when token is empty in the middle of other parameters`() {
            assertEquals("", rapidHelpers.extractToken("https://www.example.com?foo=bar&token=&baz=qux"))
        }
    }

    @Nested
    inner class TestExtractRoomBookingId {
        @Test
        fun `Verify extractRoomBookingId returns roomId when present`() {
            assertEquals("abc-123-xyz", rapidHelpers.extractRoomBookingId("https://www.example.com/rooms/abc-123-xyz?token=abc"))
        }

        @Test
        fun `Verify extractRoomBookingId returns null when roomId is not present`() {
            assertNull(rapidHelpers.extractRoomBookingId("https://www.example.com?token=abc"))
        }
    }

    @Nested
    inner class TestExtractTransactionId {
        @Test
        fun `Verify extractTransactionId returns transactionId when present`() {
            Response<String>(
                200,
                "body",
                mapOf("transaction-id" to listOf("abc-123-xyz"))
            ).let { assertEquals("abc-123-xyz", rapidHelpers.extractTransactionId(it)) }
        }

        @Test
        fun `Verify extractTransactionId returns null when transactionId is not present`() {
            Response<String>(
                200,
                "body",
                mapOf("some-header" to listOf("some data"))
            ).let { assertNull(rapidHelpers.extractTransactionId(it)) }
        }
    }
}
