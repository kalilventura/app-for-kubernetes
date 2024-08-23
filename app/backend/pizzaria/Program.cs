using backend.main.todos;
using HealthChecks.UI.Client;
using HealthChecks.UI.Configuration;
using Microsoft.AspNetCore.Diagnostics.HealthChecks;
using Microsoft.EntityFrameworkCore;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddEntityFrameworkNpgsql().AddDbContext<PizzaDb>(opt =>
{
    opt.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection"));
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(s =>
{
    s.SwaggerDoc("v1", new OpenApiInfo { Title = "Pizza API", Description = "REST API", Version = "0.0.1" });
});

builder.Services.AddHealthChecks();

var app = builder.Build();
if (app.Environment.IsDevelopment())
    app.UseDeveloperExceptionPage();

app.MapHealthChecks("/health", new HealthCheckOptions
{
    Predicate = _ => true,
    ResponseWriter = UIResponseWriter.WriteHealthCheckUIResponse
});
app.UseHealthChecksUI(delegate(Options options) { options.UIPath = "/healthcheck-ui"; });

app.UseSwagger();
app.UseSwaggerUI(s => { s.SwaggerEndpoint("/swagger/v1/swagger.json", "Pizza API"); });

app.MapGet("/pizzas", async (PizzaDb db) =>
{
    var pizzas = await db.Pizzas.ToListAsync();
    return Results.Ok(pizzas);
}).WithDescription("Find all pizzas");

app.MapGet("/pizzas/{id:int}", async (int id, PizzaDb db) =>
{
    var pizza = await db.Pizzas.FindAsync(id);
    return pizza is null ? Results.NotFound() : Results.Ok(pizza);
}).WithDescription("Get a pizza by unique identifier");

app.MapPost("/pizzas", async (Pizza pizza, PizzaDb db) =>
{
    db.Pizzas.Add(pizza);
    await db.SaveChangesAsync();

    return Results.Created($"/pizzas/{pizza.Id}", pizza);
}).WithDescription("Create a pizza");

app.MapDelete("/pizzas/{id:int}", async (int id, PizzaDb db) =>
{
    var pizza = await db.Pizzas.FindAsync(id);
    if (pizza is null)
        return Results.NotFound();

    db.Pizzas.Remove(pizza);
    await db.SaveChangesAsync();

    return Results.NoContent();
}).WithDescription("Delete a pizza");

app.Run();