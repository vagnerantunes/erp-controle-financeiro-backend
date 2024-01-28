package com.erp.controle.financeiro.repositories;

import com.erp.controle.financeiro.entities.RoleModel;
import com.erp.controle.financeiro.entities.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    @Modifying
    @Query("UPDATE UserModel u SET u.username = :username, u.password = :password, u.roles = :roles WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("username") String username, @Param("password") String password, @Param("roles") RoleModel role);


}
