package com.expediagroup.sdk.rapid.examples.scenarios;

import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;

public interface RapidScenario {

    void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile);

    void run();
}
