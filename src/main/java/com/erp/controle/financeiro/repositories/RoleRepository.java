package com.erp.controle.financeiro.repositories;

import com.erp.controle.financeiro.entities.RoleModel;
import com.erp.controle.financeiro.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    boolean existsByRoleName(RoleName roleName);
}
