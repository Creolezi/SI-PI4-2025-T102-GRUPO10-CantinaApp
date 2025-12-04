package br.com.nodelayapp.api;

import br.com.nodelayapp.domain.MenuItem;
import br.com.nodelayapp.repo.MenuItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
  private final MenuItemRepo menuRepo;

  @GetMapping
  public List<MenuItem> list() { return menuRepo.findAll(); }
}
