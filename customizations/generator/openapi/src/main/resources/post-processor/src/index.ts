import {BedGroupAvailabilityLinksProcessor} from './processors/bed-group-availability-links-processor';
import {CompletePaymentSessionLinksProcessor} from './processors/complete-payment-session-links-processor';
import {ItineraryCreationLinksProcessor} from './processors/itinerary-creation-links-processor';
import {ItineraryLinksProcessor} from './processors/itinerary-links-processor';
import {LinkProcessor} from './processors/link-processor';
import {LocalizedProcessor} from './processors/localized-processor';
import {PaymentSessionsLinksProcessor} from './processors/payment-sessions-links-processor';
import {PropertyAvailabilityLinksProcessor} from './processors/property-availability-links-processor';
import {RateLinksProcessor} from './processors/rate-links-processor';
import {RoomItineraryLinksProcessor} from './processors/room-itinerary-links-processor';
import {TraderDetailsInnerProcessor} from './processors/trader-details-inner-processor';
import {RoomPriceCheckLinksProcessor} from './processors/room-price-check-links-processor';

import * as path from 'path';

const args = process.argv.slice(2);
const filePath = args[0];
const fileName = path.parse(filePath).name;

switch (fileName) {
  case 'TraderDetailsInner':
    new TraderDetailsInnerProcessor().process(filePath);
    break;
  case 'RoomItineraryLinks':
    new RoomItineraryLinksProcessor().process(filePath);
    break;
  case 'RoomPriceCheckLinks':
    new RoomPriceCheckLinksProcessor().process(filePath);
    break;
  case 'CompletePaymentSessionLinks':
    new CompletePaymentSessionLinksProcessor().process(filePath);
    break;
  case 'ItineraryCreationLinks':
    new ItineraryCreationLinksProcessor().process(filePath);
    break;
  case 'ItineraryLinks':
    new ItineraryLinksProcessor().process(filePath);
    break;
  case 'PropertyAvailabilityLinks':
    new PropertyAvailabilityLinksProcessor().process(filePath);
    break;
  case 'RateLinks':
    new RateLinksProcessor().process(filePath);
    break;
  case 'Localized':
    new LocalizedProcessor().process(filePath);
    break;
  case 'PaymentSessionsLinks':
    new PaymentSessionsLinksProcessor().process(filePath);
    break;
  case 'BedGroupAvailabilityLinks':
    new BedGroupAvailabilityLinksProcessor().process(filePath);
    break;
  case 'Link':
    new LinkProcessor().process(filePath);
    break;
}
