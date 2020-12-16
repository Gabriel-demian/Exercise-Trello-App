# Exercise Trello App
 
 
 Documentación de Trello: https://developer.atlassian.com/cloud/trello/rest/ \
 Http:  "http://localhost:8080/api"
 
 #Argumentos de la aplicación:
 
 Los id se consiguen agregando la extensión .json en la URL del tablero. Ejemplo:  https://trello.com/b/{BOARDID}/the-example-board.json 
 
 Se dejan las url ya que son de uso general, por lo que se deja de ejemplo de relleno. 
 
 --trello.key= --trello.token= --trello.bug.label.id= --trello.test.list.id= --trello.research.list.id= --trello.maintenance.list.id= --trello.toDo.list.id= --trello.board.id= --trello.board.url=https://api.trello.com/1/boards --trello.card.url=https://api.trello.com/1/cards --trello.maintenance.label.id= --trello.research.label.id= --trello.test.label.id=

![Arguments](https://user-images.githubusercontent.com/57782295/102370372-bf7c3080-3f9b-11eb-846e-60a5503ed1d5.PNG)

# Some Screenshots of the request from Postman

curl --location --request POST 'http://localhost:8080/api?type=bug&description=Cockpit%20is%20not%20depressurizing%20correctly'
![post bug](https://user-images.githubusercontent.com/57782295/102370402-c73bd500-3f9b-11eb-877d-fca55bc5a458.PNG)

curl --location --request POST 'http://localhost:8080/api?title=Send%20message&type=issue&description=Let%20pilots%20send%20messages%20to%20Central'
![post issue](https://user-images.githubusercontent.com/57782295/102370425-cdca4c80-3f9b-11eb-9eef-d9f35673dcef.PNG)

curl --location --request POST 'http://localhost:8080/api?type=task&title=Clean%20the%20Rocket&category=Maintenance'
![post task](https://user-images.githubusercontent.com/57782295/102370444-d15dd380-3f9b-11eb-9896-e40628066bb3.PNG)


