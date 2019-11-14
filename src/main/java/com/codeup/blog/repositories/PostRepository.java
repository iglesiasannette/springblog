package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository <Post, Long>{

    List<Post> findByTitleContaining(@Param("term") String term);

}
