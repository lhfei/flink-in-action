/*
 * Copyright 2010-2011 the original author or authors.
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

package cn.lhfei.flink.sinks.mysql;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import cn.lhfei.flink.commons.domain.AuditLog;
import cn.lhfei.flink.commons.util.ConfigurationFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Jul 31, 2019
 */
public class MySqlSinkDriver {
	private static final Logger LOG = LoggerFactory.getLogger(MySqlSinkDriver.class);
	private static final Gson gson = new Gson();

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println(
					"Usage: java -jar <final-build>.jar cn.lhfei.flink.sources.kafka.KafkaDriver <KAFKA TOPIC>");
			System.exit(-1);
		}

		String topic = args[0];
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties props = ConfigurationFactory.getProperties("application.properties");
		
		SingleOutputStreamOperator<AuditLog> dss = env.addSource(new FlinkKafkaConsumer<>(
				topic,
				new SimpleStringSchema(),
				props)).setParallelism(1).map(string ->  gson.fromJson(string, AuditLog.class));
		
		dss.addSink(new Sink2MySql(props));

		env.execute("Flink Sink to MySQL");
	}
}
