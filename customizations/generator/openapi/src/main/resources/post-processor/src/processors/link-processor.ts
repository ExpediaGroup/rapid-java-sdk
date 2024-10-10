import {Edit, NapiConfig, SgNode} from '@ast-grep/napi';
import {Processor} from './processor';
import {RuleFunction} from './shared.types';

export class LinkProcessor extends Processor {
  rules: RuleFunction[];
  id: String = 'link';

  constructor() {
    super();
    this.rules = [this.classModifier, this.parameters].map(rule =>
      rule.bind(this)
    );
  }

  classModifier(root: SgNode): Edit[] {
    const config = this.readRule('class-modifier');

    return root.findAll(config).map(node => {
      return node.replace('open');
    });
  }

  parameters(root: SgNode): Edit[] {
    const config = this.readRule('parameters');

    return root.findAll(config).map(node => {
      const modifiers = node.getMatch('MODIFIERS')?.text();
      const newModifiers = `
            ${modifiers}
            open
        `;
      return node.replace(newModifiers);
    });
  }
}
