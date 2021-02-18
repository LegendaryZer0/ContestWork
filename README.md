# ContestWork
**Подготовка:**

1) В **SimpleRepository**, расположенному по пути ContestWork/java/src/ru/company/repository/SimpleRepository.java , нужно указать свои properties(url,password,username от бд), в полях класса URL,PASSWORD,USERNAME соответственно.

2) Необходим PostgreSQL JDBC  Driver (можно скачать например отсюда https://mvnrepository.com/artifact/org.postgresql/postgresql) 



**Запуск:**

**Main class** распололжен  по пути /java/src/ru/company/app/Main.java , достаточно указать ссылку на желаемый сайт 


**Примечания:**

1. **View** в пакете view отвечает за показ в консоли

2. В пакете util- находится класс **Extractor**, отвественный за извлечение слов из html

3. Страница сайта сохраняется в **домашнюю директорию пользователя**

4. Пакет logger ответственнен за сохранение ошибок в error.log файл
