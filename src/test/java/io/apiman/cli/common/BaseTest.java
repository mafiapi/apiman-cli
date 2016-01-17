/*
 * Copyright 2016 Pete Cornish
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apiman.cli.common;

import io.apiman.cli.Cli;
import org.junit.ClassRule;

/**
 * This base class waits for an instance of apiman.
 *
 * @author Pete Cornish {@literal <outofcoffee@gmail.com>}
 */
public class BaseTest {
    /**
     * Wait for apiman to be available.
     */
    @ClassRule
    public static WaitForHttp apiman = new WaitForHttp("localhost", 8080, "/apiman/system/status");

    public static String getApimanUrl() {
        return apiman.getAddress() + "/apiman";
    }

    /**
     * Create an org with the given name.
     *
     * @param orgName the org name
     */
    protected void createOrg(String orgName) {
        Cli.main("org", "create",
                "--debug",
                "--server", getApimanUrl(),
                "--serverUsername", "admin",
                "--serverPassword", "admin123!",
                "--name", orgName,
                "--description", "example");
    }
}