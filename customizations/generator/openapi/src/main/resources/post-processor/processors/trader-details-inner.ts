import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process (filePath: PathOrFileDescriptor): void {
  const source = fs.readFileSync(filePath, 'utf-8')
  const ast = parse(Lang.Kotlin, source)
  const root = ast.root()

  const edits = [addImports, addJsonSetter, addDefaultValue].flatMap((func) => func(root))
  const newSource = root.commitEdits(edits)
  fs.writeFileSync(filePath, newSource)
}

function readRule (ruleName: string): NapiConfig {
  const rule = fs.readFileSync(`./rules/trader-details-inner/${ruleName}.yaml`, 'utf-8')
  return yaml.parse(rule)
}

function addImports (root: SgNode): Edit[] {
  const config = readRule('add-imports')

  return root.findAll(config).map((node) => {
    const annotation = node.getMatch('HEADER')?.text()
    const imports = `
        ${annotation}
        import com.fasterxml.jackson.annotation.JsonSetter
        import com.fasterxml.jackson.annotation.Nulls
    `

    return node.replace(imports)
  })
}

function addJsonSetter (root: SgNode): Edit[] {
  const config = readRule('add-json-setter')

  return root.findAll(config).map((node) => {
    const annotation = node.getMatch('ANNOTATION')?.text()
    const newAnnotations = `
        ${annotation}
        @JsonSetter(nulls = Nulls.AS_EMPTY)
    `

    return node.replace(newAnnotations)
  })
}

function addDefaultValue (root: SgNode): Edit[] {
  const config = readRule('add-default-value')

  return root.findAll(config).map((node) => {
    const type = node.getMatch('TYPE')?.text()
    return node.replace(`${type}? = ""`)
  })
}

export { process as processTraderDetailsInner }
