<h1>Deposit-Control</h1>
<h3>Описание</h3>
Данное приложение cоздавалось в рамках тестового задания.
Для проверки работоспособности или пользования приложением, необходимо предварительно создать базу данных PostgreSQL.
Возможно использовать не PostgreSQl, но убедитесь, что изменили конфигурацию в application.properties для своей БД.

Приложение не имеет web-интерфейса, поэтому для работы воспользуйтесь удобным для Вас HTTP-клиентом.

Приложение протестировано на Postman, были реализованы и протестированы все CRUD-операции, с помощью запросов.

Сущность клиента имеет поле clientLegalForm. Данное поле отвечает за организационно-правовую форму. Поскольку подобных форм очень много, они были вынесены в основные 3:
- ЮКО (Юридическое лицо Коммерческой организации)
- ЮНО (Юридическое лицо Некоммерческой организации)
- ОбОЮЛ (Организация без Образования Юридического Лица)

Для проверки соединения базы данных с серверной частью дополнительно создан класс DatabaseConnectionChecker, и unit-тест для него.

<h3>Запуск</h3>
Запуск приложения производится через INTELLIJ IDEA (пока что).