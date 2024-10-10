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
package com.expediagroup.sdk.generators.openapi

import com.expediagroup.sdk.model.ClientGenerationException
import com.expediagroup.sdk.product.Product
import com.expediagroup.sdk.product.ProgrammingLanguage
import com.github.rvesse.airline.SingleCommand
import com.github.rvesse.airline.annotations.Command
import com.github.rvesse.airline.annotations.Option
import org.openapitools.codegen.CodegenConstants
import org.openapitools.codegen.DefaultGenerator
import org.openapitools.codegen.SupportingFile
import org.openapitools.codegen.api.TemplateDefinition
import org.openapitools.codegen.api.TemplateFileType
import org.openapitools.codegen.config.CodegenConfigurator

/**
 * Configures the OpenAPI Generator based on command line parameters to generate an EG Travel SDK project
 * This will produce a maven project in the specified output directory
 */
@Command(name = "generate", description = "Let's build an EG Travel SDK!")
class OpenApiSdkGenerator {
    private val supportingFiles =
        mutableListOf(
            "pom.xml",
            "README.md",
            "ApiException.kt",
            "LinkableOperation.kt",
            "PropertyConstraintViolation.kt",
            "PropertyConstraintsValidator.kt",
            "PropertyConstraintViolationException.kt"
        )

    companion object {
        /**
         * Main Entry Point
         *
         * Parses command line arguments and then generates an EG Travel SDK
         */
        @JvmStatic
        fun main(args: Array<String>) {
            val generator = SingleCommand.singleCommand(OpenApiSdkGenerator::class.java).parse(*args)
            generator.run()
        }
    }

    @Option(name = ["-i", "--input-spec"])
    lateinit var inputFile: String

    @Option(name = ["-o", "--output-directory"])
    lateinit var outputDirectory: String

    @Option(name = ["-n", "--namespace"])
    lateinit var namespace: String

    @Option(name = ["-v", "--version"])
    lateinit var version: String

    @Option(name = ["-l", "--language"])
    lateinit var programmingLanguage: String

    @Option(name = ["-t", "--templates-dir"])
    lateinit var templateDir: String

    @Option(name = ["-r", "--repo-name"])
    lateinit var repoName: String

    fun run() {
        try {
            val product = Product(namespace, repoName, programmingLanguage)
            val config =
                CodegenConfigurator().apply {
                    setGeneratorName("kotlin")
                    setTemplateDir(templateDir)
                    setInputSpec(inputFile)
                    setOutputDir(outputDirectory)
                    setArtifactId(product.artifactId)
                    setArtifactVersion(version)
                    setGroupId(product.groupId)
                    setPackageName(product.packageName)
                    setEnablePostProcessFile(true)

                    addGlobalProperty(CodegenConstants.APIS, "")
                    addGlobalProperty(CodegenConstants.API_DOCS, "false")
                    addGlobalProperty(CodegenConstants.MODELS, "")
                    addGlobalProperty(CodegenConstants.MODEL_DOCS, "false")

                    supportingFiles.add("${namespace.pascalCase()}Client.kt")
                    addGlobalProperty(CodegenConstants.SUPPORTING_FILES, supportingFiles.joinToString(","))
                    // addGlobalProperty("debugSupportingFiles", "")

                    addAdditionalProperty(CodegenConstants.API_SUFFIX, "Operation")
                    addAdditionalProperty(CodegenConstants.API_PACKAGE, product.apiPackage)
                    addAdditionalProperty(CodegenConstants.ENUM_PROPERTY_NAMING, "UPPERCASE")
                    addAdditionalProperty(CodegenConstants.LIBRARY, "jvm-ktor")
                    addAdditionalProperty(CodegenConstants.SERIALIZATION_LIBRARY, "jackson")
                    addAdditionalProperty(CodegenConstants.SORT_PARAMS_BY_REQUIRED_FLAG, true)

                    // Template specific properties
                    addAdditionalProperty("shadePrefix", product.shadePrefix)
                    addAdditionalProperty("namespace", product.namespace)
                    addAdditionalProperty("clientClassname", namespace.pascalCase())
                    addAdditionalProperty("language", product.programmingLanguage.id)
                    addAdditionalProperty("repoName", product.repoName)
                    addAdditionalProperty("isKotlin", ProgrammingLanguage.isKotlin(product.programmingLanguage))

                    // Mustache Helpers
                    mustacheHelpers.forEach { (name, function) -> addAdditionalProperty(name, function()) }
                    rapidHelpers.forEach { (name, function) -> addAdditionalProperty(name, function()) }
                }

            val generatorInput =
                config.toClientOptInput().apply {
                    val packagePath = product.packagePath

                    userDefinedTemplates(
                        buildList {
                            add(
                                SupportingFile(
                                    "client.mustache",
                                    "$packagePath/client/",
                                    "${namespace.pascalCase()}Client.kt"
                                )
                            )
                            add(SupportingFile("pom.mustache", "pom.xml"))
                            add(SupportingFile("README.mustache", "README.md"))
                            add(
                                SupportingFile(
                                    "models/apiException.mustache",
                                    "$packagePath/models/exception/",
                                    "ApiException.kt"
                                )
                            )
                            add(
                                SupportingFile(
                                    "validation/propertyConstraintViolationException.mustache",
                                    "$packagePath/models/exception/",
                                    "PropertyConstraintViolationException.kt"
                                )
                            )
                            add(
                                SupportingFile(
                                    "validation/propertyConstraintViolation.mustache",
                                    "$packagePath/models/exception/",
                                    "PropertyConstraintViolation.kt"
                                )
                            )
                            add(
                                SupportingFile(
                                    "validation/propertyConstraintsValidator.mustache",
                                    "$packagePath/validation/",
                                    "PropertyConstraintsValidator.kt"
                                )
                            )

                            add(
                                TemplateDefinition(
                                    "operation_params.mustache",
                                    "Params.kt"
                                ).also { it.templateType = TemplateFileType.API }
                            )

                            add(
                                TemplateDefinition(
                                    "domains/rapid/operation_context.mustache",
                                    "Context.kt"
                                ).also { it.templateType = TemplateFileType.API }
                            )

                            add(
                                TemplateDefinition(
                                    "domains/rapid/typed_link.mustache",
                                    "Link.kt"
                                ).also { it.templateType = TemplateFileType.API }
                            )

                            add(
                                SupportingFile(
                                    "domains/rapid/linkable_operation.mustache",
                                    "$packagePath/operations/",
                                    "LinkableOperation.kt"
                                )
                            )
                        }
                    )
                }

            val generator = DefaultGenerator(false).apply { opts(generatorInput) }
            generator.generate()
        } catch (e: Exception) {
            throw ClientGenerationException("Failed to generate SDK", e)
        }
    }
}
