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

    @Override
    public boolean equals(Object obj) throws NullPointerException {
        if (getClass() != obj.getClass()) {
            return false;
        }
        Member member = (Member) obj;
        return (member.getMemberId() == memberId &&
                member.getFirstName().equals(firstName) &&
                member.getLastName().equals(lastName) &&
                member.getPhone().equals(phone) &&
                member.getDateOfBirth().equals(dateOfBirth) &&
                member.getSocialSecurityNumber().equals(socialSecurityNumber) &&
                member.getMemberAddress().equals(memberAddress));
    }
}