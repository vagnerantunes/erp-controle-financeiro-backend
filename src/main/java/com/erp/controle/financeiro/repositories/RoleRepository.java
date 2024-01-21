package com.erp.controle.financeiro.repositories;

import com.erp.controle.financeiro.entities.RoleModel;
import com.erp.controle.financeiro.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(RoleName roleName);
}
