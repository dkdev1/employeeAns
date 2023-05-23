package com.exzeo.service;

import com.exzeo.dto.EmployeeDTO;
import com.exzeo.exception.EmployeeException;

public interface EmployeeService {
 
	public void copyData(EmployeeDTO employee);

	public EmployeeDTO getData(Integer id) throws EmployeeException;
   
}