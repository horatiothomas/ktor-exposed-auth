# ktor-exposed-auth

This is an example project demonstrating how to manage ktor authentication with an exposed database

## Running

### Generate a hex secret for HMAC SHA-256

Run the following command to generate a hex secret for HMAC SHA-256
```
openssl rand -hex 14
```
Add it to your environment
```
export HS_SECRET=your_generated_secret
```
### Setting up a Database
Setup a database either local or remote and then add the connection info to your environment
Exposed will infer the database type from the connection url
#### IMPORTANT: This project uses features supported only by the following database types: PostgreSQL, SQLite, and MariaDB
```bash
export DB_URL=your_database_url
export DB_USERNAME=your_database_username
export DB_PASSWORD=your_database_password
```

#### Run both of the following commands in separate terminals for auto reload support.
To observe file changed and rebuild the project for instant updates
```
./gradlew -t :ktor:build 
```
To start the ktor server
```
./gradlew -t :ktor:run -Dio.ktor.development=true
```

### Formatting
Run the following to format the project.
```
./gradlew ktfmtFormat 
```

#### Auto Reload Support Notes
- Uses ktor version 3.4.3 so autoreload can be used. Will upgrade to 3.5.2 when that bug is fixed.
- Uses gradle over kotlin toolchain because autoreload doesn't work in kotlin toolchain and gradle is still the industry standard
