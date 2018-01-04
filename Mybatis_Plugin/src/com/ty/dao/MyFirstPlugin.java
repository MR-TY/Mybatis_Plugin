package com.ty.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
/**
 * type:拦截的对象
 * method：拦截的方法
 * args：拦截的参数
* Copyright: Copyright (c) 2018 LanRu-Caifu
* 
* @ClassName: MyFirstPlugin.java
* @Description: 该类的功能描述
*
* @version: v1.0.0
* @author: water
* @date: 2018年1月4日 下午6:33:06 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年1月4日       water           v1.0.0               修改原因
 */
@Intercepts({
	@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)
	
})
public class MyFirstPlugin implements Interceptor{
	
	//----------拦截目标方法--------------
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("所要拦截的目标方法："+invocation.getMethod());
		// 执行目标方法,如果不执行这个方法，这就不能执行所拦截的方法
		Object proceed = invocation.proceed();
		return proceed;
	}

	//----------插件包装：为目标对象创建一个代理对象------------
	@Override
	public Object plugin(Object target) {
		System.out.println("包装的对象："+target);
		//借助这个方法，使用当前的Interceptor包装我们的目标对象
		Object wrap = Plugin.wrap(target, this);
		//返回动态代理
		return wrap;
	}
	//----------将插件注册时的属性设置--------------
	@Override
	public void setProperties(Properties properties) {
		
		System.out.println("插件配置的信息："+properties);
	}

}
