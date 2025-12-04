package br.com.nodelayapp.api;

import br.com.nodelayapp.domain.Student;
import br.com.nodelayapp.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
  private final StudentRepo repo;

  @PostMapping
  public Student create(@RequestBody Student s) { return repo.save(s); }
}
