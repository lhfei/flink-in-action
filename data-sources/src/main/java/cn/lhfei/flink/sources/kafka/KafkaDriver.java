package cn.lhfei.flink.sources.kafka;

import java.util.Properties;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import com.esotericsoftware.minlog.Log;

import cn.lhfei.flink.commons.util.ConfigurationFactory;

public class KafkaDriver {

	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
    		System.err.println("Usage: java -jar <final-build>.jar cn.lhfei.flink.sources.kafka.KafkaDriver <KAFKA TOPIC>");
			System.exit(-1);
    	}
		
		String topic = args[0];
		
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Properties props = ConfigurationFactory.getProperties("application.properties");

		DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer<>(topic, 
				new SimpleStringSchema(),
				props)).setParallelism(1);

		// dataStreamSource.print();
		
		dataStreamSource.rebalance().map(new MapFunction<String, String> () {
			private static final long serialVersionUID = 8865556101194960167L;

			@Override
			public String map(String value) throws Exception {
				Log.info("Received message: {}", value);
				return null;
			}
			
		});

		env.execute("Flink add data source");
	}

}
