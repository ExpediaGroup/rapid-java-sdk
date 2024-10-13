import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {RuleFunction} from './shared.types';
import {Processor} from './processor';

export class ItineraryLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'itinerary-links';

  constructor() {
    super();
    this.rules = [this.imports, this.resume, this.cancel].map(rule =>
      rule.bind(this)
    );
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.PutResumeBookingOperationLink
            import com.expediagroup.sdk.rapid.operations.DeleteHeldBookingOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  resume(root: SgNode): Edit[] {
    const config = this.readRule('resume');

    return root.findAll(config).map(node => {
      return node.replace('PutResumeBookingOperationLink');
    });
  }

  cancel(root: SgNode): Edit[] {
    const config = this.readRule('cancel');

    return root.findAll(config).map(node => {
      return node.replace('DeleteHeldBookingOperationLink');
    });
  }
}
