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

package cn.lhfei.flink.table;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.google.gson.Gson;

import cn.lhfei.flink.commons.domain.AuditLog;
import cn.lhfei.flink.commons.util.ConfigurationFactory;
import cn.lhfei.flink.table.timestamps.AuditLogTimestampWatermarks;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Aug 01, 2019
 */

public class StreamSQLExample {
	private static final Logger LOG = LoggerFactory.getLogger(StreamSQLExample.class);
	private static Marker MARKER = MarkerFactory.getMarker("K-TAB");
	private static final Gson gson = new Gson();

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println(
					"Usage: java -jar <final-build>.jar cn.lhfei.flink.sources.kafka.KafkaDriver <KAFKA TOPIC>");
			System.exit(-1);
		}

		String topic = args[0];
		Properties props = ConfigurationFactory.getProperties("application.properties");
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);
		
		SingleOutputStreamOperator<AuditLog> dss = env
				.addSource(new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props))
				.map(new MapFunction<String, AuditLog>() {
					private static final long serialVersionUID = 1L;
					@Override
					public AuditLog map(String value) throws Exception {
						LOG.info(MARKER, "Received message: {}", value);
						return gson.fromJson(value, AuditLog.class);
					}
				})
				/*.map(string -> gson.fromJson(string, AuditLog.class))*/
				.assignTimestampsAndWatermarks(new AuditLogTimestampWatermarks<AuditLog>());
		
		// convert DataStream to Table
		Table auditTable = tableEnv.fromDataStream(dss, "id, operationType, operationStatus, operatorId, createTime, latestTime, dataStatus, taskId, delegation");
		
		// register DataStream as Table
		tableEnv.registerTable("audit_log", auditTable);
		
		Table result = tableEnv.sqlQuery("SELECT * FROM audit_log ")/*.filter("operatorId = 'Joy1 '")*/;
		
		result.printSchema();
		
		DataStream<AuditLog> ds = tableEnv.toAppendStream(result, AuditLog.class);
		
		ds.rebalance().map(new MapFunction<AuditLog, String>() {
			private static final long serialVersionUID = -2085747479822913210L;

			@Override
			public String map(AuditLog value) throws Exception {
				LOG.info(MARKER, "Consumerd message: {}", gson.toJson(value));
				return null;
			}
			
		});
		
		env.execute("Flink Streaming Table");
	}

}
