package com.titanium.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name="bankUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private int userId;

    @Column(name = "userType")
    private String userType;

    @Column(name="email")
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private int enabled;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bankUser")
    private UserToken token;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "bankUser")
    private Member member;

    public User(String userType, String email, String username, String password) {
        super();
        this.userType = userType;
        this.email = email;
        this.username = username;
        this.password = password;
        this.enabled = 0;
    }

    @Override
    public boolean equals(Object obj) throws NullPointerException {
        if (getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        if (member != null) {
            return (user.userId == userId &&
                    user.getUserType().equals(userType) &&
                    user.getEmail().equals(email) &&
                    user.getUsername().equals(username) &&
                    user.getPassword().equals(password) &&
                    member.equals(user.getMember()) &&
                    token.equals(user.getToken()));
        }
        return (user.userId == userId &&
                user.getUserType().equals(userType) &&
                user.getEmail().equals(email) &&
                user.getUsername().equals(username) &&
                user.getPassword().equals(password) &&
                token.equals(user.getToken()));
    }
}
