import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class RoomItineraryLinksProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'room-itinerary-links';

  constructor() {
    super();
    this.rules = [
      this.imports,
      this.cancel,
      this.change,
      this.shopForChange,
    ].map(rule => rule.bind(this));
  }

  imports(root: SgNode): Edit[] {
    const config = this.readRule('imports');

    return root.findAll(config).map(node => {
      const list = node.getMatch('LIST')?.text();
      const newList = `
            import com.expediagroup.sdk.rapid.operations.DeleteRoomOperationLink
            import com.expediagroup.sdk.rapid.operations.ChangeRoomDetailsOperationLink
            import com.expediagroup.sdk.rapid.operations.GetAdditionalAvailabilityOperationLink
            ${list}
        `;
      return node.replace(newList);
    });
  }

  cancel(root: SgNode): Edit[] {
    const config = this.readRule('cancel');

    return root.findAll(config).map(node => {
      return node.replace('DeleteRoomOperationLink');
    });
  }

  change(root: SgNode): Edit[] {
    const config = this.readRule('change');

    return root.findAll(config).map(node => {
      return node.replace('ChangeRoomDetailsOperationLink');
    });
  }

  shopForChange(root: SgNode): Edit[] {
    const config = this.readRule('shop-for-change');

    return root.findAll(config).map(node => {
      return node.replace('GetAdditionalAvailabilityOperationLink');
    });
  }
}
