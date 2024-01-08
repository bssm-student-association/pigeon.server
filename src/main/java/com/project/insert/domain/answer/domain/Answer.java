package com.project.insert.domain.answer.domain;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @Column
    private LocalDate createdAt;

    @Column(name = "post_id")
    private Long postId;

    @ManyToOne
    @JoinColumn(name="author")
    private User author;

    public Answer(String content, Long postId, User author) {
        this.content = content;
        this.postId = postId;
        this.author = author;
        this.createdAt = LocalDate.now();
    }

    public void update(String content) {
        this.content = content;
    }
}
