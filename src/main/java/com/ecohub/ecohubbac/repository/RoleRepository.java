package com.ecohub.ecohubbac.repository;
import com.ecohub.ecohubbac.entity.Role;
import com.ecohub.ecohubbac.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
