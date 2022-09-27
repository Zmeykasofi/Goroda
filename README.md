# ИГРА В ГОРОДА

Игроки по очереди называют города, главное правило - следующий город должен начинаться на ту же букву, на которую заканчивался последний названный город.

В нашей игре игроками являются клиенты, подключающиеся к серверу; сервер лишь контролирует игру, сам в неё не играя.

Первый игрок (первый подключающийся после старта сервера клиент) на вход от сервера получает строку "???" - так клиент понимает, что он первый игрок и может назвать любой город. Назвать = отправить город в виде одной строки на сервер, сервер в ответ отправляет строку "OK".

Все остальные игроки (т.е. подключающиеся не первыми) получают при подключении на вход строку - последний введённый город в игре. Клиент в ответ отправляет город в виде одной строки; в ответ сервер отправляет либо строку "OK", если отправленный город начинался на букву на которую заканчивался последний город, иначе "NOT OK". В последнем случае введённый игроком город не учитывается для последующих игроков.