import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class RoomPriceCheckLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'room-price-check-links';

  constructor() {
    super();
    this.rules = [
      this.imports,
      this.book,
      this.commit,
      this.paymentSession,
      this.additionalRates,
    ].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.PostItineraryOperationLink
            import com.expediagroup.sdk.rapid.operations.CommitChangeOperationLink
            import com.expediagroup.sdk.rapid.operations.PostPaymentSessionsOperationLink
            import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink
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

  commit(root: SgNode): Edit[] {
    const config = this.readRule('commit');

    return root.findAll(config).map(node => {
      return node.replace('CommitChangeOperationLink');
    });
  }

  paymentSession(root: SgNode): Edit[] {
    const config = this.readRule('payment-session');

    return root.findAll(config).map(node => {
      return node.replace('PostPaymentSessionsOperationLink');
    });
  }

  additionalRates(root: SgNode): Edit[] {
    const config = this.readRule('additional-rates');

    return root.findAll(config).map(node => {
      return node.replace('GetAdditionalAvailabilityOperationLink');
    });
  }
}
