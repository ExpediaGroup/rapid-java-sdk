import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class TraderDetailsInnerProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'trader-details-inner';

  constructor() {
    super();
    this.rules = [
      this.addImports,
      this.addJsonSetter,
      this.addDefaultValue,
    ].map(rule => rule.bind(this));
  }

  addImports(root: SgNode): Edit[] {
    const config = this.readRule('add-imports');

    return root.findAll(config).map(node => {
      const annotation = node.getMatch('HEADER')?.text();
      const imports = `
        ${annotation}
        import com.fasterxml.jackson.annotation.JsonSetter
        import com.fasterxml.jackson.annotation.Nulls
    `;

      return node.replace(imports);
    });
  }

  addJsonSetter(root: SgNode): Edit[] {
    const config = this.readRule('add-json-setter');

    return root.findAll(config).map(node => {
      const annotation = node.getMatch('ANNOTATION')?.text();
      const newAnnotations = `
        ${annotation}
        @JsonSetter(nulls = Nulls.AS_EMPTY)
    `;

      return node.replace(newAnnotations);
    });
  }

  addDefaultValue(root: SgNode): Edit[] {
    const config = this.readRule('add-default-value');

    return root.findAll(config).map(node => {
      const type = node.getMatch('TYPE')?.text();
      return node.replace(`${type}? = ""`);
    });
  }
}
