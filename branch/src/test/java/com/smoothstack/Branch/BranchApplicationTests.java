package com.smoothstack.Branch;

import com.smoothstack.Branch.controller.BranchController;
import com.smoothstack.Branch.dto.BranchDto;
import com.smoothstack.Branch.exception.BranchNotFoundException;
import com.smoothstack.Branch.model.Branch;
import com.smoothstack.Branch.service.BranchService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BranchApplicationTests {

	@Autowired
	BranchController controller;
	@Autowired
	BranchService branchService;

	@BeforeEach
	@AfterEach
	void removeAllBranches() {
		branchService.removeAllBranches();
	}
	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

	//Create and Read
	@Test
	void addBranchAndRead() {
		BranchDto bDto = new BranchDto();
		bDto.setBranchName("Test Name 1");
		bDto.setAddressLine1("Test Line 1");
		bDto.setAddressLine2("Test Line 2");
		bDto.setCity("City 1");
		bDto.setState("State 1");
		bDto.setZipCode("Zip 1");
		Branch b = branchService.addBranchTest(bDto);
		bDto.setBranchId(b.getBranchId());
		//test dto conversion
		Assertions.assertEquals(bDto.toString(), branchService.toDto(b).toString());
		//test added to db
		Assertions.assertNotNull(branchService.findBranch(b.getBranchId()));
	}
	//Update
	@Test
	void updateBranch() {
		BranchDto bDto = new BranchDto();
		bDto.setBranchName("Test Name 1");
		bDto.setAddressLine1("Test Line 1");
		bDto.setAddressLine2("Test Line 2");
		bDto.setCity("City 1");
		bDto.setState("State 1");
		bDto.setZipCode("Zip 1");
		//add new branch
		Branch b = branchService.addBranchTest(bDto);
		bDto.setBranchId(b.getBranchId());
		bDto.setBranchName("New name");
		//update branch with new name
		branchService.updateBranch(bDto.getBranchId(), bDto);
		Branch bUpdated = branchService.findBranch(bDto.getBranchId());
		//test for update
		Assertions.assertEquals(bDto.toString(), branchService.toDto(bUpdated).toString());
	}
	//Delete
	@Test
	void deleteBranch() {
		BranchDto bDto = new BranchDto();
		bDto.setBranchName("Test Name 1");
		bDto.setAddressLine1("Test Line 1");
		bDto.setAddressLine2("Test Line 2");
		bDto.setCity("City 1");
		bDto.setState("State 1");
		bDto.setZipCode("Zip 1");
		Branch b = branchService.addBranchTest(bDto);
		//added to db
		Assertions.assertNotNull(branchService.findBranch(b.getBranchId()));
		branchService.removeBranch(b.getBranchId());
		//removed from db
		Assertions.assertThrows(BranchNotFoundException.class, () -> branchService.findBranch(b.getBranchId()));
	}

	@Test
	void deleteAll() {
		BranchDto bDto = new BranchDto();
		bDto.setBranchName("Test Name 1");
		bDto.setAddressLine1("Test Line 1");
		bDto.setAddressLine2("Test Line 2");
		bDto.setCity("City 1");
		bDto.setState("State 1");
		bDto.setZipCode("Zip 1");
		branchService.addBranchTest(bDto);
		//has branches
		Assertions.assertTrue(branchService.hasBranches());
		branchService.removeAllBranches();
		//does not have branches
		Assertions.assertFalse(branchService.hasBranches());
	}

	@Test
	void searchBranches() {
		BranchDto bDto1 = new BranchDto();
		bDto1.setBranchName("Test Name 1");
		bDto1.setAddressLine1("Test Line 1");
		bDto1.setAddressLine2("Test Line 2");
		bDto1.setCity("City 1");
		bDto1.setState("State 1");
		bDto1.setZipCode("Zip 1");
		bDto1.setBranchDetails("These are the deets");
		BranchDto bDto2 = new BranchDto();
		bDto2.setBranchName("Test Name 1");
		bDto2.setAddressLine1("Test Line 1");
		bDto2.setAddressLine2("Test Line 2");
		bDto2.setCity("City 1");
		bDto2.setState("State 1");
		bDto2.setZipCode("Zip 1");
		bDto2.setBranchDetails("These are the deets");
		BranchDto bDto3 = new BranchDto();
		bDto3.setBranchName("Should not show up");
		bDto3.setAddressLine1("Test Line 1");
		bDto3.setAddressLine2("Test Line 2");
		bDto3.setCity("City 1");
		bDto3.setState("State 1");
		bDto3.setZipCode("Zip 1");
		bDto3.setBranchDetails("this branch should not be found");
		Branch b1 = branchService.addBranchTest(bDto1);
		Branch b2 = branchService.addBranchTest(bDto2);
		Branch b3 = branchService.addBranchTest(bDto3);

		Map<String, Object> res = branchService.findAllBranches("test", 0, 5);
		List<Branch> list = new ArrayList<>((Collection<Branch>) res.get("branches"));
		Map<String, Object> resEmpty = branchService.findAllBranches("blah", 0, 5);
		List<Branch> listEmpty = new ArrayList<>((Collection<Branch>) resEmpty.get("branches"));
		Assertions.assertEquals(2, list.size());
		Assertions.assertEquals(0, listEmpty.size());
	}
}
