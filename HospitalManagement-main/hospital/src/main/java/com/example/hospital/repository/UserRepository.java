package com.example.hospital.repository;

import com.example.hospital.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {



    Optional<User> findByName(String userName);

    Page<User> searchAllByNameLike(String s, Pageable paging);
}
