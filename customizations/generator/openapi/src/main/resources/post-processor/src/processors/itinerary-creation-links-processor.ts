import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class ItineraryCreationLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'itinerary-creation-links';

  constructor() {
    super();
    this.rules = [
      this.imports,
      this.retrieve,
      this.resume,
      this.completePaymentSession,
      this.cancel,
    ].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.GetReservationByItineraryIdOperationLink
            import com.expediagroup.sdk.rapid.operations.PutResumeBookingOperationLink
            import com.expediagroup.sdk.rapid.operations.PutCompletePaymentSessionOperationLink
            import com.expediagroup.sdk.rapid.operations.DeleteHeldBookingOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  retrieve(root: SgNode): Edit[] {
    const config = this.readRule('retrieve');

    return root.findAll(config).map(node => {
      return node.replace('GetReservationByItineraryIdOperationLink');
    });
  }

  resume(root: SgNode): Edit[] {
    const config = this.readRule('resume');

    return root.findAll(config).map(node => {
      return node.replace('PutResumeBookingOperationLink');
    });
  }

  completePaymentSession(root: SgNode): Edit[] {
    const config = this.readRule('complete-payment-session');

    return root.findAll(config).map(node => {
      return node.replace('PutCompletePaymentSessionOperationLink');
    });
  }

  cancel(root: SgNode): Edit[] {
    const config = this.readRule('cancel');

    return root.findAll(config).map(node => {
      return node.replace('DeleteHeldBookingOperationLink');
    });
  }
}
