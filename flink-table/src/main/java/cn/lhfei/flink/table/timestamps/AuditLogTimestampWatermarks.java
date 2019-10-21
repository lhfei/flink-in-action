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

package cn.lhfei.flink.table.timestamps;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Aug 01, 2019
 */

public class AuditLogTimestampWatermarks<AuditLog> implements AssignerWithPeriodicWatermarks<AuditLog> {
	private static final long serialVersionUID = 7376979601591444022L;
	
	public static final int MAX_OUT_OF_ORDERNESS = 1000;

	@Override
	public long extractTimestamp(AuditLog element, long previousElementTimestamp) {
		return 0;
	}

	@Override
	public Watermark getCurrentWatermark() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*public AuditLogTimestampWatermarks() {
		super(Time.milliseconds(MAX_OUT_OF_ORDERNESS));
	}


	@Override
	public long extractTimestamp(AuditLog element) {
		return element.getLatestTime().getTime();
	}*/

}
