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
package com.expediagroup.sdk.core.constant.provider

internal object LogMaskingRegexProvider {
    /**
     * Generates a regex pattern to match specified fields in a JSON string.
     *
     * @param maskedBodyFields the set of fields to be masked
     * @param valueToMatch regex pattern to match the value of the fields. Default: match any sequence of characters except double quotes.
     * @return the regex pattern to match the specified fields and their values
     */
    fun getMaskedFieldsRegex(
        maskedBodyFields: Set<String>,
        valueToMatch: String = "[^\\\"]+"
    ): Regex {
        val fields = maskedBodyFields.joinToString("|")
        // The pattern matches:
        // - The field name (one of the specified fields) captured in group 1.
        // - Optional backslash, closing double quotes, colon(:), optional whitespace, and opening double quotes.
        // - The value of the field, matching the specified valueToMatch pattern.
        // - Optional backslash, followed by a closing double quote
        return "\"($fields)(\\\\?\"\\s*:\\s*\\\\*\")$valueToMatch(?:\\\\?\")".toRegex()
    }

    /**
     * Generates a regex pattern to match a specified field in a JSON string.
     *
     * @param maskedBodyField the field to be masked
     * @param valueToMatch regex pattern to match the value of the field. Default: match any sequence of characters except double quotes.
     * @return the regex pattern to match the specified field and its value
     */
    fun getMaskedFieldsRegex(
        maskedBodyField: String,
        valueToMatch: String = "[^\\\"]+"
    ): Regex {
        val fields = setOf(maskedBodyField)
        return getMaskedFieldsRegex(fields, valueToMatch)
    }
}
