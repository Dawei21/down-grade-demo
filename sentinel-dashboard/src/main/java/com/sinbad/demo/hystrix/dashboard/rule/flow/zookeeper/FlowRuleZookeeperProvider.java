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
package com.sinbad.demo.hystrix.dashboard.rule.flow.zookeeper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import com.sinbad.demo.hystrix.dashboard.datasource.DynamicDataSourceProvider;
import com.sinbad.demo.hystrix.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.sinbad.demo.enums.RuleTypePathEnum;

@Component("flowRuleZookeeperProvider")
public class FlowRuleZookeeperProvider implements DynamicDataSourceProvider<List<FlowRuleEntity>> {

	@Resource
	private CuratorFramework curatorFramework;
	@Resource
	private Converter<String, List<FlowRuleEntity>> converter;

	@Override
	public List<FlowRuleEntity> getRules(String appName) throws Exception {

		return new ZkRuleDataHelper(curatorFramework, converter, null).getRules(appName, RuleTypePathEnum.FLOW.getType());
//		String zkPath = ZookeeperConfigUtil.getPath(appName);
//		Stat stat = curatorFramework.checkExists().forPath(zkPath);
//		if (stat == null) {
//			return new ArrayList<>(0);
//		}
//		byte[] bytes = curatorFramework.getData().forPath(zkPath);
//		if (null == bytes || bytes.length == 0) {
//			return new ArrayList<>();
//		}
//		String resource = new String(bytes);
//
//		return converter.convert(resource);
	}
}