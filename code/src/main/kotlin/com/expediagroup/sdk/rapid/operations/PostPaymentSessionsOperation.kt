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
package com.expediagroup.sdk.rapid.operations

import com.expediagroup.sdk.core.model.Operation
import com.expediagroup.sdk.rapid.models.Link
import com.expediagroup.sdk.rapid.models.PaymentSessionsRequest

/**
 * Register Payments
 * @property requestBody [PaymentSessionsRequest]
 * @property params [PostPaymentSessionsOperationParams]
 */
class PostPaymentSessionsOperation private constructor(
    params: PostPaymentSessionsOperationParams?,
    requestBody: PaymentSessionsRequest?,
    link: Link?
) : Operation<
        PaymentSessionsRequest
    >(
        url(null, link, "/v3/payment-sessions"),
        "POST",
        "postPaymentSessions",
        requestBody,
        params
    ) {
    @Deprecated("Switch order of arguments", ReplaceWith("Operation(params: PostPaymentSessionsOperationParams, requestBody: PaymentSessionsRequest?)"))
    constructor(
        requestBody: PaymentSessionsRequest?,
        params: PostPaymentSessionsOperationParams
    ) : this(params, requestBody)

    constructor(
        params: PostPaymentSessionsOperationParams,
        requestBody: PaymentSessionsRequest?
    ) : this(
        params,
        requestBody,
        null
    )

    constructor(
        link: Link,
        context: PostPaymentSessionsOperationContext,
        requestBody: PaymentSessionsRequest?
    ) : this(
        PostPaymentSessionsOperationParams(context),
        requestBody,
        link
    )

    companion object : LinkableOperation {
        override fun pathPattern(): String {
            return "/v3/payment-sessions"
        }
    }
}
