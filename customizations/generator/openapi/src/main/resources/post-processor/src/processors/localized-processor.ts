import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class LocalizedProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'localized';

  constructor() {
    super();
    this.rules = [this.imports, this.links].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.GetPropertyContentOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  links(root: SgNode): Edit[] {
    const config = this.readRule('links');

    return root.findAll(config).map(node => {
      return node.replace('GetPropertyContentOperationLink');
    });
  }
}
