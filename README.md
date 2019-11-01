# Multiplatform sample

This example shows how to use Ktor Kotlin/Native in the multiplatform world.

This sample based on the [multiplatform documentation](https://github.com/h0tk3y/k-new-mpp-samples/blob/master/README.md).
If you have questions about the structure or how it works take a look at the documentation there.

## iOS

To compile the project from Xcode just open `iosApp/iosApp.xcodeproj` and run the application.

To compile a framework and link framework for ios simulator from the command line execute:

```
  > ./gradlew :common:linkFrameworkIos
```

To compile the framework for a device use the `device` project property:

```
  > ./gradlew :common:linkFrameworkIos -Pdevice=true
```
## Android
> Android sample is Work in Progress

## Backend
To run backend move to `backendApp` directory and run:

```
> ./gradlew run
```
This will start application on port 8080, on localhost.