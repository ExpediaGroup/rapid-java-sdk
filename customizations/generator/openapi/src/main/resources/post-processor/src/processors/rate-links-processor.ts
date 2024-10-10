import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class RateLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'rate-links';

  constructor() {
    super();
    this.rules = [this.imports, this.paymentOptions].map(rule =>
      rule.bind(this)
    );
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.GetPaymentOptionsOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  paymentOptions(root: SgNode): Edit[] {
    const config = this.readRule('payment-options');

    return root.findAll(config).map(node => {
      return node.replace('GetPaymentOptionsOperationLink');
    });
  }
}
