package com.smoothstack.Branch.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tbl_branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private int branchId;
    @Column(name = "branch_name")
    private String branchName;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "branch")
    private Address address;
    public Branch (String branchName) {
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return ("id: " + branchId + ", name: " + branchName + ", address: " + address.toString());
    }
}
