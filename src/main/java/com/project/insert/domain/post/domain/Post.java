package com.project.insert.domain.post.domain;

import com.project.insert.domain.answer.domain.Answer;
import com.project.insert.domain.comment.domain.Comment;
import com.project.insert.domain.post.presentation.dto.PostRequestDto;
import com.project.insert.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column
    private LocalDate createdAt;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<Answer> answers;

    @OneToMany
    @JoinColumn(name="post_id")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name="author")
    private User author;

    public Post(String title, String content, PostType postType, User author){
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.createdAt = LocalDate.now();
        this.author = author;
    }
}
