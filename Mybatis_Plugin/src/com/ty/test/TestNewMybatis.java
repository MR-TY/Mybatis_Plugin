package com.ty.test;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.ty.dao.DepartmentMapper;
import com.ty.dao.EmployeeMapper;
import com.ty.dao.EmployeeMapperDynamicSQL;
import com.ty.dao.EmployeeMapperParame;
import com.ty.dao.EmployeeMapperPlus;
import com.ty.entity.Department;
import com.ty.entity.Employee;

public class TestNewMybatis {
	InputStream inputStream = null;
	SqlSession session = null;
	SqlSession session1 = null;
	 {
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		session = sessionFactory.openSession();
		session1 = sessionFactory.openSession();
	}
	 @Test
	 public void testFirstCache(){
		 System.out.println(session);
		 EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		 Employee employee = employeeMapper.getEmployById(3);
		 System.out.println(employee);
		 session.clearCache();//清除一级缓存
		 System.out.println("-----------------再查询一次------------");
		 Employee employee2 =  employeeMapper.getEmployById(3);
		 System.out.println(employee2);
	 }
	 
	 @Test
	 public void testSecondCache(){
		EmployeeMapper employeeMapper =  session.getMapper(EmployeeMapper.class);
		Employee employee = employeeMapper.getEmployById(3);
		System.out.println(employee);
		session.close();
		System.out.println("-----------------不同的session再查询一次------------");
		EmployeeMapper employeeMapper2 = session1.getMapper(EmployeeMapper.class);
		Employee employee2 = employeeMapper2.getEmployById(3);
		System.out.println(employee2);
		session.close();
	 }
	 
	 @Test
	 public void testEhcacth(){
		 	EmployeeMapper employeeMapper =  session.getMapper(EmployeeMapper.class);
			Employee employee = employeeMapper.getEmployById(3);
			System.out.println(employee);
			session.close();
			System.out.println("-----------------不同的session再查询一次------------");
			EmployeeMapper employeeMapper2 = session1.getMapper(EmployeeMapper.class);
			Employee employee2 = employeeMapper2.getEmployById(3);
			System.out.println(employee2);
			session.close();
	 }
	 
	 @Test
	 public void testEhcacthRef(){
		 	DepartmentMapper departmentMapper =  session.getMapper(DepartmentMapper.class);
		 	Department department = departmentMapper.GetDeptById(1);
		 	System.out.println(department);
		 	session.close();
		 	System.out.println("-----------------不同的session再查询一次------------");
		 	DepartmentMapper departmentMapper2 = session1.getMapper(DepartmentMapper.class);
		 	Department department2 = departmentMapper2.GetDeptById(1);
		 	System.out.println(department2);
		 	session.close();
	 }
	 
	 @Test
	 public void testPlugin(){
		 
	 }
}
