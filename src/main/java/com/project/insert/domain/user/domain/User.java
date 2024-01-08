package com.project.insert.domain.user.domain;

import com.project.insert.domain.user.domain.authority.Authority;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id @GeneratedValue
    private Long id;


    @Column(unique = true, length = 32)
    private String email;

    @Column(length = 16)
    private String nickname;

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    private int enroll;

    private int grade;

    private int class_number;

    private int student_number;

    public User update(BsmResourceResponse resource) {
        this.email = resource.getEmail();
        this.nickname = resource.getNickname();
        return this;
    }
}
