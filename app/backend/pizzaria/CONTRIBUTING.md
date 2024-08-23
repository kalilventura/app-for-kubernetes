# Contributing to Pizzaria

## Getting Started
### Prerequisites

Before you begin, ensure you have the following tools installed:

.NET SDK
Entity Framework Core CLI

### Setting Up the Project

#### Fork the Repository
Fork the repository to your GitHub account and clone it to your local machine.
```bash
git clone https://github.com/kalilventura/app-for-kubernetes.git
cd app-for-kubernetes
```

#### Install Dependencies
Restore the project dependencies using the .NET CLI:
```bash
dotnet restore
```

#### Set Up the Database
Update the appsettings.Development.json file with your database connection string. The connection string should point to your development SQL Server instance.
```json
"ConnectionStrings": {
  "DefaultConnection": "Server=localhost;Database=YourDatabase;User Id=your-username;Password=your-password;"
}
```

#### Apply Migrations
If there are any pending migrations, apply them to your database:
```bash
dotnet ef database update
```

### Database Migrations

If you need to make changes to the database schema, follow these steps:

#### Create a Migration
Generate a new migration after making changes to your Entity Framework Core models:

```bash
dotnet ef migrations add YourMigrationName
```

#### Apply the Migration
Apply the new migration to your database:

```bash
dotnet ef database update
```

### Review Migrations
Ensure that your migration files are correctly generated and do not contain any unnecessary changes.
