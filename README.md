# NYTimesMostPopular


NYTimesMostPopular and show a list of articles,
 that shows details when items on the list are tapped.

 ### Tech

 - MVP pattern used.
 - Dagger, Retrofit, RxJava, Timber, Glide are used.
 - Android X and Kotlin extension are used.


 Build the project from the command-line

 ```sh
 $ git clone git@github.com:gobinda99/NYTimesMostPopular.git
 $ cd NYTimesMostPopular
 $ ./gradlew assembleDebug
 ```

 Run static code analysis such as linting

  ```sh
  $ ./gradlew lint
  ```

  Run unit tests and code coverage

   ```sh
   $ ./gradlew test
   ```

 SonarQube report for the code showing its quality summary

  ```sh
     $ ./gradlew sonarqube \
         -Dsonar.projectKey=gobinda99_NYTimesMostPopular \
         -Dsonar.organization=gobinda99-github \
         -Dsonar.host.url=https://sonarcloud.io \
         -Dsonar.login=53935205a45f1b78679ae5e986eaa76d334bf2d9
  ```





