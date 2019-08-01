package cn.lhfei.flink.commons.util;

import java.io.Serializable;
import java.util.Properties;
import java.util.function.Consumer;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationFactory implements Serializable {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationFactory.class);
	private static final long serialVersionUID = 1168258796368555763L;
	
	public static Configuration getConfiguration(String file) {
		Configuration config = null;
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName(file));

		try {
			config = builder.getConfiguration();
		} catch (ConfigurationException e) {
			LOG.warn("Properties file [{}] not found.", file);
		} 
		return config;
	}
	
	public static Properties getProperties(String file) {
		Properties props = new Properties();
		Configuration config = getConfiguration(file);
		config.getKeys().forEachRemaining(new Consumer<String>() {

			@Override
			public void accept(String key) {
				props.put(key, config.getString(key));
			}
			
		});
		
		return props;
	}
}
