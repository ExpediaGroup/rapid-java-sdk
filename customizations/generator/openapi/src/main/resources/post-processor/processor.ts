import { processTraderDetailsInner } from './processors/trader-details-inner'
import { processRoomItineraryLinks } from './processors/room-itinerary-links'
import { processRoomPriceCheckLinks } from "./processors/room-price-check-links";
import { processCompletePaymentSessionLinks } from "./processors/complete-payment-session-links";
import { processItineraryCreationLinks } from "./processors/itinerary-creation-links";
import { processItineraryLinks } from "./processors/itinerary-links";
import { processPropertyAvailabilityLinks } from "./processors/property-availability-links";
import { processRateLinks } from "./processors/rate-links";
import { processLocalized } from "./processors/localized";
import { processPaymentSessionsLinks } from "./processors/payment-sessions-links";
import { processBedGroupAvailabilityLinks } from "./processors/bed-group-availability-links";
import { processLink } from "./processors/link";

import * as path from 'path'

const args = process.argv.slice(2)
const filePath = args[0]
const fileName = path.parse(filePath).name

switch (fileName) {
  case 'TraderDetailsInner':
    processTraderDetailsInner(filePath)
    break
  case 'RoomItineraryLinks':
    processRoomItineraryLinks(filePath)
    break
  case 'RoomPriceCheckLinks':
    processRoomPriceCheckLinks(filePath)
    break
  case 'CompletePaymentSessionLinks':
    processCompletePaymentSessionLinks(filePath)
    break
  case 'ItineraryCreationLinks':
    processItineraryCreationLinks(filePath)
    break
  case 'ItineraryLinks':
    processItineraryLinks(filePath)
    break
  case 'PropertyAvailabilityLinks':
    processPropertyAvailabilityLinks(filePath)
    break
  case 'RateLinks':
    processRateLinks(filePath)
    break
  case 'Localized':
    processLocalized(filePath)
    break
  case 'PaymentSessionsLinks':
    processPaymentSessionsLinks(filePath)
    break
  case 'BedGroupAvailabilityLinks':
    processBedGroupAvailabilityLinks(filePath)
    break
  case 'Link':
    processLink(filePath)
    break
}
