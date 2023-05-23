package com.amolsoftwares.www.mymultithreadingapp.repository;

import com.amolsoftwares.www.mymultithreadingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
