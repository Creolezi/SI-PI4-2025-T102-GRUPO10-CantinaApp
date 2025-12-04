package br.com.nodelayapp.repo;

import br.com.nodelayapp.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {}
