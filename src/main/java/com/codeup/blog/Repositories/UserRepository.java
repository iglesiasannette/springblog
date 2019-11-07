package com.codeup.blog.Repositories;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {




}
