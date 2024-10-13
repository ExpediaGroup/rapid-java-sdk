import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class BedGroupAvailabilityLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'bed-group-availability-links';

  constructor() {
    super();
    this.rules = [this.imports, this.priceCheck].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.PriceCheckOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  priceCheck(root: SgNode): Edit[] {
    const config = this.readRule('price-check');

    return root.findAll(config).map(node => {
      return node.replace('PriceCheckOperationLink');
    });
  }
}
