package com.titanium.user;

import com.titanium.user.dto.MemberRegistration;
import com.titanium.user.dto.UserRegistration;
import com.titanium.user.model.Member;
import com.titanium.user.model.MemberAddress;
import com.titanium.user.model.BankUser;
import com.titanium.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class UserApplicationTests {

	@Autowired
	private UserService service;

	@BeforeEach
	void setUp() {
		if(service.userExists()) {
			service.deleteAllUsers();
		}
	}

	@Test
	void contextLoads() {
	}

	@Test
	void addUser() {
		UserRegistration registration = new UserRegistration();
		registration.setUserType("member");
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
	    BankUser actualUser = service.addUser(registration);
		BankUser expectedUser = new BankUser("member", "chloejohnsoncodes@gmail.com", "chloe", actualUser.getPassword());
		expectedUser.setToken(actualUser.getToken());
		expectedUser.setUserId(actualUser.getUserId());
		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	void addMember() {
		MemberRegistration registration = new MemberRegistration();
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
		registration.setFirstName("Chloe");
		registration.setLastName("Johnson");
		registration.setAddressLine1("1337 N Highwood Ave");
		registration.setPhone("2089541744");
		registration.setDateOfBirth(LocalDate.now());
		registration.setSocialSecurityNumber("503-14-1234");
		registration.setCity("Boise");
		registration.setState("Idaho");
		registration.setZipcode("83713");
		Member actualMember = service.addMember(registration);
		BankUser user = new BankUser("member", "chloejohnsoncodes@gmail.com", "chloe", actualMember.getBankUser().getPassword());
		user.setUserId(actualMember.getBankUser().getUserId());
		user.setToken(actualMember.getBankUser().getToken());
		Member expectedMember = new Member("Chloe", "Johnson", "2089541744", registration.getDateOfBirth(), "503-14-1234");
		expectedMember.setMemberId(actualMember.getMemberId());
		MemberAddress address = new MemberAddress("1337 N Highwood Ave", null, "Boise", "Idaho", "83713");
		address.setAddressId(actualMember.getMemberAddress().getAddressId());
		expectedMember.setMemberAddress(address);
		address.setMember(expectedMember);
		user.setMember(expectedMember);
		expectedMember.setBankUser(user);
		Assertions.assertEquals(expectedMember, actualMember);
	}
}
