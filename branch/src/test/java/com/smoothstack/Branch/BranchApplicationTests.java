package com.smoothstack.Branch;

import com.smoothstack.Branch.controller.BranchController;
import com.smoothstack.Branch.dto.BranchDto;
import com.smoothstack.Branch.model.Branch;
import com.smoothstack.Branch.service.BranchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BranchApplicationTests {

	@Autowired
	BranchController controller;
	@Autowired
	BranchService branchService;
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
		branchService.removeAllBranches();
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
		Assertions.assertNull(branchService.findBranch(b.getBranchId()));
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
}
