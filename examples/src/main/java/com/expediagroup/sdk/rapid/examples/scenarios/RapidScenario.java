package com.expediagroup.sdk.rapid.examples.scenarios;

import com.expediagroup.sdk.rapid.examples.salesprofiles.RapidPartnerSalesProfile;
import java.util.concurrent.ExecutionException;

/**
 * Interface representing a scenario to be executed with a specific sales profile.
 */
public interface RapidScenario {

  /**
   * Sets the sales profile for the scenario.
   *
   * @param rapidPartnerSalesProfile the sales profile to be set
   */
  void setProfile(RapidPartnerSalesProfile rapidPartnerSalesProfile);

  /**
   * Executes the scenario.
   *
   * @throws ExecutionException if an error occurs during execution
   * @throws InterruptedException if the execution is interrupted
   */
  void run() throws ExecutionException, InterruptedException;
}
