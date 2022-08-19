package com.smoothstack.titaniumbanking.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name ="accountType")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountTypeId", unique = true, nullable = false)
    private int accountTypeId;

    @Column(name="accountType", nullable = false)
    private String accountType;

    @Column(name="accountAbbr")
    private String accountTypeAbbr;

    @Column(name="interest")
    private Integer interest;

    @Column(name="balanceRequirement")
    private Long balanceRequirement;

    @Column(name="loanId")
    private Integer loanId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountId")
    @JsonBackReference
    private List<Account> account;

    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass()){
            return false;
        }
        AccountType accountTypeObj = (AccountType) o;
        return (
                accountTypeAbbr.equals(accountTypeObj.getAccountTypeAbbr()) &&
                        accountType.equals(accountTypeObj.getAccountType()) &&
                        interest.equals(accountTypeObj.getInterest())
        );
    }

    public AccountType(String accountType, String accountAbbr, Integer interest, Long balanceRequirement){
        super();
        this.accountType = accountType;
        this.accountTypeAbbr = accountAbbr;
        this.interest = interest;
        this.balanceRequirement = balanceRequirement;
        this.loanId = 0;
    }
}