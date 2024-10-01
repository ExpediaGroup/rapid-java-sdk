import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process(filePath: PathOrFileDescriptor): void {
  const source = fs.readFileSync(filePath, 'utf-8')
  const ast = parse(Lang.Kotlin, source)
  const root = ast.root()

  const edits = [classModifier, parameters].flatMap((func) => func(root))
  const newSource = root.commitEdits(edits)
  fs.writeFileSync(filePath, newSource)
}

function readRule(ruleName: string): NapiConfig {
  const rule = fs.readFileSync(`./rules/link/${ruleName}.yaml`, 'utf-8')
  return yaml.parse(rule)
}

function classModifier(root: SgNode): Edit[] {
    const config = readRule('class-modifier')

    return root.findAll(config).map((node) => {
        return node.replace("open")
    })
}

function parameters(root: SgNode): Edit[] {
    const config = readRule('parameters')

    return root.findAll(config).map((node) => {
        const modifiers = node.getMatch('MODIFIERS')?.text()
        const newModifiers = `
            ${modifiers}
            open
        `
        return node.replace(newModifiers)
    })
}

export { process as processLink }
