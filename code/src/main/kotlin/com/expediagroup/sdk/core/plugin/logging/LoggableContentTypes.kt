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

import io.ktor.http.ContentType

val LoggableContentTypes: List<ContentType> =
    buildList {
        add(ContentType.Application.Json)
        add(ContentType.Application.Xml)
        add(ContentType.Application.Xml_Dtd)
        add(ContentType.Application.ProblemXml)
        add(ContentType.Application.Json)
        add(ContentType.Application.HalJson)
        add(ContentType.Application.FormUrlEncoded)
        add(ContentType.Application.ProblemJson)
        add(ContentType.Text.Xml)
        add(ContentType.Text.Plain)
    }
