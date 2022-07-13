package com.titanium.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="member_address")
public class MemberAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="addressId")
    private int addressId;

    @Column(name = "addressLine1")
    private String addressLine1;

    @Column(name="addressLine2")
    private String addressLine2;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipCode")
    private String zipCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member", referencedColumnName = "memberId")
    private Member member;

    public MemberAddress(String addressLine1, String addressLine2, String city, String state, String zipCode) {
        super();
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) throws NullPointerException {
        if (obj.getClass() != getClass()) {
            return false;
        }
        MemberAddress address = (MemberAddress) obj;
        if (addressLine2 == null) {
            return (address.getAddressId() == addressId &&
                    address.getAddressLine1().equals(addressLine1) &&
                    address.getCity().equals(city) &&
                    address.getState().equals(state) &&
                    address.getZipCode().equals(zipCode));
        }
        return (address.getAddressId() == addressId &&
                address.getAddressLine1().equals(addressLine1) &&
                address.getAddressLine2().equals(addressLine2) &&
                address.getCity().equals(city) &&
                address.getState().equals(state) &&
                address.getZipCode().equals(zipCode));
    }
}