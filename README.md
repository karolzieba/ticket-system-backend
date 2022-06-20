[PL] Projekt został wykonany jako projekt studencki w ramach przedmiotu "Inżynieria systemów informacyjnych" w czerwcu 2022 roku. Jest to back - end aplikacji do kupowania biletów na różne wydarzenia. W aplikacji istnieją trzy rodzaje kont: klient, agencja oraz moderator. Każdy z nich ma swoje własne, unikalne dla siebie funkcje. Ta warstwa aplikacji została napisana w języku Java przy użyciu frameworka Spring Boot. Wykorzystano tutaj również mechanizm sesji, Hibernate'a i PostgreSQL jako bazę danych. Znajduje się tutaj integracja z API Facebooka w celu logowania przy jego pomocy oraz z API PayPal w celu możliwości płatności.

Repozytorium front - endu aplikacji: https://github.com/karolzieba/ticket-system-frontend

Jak uruchomić projekt:

1. Należy utworzyć bazę danych, na przykład za pomocą polecenia "CREATE DATABASE ticketsystem;" w naszym DBMS.
2. W pliku "src/main/resources/application.properties" należy skonfigurować połączenie z naszą bazą danych oraz uzupełnić dane do integracji aplikacji z API PayPal.
3. W pliku "src/main/resources/application.yml" należy uzupełnić dane związane z integracją aplikacji z API Facebook.
4. W folderze "src/main/resources" należy stworzyć katalog o nazwie "img", w którym przechowywane będą obrazy dodanych do systemu wydarzeń.
5. W terminalu naszego IDE wpisujemy polecenie "./mvnw spring-boot:run" lub "mvnw spring-boot:run".
6. Po uruchomieniu aplikacji należy uruchomić również jej warstwę front - endową.

W przypadku występujących problemów można spróbować przeładować projekt w Mavenie (pom.xml -> Maven -> Reload Project).

[ENG] This project was done as a student project at "Information Systems Engineering" subject at June 2022. This is a back - end of the application for buying event tickets. In application there are three types of accounts: client, agency and moderator. Every of them has his own specific functions. This layer of application was written in Java language using the Spring Boot framework. It also uses session mechanism, Hibernate and PostgreSQL as a database. There is integration with Facebook API for logging in with Facebook and with PayPal API for payments.

Front - end repository: https://github.com/karolzieba/ticket-system-frontend

How to run the project:

1. You have to create database, for example by using "CREATE DATABASE ticketsystem;" command in our DBMS.
2. In "src/main/resources/application.properties" file, configure connection with database and fill data for integration with PayPal API.
3. In "Src/main/resources/application.yml" file, fill data for integration with Facebook API.
4. In "src/main/resources" create directory with name "img", where events images will be stored.
5. In IDE terminal type command "./mvnw spring-boot:run" or "mvnw spring-boot:run".
6. After start of application, you should run its front - end layer also.

In the case of problems, we can try reload project in Maven (pom.xml -> Maven -> Reload Project).