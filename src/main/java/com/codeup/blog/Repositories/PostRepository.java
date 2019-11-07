package com.codeup.blog.Repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long>{
//    Post findByTitle(String title);
//
//    @Query("from Ad a where a.id like ?1")
//    Post getPostById(long id);
//
//    @Query("from Ad a where a.title like %:term%")
//    List<Post> searchByTitleLike(@Param("term") String term);
}
