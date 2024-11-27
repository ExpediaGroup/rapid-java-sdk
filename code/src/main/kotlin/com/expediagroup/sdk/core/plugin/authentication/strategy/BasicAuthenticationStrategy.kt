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
package com.expediagroup.sdk.core.plugin.authentication.strategy

import com.expediagroup.sdk.core.plugin.authentication.AuthenticationConfiguration
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.request.HttpRequestBuilder

internal class BasicAuthenticationStrategy(
    private val configs: AuthenticationConfiguration
) : AuthenticationStrategy {
    override fun loadAuth(auth: Auth) {
        auth.basic {
            sendWithoutRequest { true }
            credentials {
                BasicAuthCredentials(username = configs.credentials.key, password = configs.credentials.secret)
            }
        }
    }

    override fun isTokenAboutToExpire(): Boolean = false

    override fun renewToken() = Unit

    override fun isIdentityRequest(request: HttpRequestBuilder) = true

    override fun getAuthorizationHeader() = String()
}
