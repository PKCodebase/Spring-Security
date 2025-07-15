# Database Configuration Setup

## Environment Variables Required

Set these environment variables before running the application:

### Development Environment
```bash
export DB_URL=jdbc:postgresql://10.197.214.54:5432/SWAGUM
export DB_USERNAME=postgres
export DB_PASSWORD=your_actual_password
```

### Windows (Command Prompt)
```cmd
set DB_URL=jdbc:postgresql://10.197.214.54:5432/SWAGUM
set DB_USERNAME=postgres
set DB_PASSWORD=your_actual_password
```

### Windows (PowerShell)
```powershell
$env:DB_URL="jdbc:postgresql://10.197.214.54:5432/SWAGUM"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_actual_password"
```

## Alternative: Use Local Profile

Run with local profile:
```bash
java -jar app.jar --spring.profiles.active=local
```

## Production Deployment

For production, set environment variables in your deployment platform:
- Docker: Use docker-compose.yml or Kubernetes secrets
- Cloud: Use platform-specific secret management
- Server: Set system environment variables

## Security Notes

1. Never commit passwords to version control
2. Use different passwords for different environments
3. Rotate passwords regularly
4. Use strong passwords (minimum 12 characters)
5. Consider using database connection pooling with encrypted connections