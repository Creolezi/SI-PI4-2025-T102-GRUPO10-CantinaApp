package br.com.nodelayapp.repo;

import br.com.nodelayapp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
  Optional<Student> findByEmail(String email);
}
