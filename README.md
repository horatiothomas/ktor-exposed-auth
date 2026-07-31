# ktor-minstart

Minimal starter code for a ktor project

- Uses Netty as the server engine
- Uses yaml for declarative configuration

## Running
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

### Customization
- Change the root project name in `settings.gradle.kts`
- Update the package directory structure and reference to the routing functionin `application.yaml`

#### Auto Reload Support Notes
- Uses ktor version 3.4.3 so autoreload can be used. Will upgrade to 3.5.2 when that bug is fixed.
- Uses gradle over kotlin toolchain because autoreload doesn't work in kotlin toolchain and gradle is still the industry standard
