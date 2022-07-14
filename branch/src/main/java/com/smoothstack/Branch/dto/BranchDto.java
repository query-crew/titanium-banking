package com.smoothstack.Branch.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BranchDto {
    private int branchId;
    private String branchName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}
