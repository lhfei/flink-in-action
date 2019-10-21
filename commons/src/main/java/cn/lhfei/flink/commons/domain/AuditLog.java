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

package cn.lhfei.flink.commons.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Jul 31, 2019
 */

public class AuditLog /* extends AbstractDomain */ {

	public AuditLog() {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.latestTime = sf.format(date);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLatestTime() {
		return latestTime;
	}

	public void setLatestTime(String latestTime) {
		// this.latestTime = latestTime;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getDelegation() {
		return delegation;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	private Long id;
	private String operatorId;
	private String createTime;
	private String latestTime;
	private int dataStatus;
	private String taskId;
	private String delegation;
	private String operationType;
	private String operationStatus;
}
