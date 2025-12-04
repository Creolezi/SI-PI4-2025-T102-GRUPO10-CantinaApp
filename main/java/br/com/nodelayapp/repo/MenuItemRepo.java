package br.com.nodelayapp.repo;

import br.com.nodelayapp.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {}
