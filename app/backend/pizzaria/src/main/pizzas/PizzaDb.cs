using Microsoft.EntityFrameworkCore;

namespace backend.main.todos;

public class PizzaDb(DbContextOptions<PizzaDb> options) : DbContext(options)
{
    public DbSet<Pizza> Pizzas => Set<Pizza>();
}
