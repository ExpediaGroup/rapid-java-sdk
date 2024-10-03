import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process (filePath: PathOrFileDescriptor): void {
  const source = fs.readFileSync(filePath, 'utf-8')
  const ast = parse(Lang.Kotlin, source)
  const root = ast.root()

  const edits = [imports, links].flatMap((func) => func(root))
  const newSource = root.commitEdits(edits)
  fs.writeFileSync(filePath, newSource)
}

function readRule (ruleName: string): NapiConfig {
  const rule = fs.readFileSync(`./rules/localized/${ruleName}.yaml`, 'utf-8')
  return yaml.parse(rule)
}

function imports(root: SgNode): Edit[] {
    const config = readRule('imports')

    return root.findAll(config).map((node) => {
        const list = node.getMatch('LIST')?.text()
        const newList = `
            import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationLink
            ${list}
        `
        return node.replace(newList)
    })
}

function links (root: SgNode): Edit[] {
    const config = readRule('links')

    return root.findAll(config).map((node) => {
        return node.replace("GetPropertyContentOperationLink")
    })
}

export { process as processLocalized }