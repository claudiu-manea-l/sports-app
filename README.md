
# Setup

Project was created in Android Studio Meerkat | 2024.3.1 Patch 2  
Utilises Kotlin 2.0, GradleW - 8.11.1 and AGP - 8.9.2

For ease of setup, import it in the same version of Android Studio, and everything else should be smooth

Potential Gatch - Ensure Gradle JDK is set to Java 21 ( File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle -> Gradle JDK )

# Features

I have managed to find time to only tackle the first part of the "homework" the Match details card. I do plan to come back and implement the Fixtures (Match list) to demonstrate the ease of scalability of the clean code approach.

Things missing, that I didn't get to due to lack of time.
* Proper UseCase/Interactor wrapper using a Result class. To allow error propagation through the UseCase chains.
* Extract the data, domain and presentation in to their own modules
* Handle date strings as Date instead of String (to gain better flexibility)
* UnitTests
* Improvements around the Compose UI.