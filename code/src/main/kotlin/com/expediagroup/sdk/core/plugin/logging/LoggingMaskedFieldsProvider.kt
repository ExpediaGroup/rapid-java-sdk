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

import com.expediagroup.sdk.core.constant.LogMaskingFields.DEFAULT_MASKED_BODY_FIELDS
import com.expediagroup.sdk.core.constant.LogMaskingFields.DEFAULT_MASKED_HEADER_FIELDS
import com.expediagroup.sdk.core.constant.LogMaskingRegex.FIELD_REGEX
import com.expediagroup.sdk.core.model.exception.client.ExpediaGroupInvalidFieldNameException

class LoggingMaskedFieldsProvider(maskedLoggingHeaders: Set<String>) {
    private val maskedHeaderFields: Set<String>
    private val maskedBodyFields: Set<String>

    init {
        maskedLoggingHeaders.filter(::isInvalid).takeIf { it.isNotEmpty() }?.let { throw ExpediaGroupInvalidFieldNameException(it) }
        maskedHeaderFields = DEFAULT_MASKED_HEADER_FIELDS.union(maskedLoggingHeaders)
        maskedBodyFields = DEFAULT_MASKED_BODY_FIELDS
    }

    /**
     * @return a copy of the list of headers to be masked
     */
    fun getMaskedHeaderFields(): Set<String> = maskedHeaderFields.toSet()

    /**
     * @return a copy of the list of body fields to be masked
     */
    fun getMaskedBodyFields(): Set<String> = maskedBodyFields.toSet()

    private fun isInvalid(fieldName: String): Boolean = !fieldName.matches(FIELD_REGEX)
}
