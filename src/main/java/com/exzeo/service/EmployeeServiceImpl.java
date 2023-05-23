package com.exzeo.service;

import java.util.Optional;

import com.exzeo.dto.EmployeeDTO;
import com.exzeo.entity.Employee;
import com.exzeo.entity.Employee2;
import com.exzeo.repository.Employee2Repository;
import com.exzeo.repository.EmployeeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exzeo.exception.EmployeeException;

@Service(value = "customerLoanService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {


	public static final Log log = LogFactory.getLog(EmployeeServiceImpl.class);;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private Employee2Repository employee2Repository;

	@Override
	public void copyData(EmployeeDTO employee) {
		log.info("Employee data is being copied");
		Employee2 employee2 = new Employee2();
		employee2.setId(employee.getId());
		employee2.setName(employee.getName());
		employee2.setAge(employee.getAge());
		employee2Repository.save(employee2);
	}

	@Override
	public EmployeeDTO getData(Integer id) throws EmployeeException {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee =optional.orElseThrow(()->new EmployeeException("No Employee is present with given id"));
		log.info("Data found now being populated in dto object ");
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setAge(employee.getAge());
		return employeeDTO;
	}

}