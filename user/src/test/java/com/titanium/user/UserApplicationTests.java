package com.titanium.user;

import com.titanium.user.dto.MemberRegistration;
import com.titanium.user.dto.MemberResponse;
import com.titanium.user.dto.UserRegistration;
import com.titanium.user.dto.UserResponse;
import com.titanium.user.model.Member;
import com.titanium.user.model.User;
import com.titanium.user.model.UserToken;
import com.titanium.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	    service.addUser(registration, "/verify");
		UserResponse actualUser = service.getAllUsers().get(0);
		UserResponse expectedUser = new UserResponse();
		expectedUser.setId(actualUser.getId());
		expectedUser.setUserType("member");
		expectedUser.setEmail("chloejohnsoncodes@gmail.com");
		expectedUser.setUsername("chloe");
		expectedUser.setPassword(actualUser.getPassword());
		Assertions.assertEquals(expectedUser, actualUser);
	}

	@Test
	void addUserAndVerify() {
		UserRegistration registration = new UserRegistration();
		registration.setUserType("member");
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
		User user = service.addUser(registration, "/verify");
		String retVal = service.verifyUser(user.getUserId());
		Assertions.assertEquals("verified", retVal);
	}

	@Test
	void addUserAndVerifyTokenExpired() {
		UserRegistration registration = new UserRegistration();
		registration.setUserType("member");
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
		User user = service.addUser(registration, "/verify");
		LocalDateTime tokenTime = user.getToken().getDateCreated().plusHours(25);
		UserToken token = new UserToken(user.getToken().getConfirmationToken(), tokenTime);
		service.updateToken(user.getToken().getId(), token);
		String retVal = service.verifyUser(user.getUserId());
		Assertions.assertEquals("resend_verification_email", retVal);
	}

	@Test
	void addUserAlreadyVerified() {
		UserRegistration registration = new UserRegistration();
		registration.setUserType("member");
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
		User user = service.addUser(registration, "/verify");
		user.setEnabled(1);
		service.updateUser(user.getUserId(), user);
		String retVal = service.verifyUser(user.getUserId());
		Assertions.assertEquals("user_already_verified", retVal);
	}

	@Test
	void resendVerificationEmail() {
		UserRegistration registration = new UserRegistration();
		registration.setUserType("member");
		registration.setEmail("chloejohnsoncodes@gmail.com");
		registration.setUsername("chloe");
		registration.setPassword("mypassword");
		User user = service.addUser(registration, "/verify");
		LocalDateTime tokenTime = user.getToken().getDateCreated().plusHours(25);
		UserToken token = new UserToken(user.getToken().getConfirmationToken(), tokenTime);
		service.updateToken(user.getUserId(), token);
		String prevToken = user.getToken().getConfirmationToken();
		service.resendVerificationEmail(user.getUserId(), "/verify");
		Assertions.assertNotEquals(service.getUserById(user.getUserId()).getToken().getConfirmationToken(), prevToken);
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
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		service.addMember(registration, "/valid");
		MemberResponse actualUser = service.getAllMembers().get(0);
		MemberResponse expectedMember = new MemberResponse();
		expectedMember.setId(actualUser.getId());
		expectedMember.setUserType("member");
		expectedMember.setEmail("chloejohnsoncodes@gmail.com");
		expectedMember.setUsername("chloe");
		expectedMember.setPassword(actualUser.getPassword());
		expectedMember.setFirstName("Chloe");
		expectedMember.setLastName("Johnson");
		expectedMember.setAddressLine1("1337 N Highwood Ave");
		expectedMember.setPhone("2089541744");
		expectedMember.setDateOfBirth(LocalDate.now());
		expectedMember.setSocialSecurityNumber("503-14-1234");
		expectedMember.setCity("Boise");
		expectedMember.setState("Idaho");
		expectedMember.setZipcode("83713");
		Assertions.assertEquals(expectedMember, actualUser);
	}

	@Test
	void addMemberAndVerify() {
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
		Member member = service.addMember(registration, "/verify");
		String retVal = service.verifyUser(member.getBankUser().getUserId());
		Assertions.assertEquals("verified", retVal);
	}

	@Test
	void addMemberAndVerifyTokenExpired() {
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
		Member member = service.addMember(registration, "/verify");
		LocalDateTime tokenTime = member.getBankUser().getToken().getDateCreated().plusHours(25);
		UserToken token = new UserToken(member.getBankUser().getToken().getConfirmationToken(), tokenTime);
		service.updateToken(member.getBankUser().getToken().getId(), token);
		String retVal = service.verifyUser(member.getBankUser().getUserId());
		Assertions.assertEquals("resend_verification_email", retVal);
	}
}
