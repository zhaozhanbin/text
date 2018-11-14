/** 
 * <pre>项目名称:StringBoot-dubbo-provider1 
 * 文件名称:user.java 
 * 包名:com.jk.model 
 * 创建日期:2018年11月13日下午11:06:20 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import java.util.HashMap;

/** 
 * <pre>项目名称：StringBoot-dubbo-provider1    
 * 类名称：user    
 * 类描述：    
 * 创建人：赵展彬 
 * 创建时间：2018年11月13日 下午11:06:20    
 * 修改人：赵展彬   
 * 修改时间：2018年11月13日 下午11:06:20    
 * 修改备注：       
 * @version </pre>    
 */

public class User {
	private HashMap<String, String> map;
	
	
	

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Redis [map=" + map + "]";
	}
	
	

}
