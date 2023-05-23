package com.exzeo;

import java.time.LocalDate;

import com.exzeo.dto.EmployeeDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.exzeo.service.EmployeeService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	public static final Log LOGGER = LogFactory.getLog(DemoApplication.class);

	@Autowired
	EmployeeService customerLoanService;
	
	@Autowired
	Environment environment;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EmployeeDTO employeeDTO = getEmployeeData();
		LOGGER.info("Copying data into another table");
		copyData(employeeDTO);
	}

	private void copyData(EmployeeDTO employeeDTO) {
		try {
			customerLoanService.copyData(employeeDTO);
			LOGGER.info("Data is saved successfully check in db");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

	private EmployeeDTO getEmployeeData() {
		try {
			EmployeeDTO employeeDTO=customerLoanService.getData(1);
			LOGGER.info(employeeDTO);
			return employeeDTO;
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
		return null;
	}

}

