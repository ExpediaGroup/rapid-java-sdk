//package com.expediagroup.sdk.rapid.examples.services;
//
//import com.expediagroup.sdk.core.model.paging.Paginator;
//import com.expediagroup.sdk.rapid.models.Region;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * This shows how to consume the rapid geography apis using the rapid java sdk to:
// * 1. get all regions
// * 2. get all regions within a specific larger region
// * 3. get one region definition and properties mappings within this region
// */
//public class GeographyService extends RapidService {
//
//    private static final Logger logger = LoggerFactory.getLogger(GeographyService.class);
//
//    public List<List<Region>> getAllRegions() {
//        logger.info("------------- Calling getRegionsPaginator with no ancestor ID to get all regions:");
//
//        Paginator<List<Region>> paginator = rapidClient.getRegionsPaginator(Arrays.asList("details"), "en-US");
//
//        List<List<Region>> pages = new ArrayList<>();
//
//        logger.info("Paginator total results count: {}", paginator.getPaginationTotalResults());
//
//        logger.info("First page results:");
//
//        while (paginator.hasNext()) {
//            List<Region> page = paginator.next();
//
//            page.forEach(region -> {
//                logger.info("-------------------------------------");
//                logger.info("Region Full Name: {}", region.getNameFull());
//                logger.info("Region Type: {}", region.getType());
//                logger.info("Region Country Code: {}", region.getCountryCode());
//                logger.info("-------------------------------------");
//
//            });
//
//            pages.add(page);
//            break; // remove to iterate over all pages.
//        }
//
//        return pages;
//    }
//
//    public List<List<Region>> getRegionsByAncestor(String ancestorId) {
//        logger.info("------------- Calling getRegionsPaginator by ancestor ID:");
//        Paginator<List<Region>> paginator = rapidClient.getRegionsPaginator(Arrays.asList("details"), "en-US", "", ancestorId);
//
//        List<List<Region>> pages = new ArrayList<>();
//
//        while (paginator.hasNext()) {
//            List<Region> page = paginator.next();
//
//            page.forEach(region -> {
//                logger.info("-------------------------------------");
//                logger.info("Region Full Name: {}", region.getNameFull());
//                logger.info("Region Type: {}", region.getType());
//                logger.info("Region Country Code: {}", region.getCountryCode());
//                logger.info("-------------------------------------");
//
//            });
//
//            pages.add(page);
//            break; // remove to iterate over all pages.
//        }
//
//        return pages;
//    }
//
//    public Region getRegion(String regionId, String language) {
//        logger.info("------------- Calling GetRegion:");
//        Region region = rapidClient.getRegion(regionId, language, Arrays.asList("property_ids"));
//
//        logger.info("Region Full Name: {}", region.getNameFull());
//        logger.info("Region Type: {}", region.getType());
//        logger.info("Region Country Code: {}", region.getCountryCode());
//
//        logger.info("Region Property IDs:");
//        region.getPropertyIds().forEach(System.out::println);
//
//        return region;
//    }
//
//}
