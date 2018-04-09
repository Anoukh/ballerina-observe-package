/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.test.metrics;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.ballerinalang.config.ConfigRegistry;
import org.testng.annotations.BeforeSuite;

import static org.ballerinalang.util.observability.ObservabilityConstants.CONFIG_METRICS_ENABLED;

public class MetricTest {

    @BeforeSuite
    public void init() {
        Metrics.addRegistry(new SimpleMeterRegistry());
        ConfigRegistry configRegistry = ConfigRegistry.getInstance();
        configRegistry.addConfiguration(CONFIG_METRICS_ENABLED, String.valueOf(Boolean.TRUE));
    }
}
