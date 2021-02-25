/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myhexin.kaihu.sentinel.configuration;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
public final class SentinelNacosConfigUtils {

    public static String getFlowDataId(String appName) {
        return appName + SentinelNacosConfigConstant.FLOW_DATA_ID_POSTFIX;
    }

    public static String getDegradeDataId(String appName) {
        return appName + SentinelNacosConfigConstant.DEGRADE_DATA_ID_POSTFIX;
    }

    public static String getSystemDataId(String appName) {
        return appName + SentinelNacosConfigConstant.SYSTEM_DATA_ID_POSTFIX;
    }

    public static String getParamFlowDataId(String appName) {
        return appName + SentinelNacosConfigConstant.PARAM_FLOW_DATA_ID_POSTFIX;
    }

    public static String getAuthorityDataId(String appName) {
        return appName + SentinelNacosConfigConstant.AUTHORITY_DATA_ID_POSTFIX;
    }

    public static String getGroupId(String appName) {
        return appName.toUpperCase() + SentinelNacosConfigConstant.GROUP_ID_POSTFIX;
    }
}
