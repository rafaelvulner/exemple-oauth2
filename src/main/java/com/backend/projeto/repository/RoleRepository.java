package com.backend.projeto.repository;

import com.backend.projeto.entity.Role;
import com.backend.projeto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
