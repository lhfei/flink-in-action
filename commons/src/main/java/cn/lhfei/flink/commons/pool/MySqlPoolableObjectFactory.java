package cn.lhfei.flink.commons.pool;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MySqlPoolableObjectFactory extends BasePooledObjectFactory<Connection> {

	public MySqlPoolableObjectFactory(String host, int port, String schema, String user, String password) {
		this.host = host.trim();
		this.port = port;
		this.schema = schema.trim();
		this.user = user.trim();
		this.password = password.trim();
		this.url = this.buildUrl();
	}
	
	public MySqlPoolableObjectFactory(String url, String user, String password) {
		this.url = url;
		this.user = user.trim();
		this.password = password.trim();
	}

	@Override
	public Connection create() throws Exception {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(url, user, password);
	}

	@Override
	public PooledObject<Connection> wrap(Connection conn) {
		return new DefaultPooledObject<Connection>(conn);
	}
	
	private String buildUrl() {
		return "jdbc:mysql://" + host + ":" + port + "/"
	              + schema + "?autoReconnectForPools=true";
	}
	
	private String host;
    private int port;
    private String schema;
    private String user;
    private String password;
    private String url;

}
