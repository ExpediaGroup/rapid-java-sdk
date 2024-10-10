import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class PaymentSessionsLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'payment-sessions-links';

  constructor() {
    super();
    this.rules = [this.imports, this.book].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.PostItineraryOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  book(root: SgNode): Edit[] {
    const config = this.readRule('book');

    return root.findAll(config).map(node => {
      return node.replace('PostItineraryOperationLink');
    });
  }
}
