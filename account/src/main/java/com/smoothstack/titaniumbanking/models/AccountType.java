package com.smoothstack.titaniumbanking.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="Accounts_Types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_type_id")
    private int accountTypeId;

    @Column(name="savings", nullable = true)
    private Boolean savings;

    @Column(name="investing", nullable = true)
    private Boolean investing;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "accountType")
    @JsonBackReference
    private Account account;

    public AccountType(boolean savings, boolean investing){
        super();
        this.savings = savings;
        this.investing = investing;
    }
}
