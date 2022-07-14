package com.titanium.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "confirmationToken")
    private String confirmationToken;

    @Column(name="dateCreated", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateCreated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankUser", referencedColumnName = "userId")
    @JsonBackReference
    private BankUser bankUser;

    public UserToken(String confirmationToken, LocalDateTime dateCreated) {
        super();
        this.confirmationToken = confirmationToken;
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object obj) throws NullPointerException {
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserToken token = (UserToken) obj;
        return (confirmationToken.equals(token.getConfirmationToken()) &&
                dateCreated.equals(token.getDateCreated()));
    }
}
