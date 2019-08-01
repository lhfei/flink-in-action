package cn.lhfei.flink.sources.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import cn.lhfei.flink.commons.domain.AuditLog;
import cn.lhfei.flink.commons.pool.MySqlPoolableObjectFactory;

public class MySqlSource extends RichSourceFunction<AuditLog> {
	private static final long serialVersionUID = -3972548667355328575L;
	private static final Logger LOG = LoggerFactory.getLogger(MySqlSource.class);
	private static final Gson gson = new Gson();

	@Override
	public void open(Configuration parameters) throws Exception {
		super.open(parameters);
		conn = new MySqlPoolableObjectFactory("192.168.149.91", 3306, "test", "root", "Lhfei").create();
		
		String sql = "SELECT`ID`,  `OPERATOR_ID`,  `DELEGATION`,  `TASK_ID`,  `OPS_TYPE`,  `OPS_STATUS`,  `CREATE_TIME`,  `LATEST_TIME`,  `DATA_STATUS` FROM  `test`.`audit_log` ";
		ps = conn.prepareStatement(sql);
	}

	@Override
	public void run(SourceContext<AuditLog> ctx) throws Exception {
		ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	AuditLog audit = new AuditLog();
        	audit.setId(rs.getLong("ID"));
        	audit.setDataStatus(rs.getInt("DATA_STATUS"));
        	audit.setDelegation(rs.getString("DELEGATION"));
        	audit.setOperationStatus(rs.getString("OPS_STATUS"));
        	audit.setOperationType(rs.getString("OPS_TYPE"));
        	audit.setOperatorId(rs.getString("OPERATOR_ID"));
        	audit.setTaskId(rs.getString("TASK_ID"));
        	
            ctx.collect(audit);
            
            LOG.info("==> {}", gson.toJson(audit));
            
        }
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

	@Override
	public void cancel() {
	}

	private PreparedStatement ps;
	private Connection conn;
}
