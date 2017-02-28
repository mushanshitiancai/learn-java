package com.mushan.learn.java.aop.service.impl;

import com.mushan.learn.java.aop.service.BaseService;
import com.mushan.learn.java.aop.service.EmployeeService;
import com.mushan.learn.java.aop.aop.PeopleService;
import org.springframework.stereotype.Service;

/**
 * Created by mazhibin on 16/12/30
 */
@Service("employeeServiceImpl")
public class EmployeeServiceImpl extends BaseService implements EmployeeService,PeopleService {
    
    private static int i = 0;
    
    @Override
    public int getCount() {
        return i++;
    }

    @Override
    public String getName() {
        return "mushan";
    }
}
