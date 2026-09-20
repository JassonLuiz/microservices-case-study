package br.com.jassonluizjr.userservice.repository;

import br.com.jassonluizjr.userservice.domain.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
