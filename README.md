# Diplom_3
Проект использует Java 11;

Используемые библиотеки: Selenium, Webdrivermanager, JUnit 4, Rest-Assured, Lombok, Allure;

Чтобы запустить тесты в браузере yandex, необходимо выполнить команду: mvn clean test -Dbrowser=yandex, где в параметр Dbrowser можно передать yandex;

Чтобы просмотреть собранный allure report по выполненному прогону тестов, необходимо выполнить команду: mvn allure:serve.
