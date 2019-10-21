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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Aug 06, 2019
 */

public class Happy {

	public static void main(String[] args) {
		int i = 1;
		int j = i++;
		if(i == (++j) && ((i++) == j)) {
			i +=j;
		}
		
		int num = 2147483647;
		
		num += 2;
		
		boolean flag = 10 % 2 == 1 && 10 / 3 == 0 && 1 / 0 == 0;
		
		System.out.println(flag ? "mldn" : "yootk");
		
		System.out.println(num);
		
		
		char c = 'A';
		int nm = 10;
		switch (c) {
		case 'B':
			nm++;
		case 'A':
			nm++;
		case 'Y':
			nm++;
			break;
		default:
			nm--;
		}
		
		System.out.println(nm);
		
		int tt = 50;
		tt = tt++ * 2;
		
		num = 2147483647;
		long temp = num +2L;
		System.out.println(temp);
		
		
		int a = 10;
		double y = 20.2;
		long z = 10L;
		
		System.out.println("" +a +y*z);
		
	}

}
