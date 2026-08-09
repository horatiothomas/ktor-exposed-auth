# ktor-exposed-auth

This is an example project demonstrating how to manage ktor authentication with an exposed database

## Running

### Generate a hex secret for HMAC SHA-256

Run the following command to generate a hex secret for HMAC SHA-256
```bash
openssl rand -hex 14
```
Add it to your environment
```bash
export HS_SECRET=your_generated_secret
```
### Setting up a Database
Create a postgres database and add the configuration information to your environment like so:
```bash
export DB_URL=your_database_url
export DB_USERNAME=your_database_username
export DB_PASSWORD=your_database_password
```
Connect to your database and run the migrations in ktor/src/main/resources/db/migration. You can even do so in Intellij. 

### Configuring Cookies

Set the secure_cookies environment variable to true if you want to use secure cookies. This is recommended for production environments.
In local development, you would want to set it to false.
```bash
export SECURE_COOKIES=true
```

### Running the local server

#### Run both of the following commands in separate terminals for auto reload support.
To observe file changed and rebuild the project for instant updates
```bash
./gradlew -t :ktor:build 
```
To start the ktor server
```bash
./gradlew -t :ktor:run -Dio.ktor.development=true
```

### Formatting
Run the following to format the project.
```bash
./gradlew ktfmtFormat 
```
