package com.titanium.user.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private User bankUser;

    public UserToken(String confirmationToken, LocalDateTime dateCreated) {
        super();
        this.confirmationToken = confirmationToken;
        this.dateCreated = dateCreated;
    }
}
