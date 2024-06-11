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
package com.expediagroup.sdk.core.model.exception.service

import io.ktor.http.HttpStatusCode

/**
 * An exception that is thrown when an authentication error occurs.
 *
 * @param message The error message.
 * @param cause The cause of the error.
 * @param transactionId The transaction-id of the auth request.
 */
class ExpediaGroupAuthException(
    message: String? = null,
    cause: Throwable? = null,
    transactionId: String? = null
) : ExpediaGroupServiceException(message, cause, transactionId) {
    /**
     * An exception that is thrown when an authentication error occurs.
     *
     * @param errorCode The HTTP status code of the error.
     * @param message The error message.
     */
    constructor(
        errorCode: HttpStatusCode,
        message: String,
        transactionId: String?
    ) : this(message = "[${errorCode.value}] $message", transactionId = transactionId)
}
