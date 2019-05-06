# NY Times Most Popular


NY Times Most Popular show as list of articles and its
 details when items on the list are tapped.

 ### Tech

 - MVP pattern used.
 - Dagger, Retrofit, RxJava, Timber, Glide are used.
 - Android X and Kotlin extension are used.
 -> Test cases is pending


 Build the project from the command-line

 ```sh
 $ git clone git@github.com:gobinda99/NYTimesMostPopular.git
 $ cd NYTimesMostPopular
 $ ./gradlew assembleDebug
 ```

 Run static code analysis such as linting. The result will generate on path app/build/reports/lint-results.html

  ```sh
  $ ./gradlew lint
  ```

  Run unit tests and code coverage,

   ```sh
   $ ./gradlew test
   ```

 Run SonarQube from command line to generate report

  ```sh
     $ ./gradlew sonarqube \
         -Dsonar.projectKey=gobinda99_NYTimesMostPopular \
         -Dsonar.organization=gobinda99-github \
         -Dsonar.host.url=https://sonarcloud.io \
         -Dsonar.login=53935205a45f1b78679ae5e986eaa76d334bf2d9
  ```

  Here is the link to see [SonacQube Report](https://sonarcloud.io/project/issues?id=gobinda99_NYTimesMostPopular&resolved=false) of the project.









