package com.smoothstack.titaniumbanking.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

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

    @Column(name="accountTypeColor")
    private String accountTypeColor;

    @Column(name="interest")
    private Integer interest;

    @Column(name="balanceRequirement")
    private Long balanceRequirement;

    @Column(name="loanId")
    private Integer loanId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountType")
    private List<Account> accounts;

    @JsonIgnore
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
    @JsonIgnore List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if(getClass() != o.getClass()){
            return false;
        }
        AccountType accountTypeObj = (AccountType) o;
        return (
                accountTypeAbbr.equals(accountTypeObj.getAccountTypeAbbr()) &&
                        accountType.equals(accountTypeObj.getAccountType()) &&
                        interest.equals(accountTypeObj.getInterest()) &&
                        accountTypeColor.equals(accountTypeObj.getAccountTypeColor())
        );
    }

    public AccountType(String accountType, String accountAbbr, String accountTypeColor, Integer interest, Long balanceRequirement){
        super();
        this.accountType = accountType;
        this.accountTypeAbbr = accountAbbr;
        this.accountTypeColor = accountTypeColor;
        this.interest = interest;
        this.balanceRequirement = balanceRequirement;
        this.loanId = 0;
    }
}