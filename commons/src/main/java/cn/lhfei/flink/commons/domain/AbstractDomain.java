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

import java.io.Serializable;
import java.util.Date;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Jul 31, 2019
 */
public abstract class AbstractDomain implements Serializable {
	private static final long serialVersionUID = -825478888629012903L;

	public AbstractDomain() {
		Date date = new Date();
		this.createTime = date;
		this.latestTime = date;
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
	public Date getCreateTime() {
		return createTime;
	}
	public Date getLatestTime() {
		return latestTime;
	}

	private Long id;
	private String operatorId;
	private Date createTime;
	private Date latestTime;
	private int dataStatus;
	private String taskId;
	private String delegation;
}