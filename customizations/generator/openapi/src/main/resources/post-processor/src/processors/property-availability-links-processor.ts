import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class PropertyAvailabilityLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'property-availability-links';

  constructor() {
    super();
    this.rules = [this.imports, this.additionalRates, this.recommendations].map(
      rule => rule.bind(this)
    );
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink
            import com.expediagroup.sdk.rapid.operations.GetAvailabilityOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  additionalRates(root: SgNode): Edit[] {
    const config = this.readRule('additional-rates');

    return root.findAll(config).map(node => {
      return node.replace('GetAdditionalAvailabilityOperationLink');
    });
  }

  recommendations(root: SgNode): Edit[] {
    const config = this.readRule('recommendations');

    return root.findAll(config).map(node => {
      return node.replace('GetAvailabilityOperationLink');
    });
  }
}
