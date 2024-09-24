package br.com.github.kalilventura.pizzaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "pizzas")
@RequestMapping("/v1/pizzas")
public class PizzaController {

    private final JpaPizzaRepository repository;

    public PizzaController(final JpaPizzaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(
            summary = "Fetch all pizzas",
            description = "Fetches all pizzas from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All pizzas"),
            @ApiResponse(responseCode = "204", description = "There are no pizzas to show"),
    })
    public ResponseEntity<List<JpaPizza>> getAll() {
        final var pizzas = repository.findAll();
        if (pizzas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get by id",
            description = "Get the pizza by the target id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "There is a pizza to show"),
            @ApiResponse(responseCode = "204", description = "There is no pizza to show"),
    })
    public ResponseEntity<JpaPizza> getById(@PathVariable Long id) {
        final var pizza = repository.findById(id);
        return pizza
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    @Operation(
            summary = "Create a new pizza",
            description = "Save a new pizza on the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The pizza was created"),
    })
    public ResponseEntity<JpaPizza> create(@RequestBody JpaPizza pizza) {
        final var created = repository.save(pizza);
        return ResponseEntity
                .created(URI.create("/v1/pizzas/"+created.getId()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete by id",
            description = "Delete the target pizza by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The pizza was deleted"),
    })
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
