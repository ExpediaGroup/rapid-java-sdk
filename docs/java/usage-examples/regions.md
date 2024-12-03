---
intro: Regions
shortTitle: Regions
title: Regions
---
## Regions

#### The Regions API returns the geographic definition and property mappings of regions matching the specified parameters.

The response is a paginated list of regions and you can use the SDK to fetch additional pages of results. For more information on this service please see [here](products/rapid/lodging/geography).

```java
GetRegionsOperationParams regionsOperationParams = GetRegionsOperationParams
        .builder()
        .language("en_US")
        .include(List.of("details"))
        /* ... */
        .build();

GetRegionsOperation getRegionsOperation = new GetRegionsOperation(regionsOperationParams);
List<List<Region>> pages = new ArrayList<>();
rapidClient.getPaginator(getRegionsOperation).forEachRemaining(page -> pages.add(page.getData()));
```
In the example above:
`pages` have all the regions, you can iterate over them as needed.
Another option is to embed your needed functionality in the `forEachRemaining` lambda function directly.
