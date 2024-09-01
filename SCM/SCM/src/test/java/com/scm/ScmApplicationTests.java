package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.serviceimplementation.EmailServiceImplementation;

@SpringBootTest
class ScmApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailServiceImplementation emailService;
	@Test
	void sendEmail(){
		emailService.sendEmail("manishyadav01445@gmail.com", "Just a testing mail", "Testing Mail Service");
	}

}
