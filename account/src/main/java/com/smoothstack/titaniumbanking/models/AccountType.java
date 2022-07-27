package com.smoothstack.titaniumbanking.models;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "accountTypeId")
    private Account account;

    public AccountType(boolean savings, boolean investing){
        super();
        this.savings = savings;
        this.investing = investing;
    }
}
