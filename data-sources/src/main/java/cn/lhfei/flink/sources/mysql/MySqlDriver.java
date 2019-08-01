package cn.lhfei.flink.sources.mysql;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MySqlDriver {

	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.addSource(new MySqlSource()).print();

		env.execute("Flink add data sourc");
	}

}
