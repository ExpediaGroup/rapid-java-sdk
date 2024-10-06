import * as fs from 'node:fs'
import { Edit, Lang, NapiConfig, parse, SgNode } from '@ast-grep/napi'
import { PathOrFileDescriptor } from 'fs'
import * as yaml from 'yaml'

function process(filePath: PathOrFileDescriptor): void {
  const source = fs.readFileSync(filePath, 'utf-8')
  const ast = parse(Lang.Kotlin, source)
  const root = ast.root()

  const edits = [imports, additionalRates, recommendations].flatMap((func) => func(root))
  const newSource = root.commitEdits(edits)
  fs.writeFileSync(filePath, newSource)
}

function readRule(ruleName: string): NapiConfig {
  const rule = fs.readFileSync(`./rules/property-availability-links/${ruleName}.yaml`, 'utf-8')
  return yaml.parse(rule)
}

function imports(root: SgNode): Edit[] {
    const config = readRule('imports')

    return root.findAll(config).map((node) => {
        const list = node.getMatch('LIST')?.text()
        const newList = `
            import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink
            import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationLink
            ${list}
        `
        return node.replace(newList)
    })
}

function additionalRates(root: SgNode): Edit[] {
    const config = readRule('additional-rates')

    return root.findAll(config).map((node) => {
        return node.replace("GetAdditionalAvailabilityOperationLink")
    })
}

function recommendations(root: SgNode): Edit[] {
    const config = readRule('recommendations')

    return root.findAll(config).map((node) => {
        return node.replace("GetAvailabilityOperationLink")
    })
}

export { process as processPropertyAvailabilityLinks }
