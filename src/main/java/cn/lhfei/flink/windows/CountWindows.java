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

package cn.lhfei.flink.windows;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 8月 27, 2018
 */

public class CountWindows {
	public static void main(String[] args) throws Exception {

		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStreamSource<Tuple2<String, Integer>> sourceStream = env.fromElements(
				new Tuple2("a", 1),
				new Tuple2("a", 1), 
				new Tuple2("a", 1), 
				new Tuple2("a", 1), 
				new Tuple2("a", 1), 
				new Tuple2("b", 2),
				new Tuple2("b", 2), 
				new Tuple2("b", 2), 
				new Tuple2("b", 2), 
				new Tuple2("b", 2));

		sourceStream.keyBy(0).countWindow(5).sum(1).print();

		// execute program
		env.execute("Count Windows");
	}
}
