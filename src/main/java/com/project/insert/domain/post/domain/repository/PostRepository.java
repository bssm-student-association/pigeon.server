package com.project.insert.domain.post.domain.repository;

import com.project.insert.domain.post.domain.Post;
import com.project.insert.domain.post.domain.PostType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPostTypeOrderByCreatedAtDesc(PostType postType, PageRequest pageRequest);

    List<Post> findByPostType(PostType postType);
}
