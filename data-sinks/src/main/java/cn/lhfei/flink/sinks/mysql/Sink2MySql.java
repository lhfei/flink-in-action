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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import cn.lhfei.flink.commons.domain.AuditLog;
import cn.lhfei.flink.commons.pool.MySqlPoolableObjectFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Jul 31, 2019
 */
public class Sink2MySql extends RichSinkFunction<AuditLog> {
	private static final long serialVersionUID = 6220300930318865579L;
	
	public Sink2MySql() {}
	
	public Sink2MySql(Properties props) {
		this.props = props;
	}
	
	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		conn = new MySqlPoolableObjectFactory(props.getProperty("connection.url"), 
				props.getProperty("connection.user"), 
				props.getProperty("connection.password")).create();

		ps = conn.prepareStatement(props.getProperty("connection.prepare_statement.sql"));
	}
	
	@Override
	public void invoke(AuditLog audit, Context context) throws Exception {
		super.invoke(audit, context);
		ps.setString(1, audit.getOperatorId());
		ps.setString(2, audit.getDelegation());
		ps.setString(3, audit.getTaskId());
		ps.setString(4, audit.getOperationType());
		ps.setString(5, audit.getOperationStatus());
		ps.setDate(6, new java.sql.Date(audit.getCreateTime().getTime()));
		ps.setDate(7, new java.sql.Date(audit.getLatestTime().getTime()));
		ps.setInt(8, audit.getDataStatus());
		
		ps.executeUpdate();
	}
	
	@Override
	public void close() throws Exception {
		super.close();
		if (conn != null) {
			conn.close();
		}
		if (ps != null) {
			ps.close();
		}
	}
	private PreparedStatement ps;
	private Connection conn;
	private Properties props ;
}
