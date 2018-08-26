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

package cn.lhfei.flink.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.tuple.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lhfei.flink.service.WordCountService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Oct 25, 2018
 */
@RestController
@RequestMapping("/jobs")
public class BatchResource extends AbstractResource {

	@RequestMapping(value = "/ok", method = GET)
	public String isOk() {
		return "ok";
	}
	
	@RequestMapping(value = "/wordCount", method = GET)
	public DataSet<Tuple2<String, Integer>> count() throws Exception {
		String input = "";
		
		DataSet<Tuple2<String, Integer>> counts = wordCountService.count(new String[] {input});
		
		return counts;
	}
	
	
	@Autowired
	private WordCountService wordCountService;
}
