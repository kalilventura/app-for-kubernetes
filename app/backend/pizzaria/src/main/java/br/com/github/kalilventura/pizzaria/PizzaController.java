package br.com.github.kalilventura.pizzaria;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/v1/pizzas")
public class PizzaController {

    private final JpaPizzaRepository repository;

    public PizzaController(final JpaPizzaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<JpaPizza>> getAll() {
        final var pizzas = repository.findAll();
        if (pizzas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JpaPizza> getById(@PathVariable Long id) {
        final var pizza = repository.findById(id);
        return pizza
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<JpaPizza> create(@RequestBody JpaPizza pizza) {
        final var created = repository.save(pizza);
        return ResponseEntity
                .created(URI.create("/v1/pizzas/"+created.getId()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
