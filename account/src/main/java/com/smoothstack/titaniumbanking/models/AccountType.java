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

    @Column(name="savings")
    private boolean savings;

    @Column(name="investing")
    private boolean investing;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account", referencedColumnName = "accountTypeId")
    @JsonBackReference
    private Account account;

    public AccountType(boolean savings, boolean investing){
        super();
        this.savings = savings;
        this.investing = investing;
    }
}
