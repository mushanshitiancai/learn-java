package com.mushan.learn.java.aop;

import com.mushan.learn.java.aop.service.EmployeeService;
import com.mushan.learn.java.aop.aop.PeopleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/12/30
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-context.xml");

//        EmployeeService employeeService = context.getBean(EmployeeService.class);
//        System.out.println(employeeService.getCount());

//        BaseService baseService = context.getBean(BaseService.class);
//        System.out.println(baseService.getServiceName());

//        EmployeeServiceImpl employeeService1 = context.getBean(EmployeeServiceImpl.class);
//        System.out.println(employeeService1.getCount());

        EmployeeService employeeService1 = (EmployeeService)context.getBean("employeeServiceImpl");
        System.out.println(employeeService1.getCount());

        PeopleService employeeService1_people = (PeopleService)employeeService1; 
        System.out.println(employeeService1_people.getName());
        
        

        EmployeeService employeeService2 = (EmployeeService)context.getBean("employeeServiceImpl2");
        System.out.println(employeeService2.getCount());

        PeopleService employeeService2_people = (PeopleService)employeeService2;
        System.out.println(employeeService2_people.getName());
        
        Runnable employeeService2_run = (Runnable)employeeService2;
        employeeService2_run.run();
    }
}
