package com.titanium.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="memberId")
    private int memberId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name="dateOfBirth", columnDefinition = "DATE")
    private LocalDate dateOfBirth;

    @Column(name="socialSecurityNumber")
    private String socialSecurityNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankUser", referencedColumnName = "userId")
    private User bankUser;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "member")
    private MemberAddress memberAddress;

    public Member(String firstName, String lastName, String phone, LocalDate dateOfBirth, String socialSecurityNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
    }
}