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
package com.expediagroup.sdk.core.model.exception

import com.expediagroup.sdk.core.constant.provider.ExceptionMessageProvider
import com.expediagroup.sdk.core.model.exception.service.ExpediaGroupServiceException

/** Handles exceptions by ensuring that only instances of [ExpediaGroupException] are thrown. */
fun Throwable.handle(): Nothing = handleWith(null)

/**
 * Handles exceptions by ensuring that only instances of [ExpediaGroupException] are thrown.
 *
 * @param transactionId the transaction ID to be included in the exception message, can be null.
 */
fun Throwable.handleWith(transactionId: String?): Nothing {
    if (this is ExpediaGroupException) throw this

    when (val cause = this.cause) {
        is ExpediaGroupException -> throw cause
        else -> throw ExpediaGroupServiceException(
            ExceptionMessageProvider.getExceptionOccurredWithTransactionIdMessage(transactionId, message),
            this,
            transactionId
        )
    }
}
