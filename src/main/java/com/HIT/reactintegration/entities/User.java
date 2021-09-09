package com.HIT.reactintegration.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity (name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "image")
    private String eventImage;

    public User(String nickname) {
        this.nickname = nickname;
    }
}
