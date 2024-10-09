package com.expediagroup.sdk.rapid.examples.scenarios;

import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;

import java.util.concurrent.ExecutionException;

public interface RapidScenario {

    void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile);

    void run() throws ExecutionException, InterruptedException;
}
