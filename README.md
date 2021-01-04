# Exercise Trello App
 
 Ejercicio:  [The Space-x team.docx](https://github.com/Gabriel-demian/Exercise-Trello-App/files/5764879/The.Space-x.team.docx)
 
 documentation from Trello Api: https://developer.atlassian.com/cloud/trello/rest/ \
 Http used for this app at localhost:  "http://localhost:8080/api"
 
 This application is a bridge between Trello and other Apps. We can create 3 diferent types of cards (An issue, A bug and A task).
 
 1.	An issue: This represents a business feature that needs implementation, they will provide a short title and a description. All issues gets added to the “To Do” list as unassigned (it will accept 3 parameters: "type", "title" and "description")
 2.	A bug: This represents a problem that needs fixing. They will only provide a description, the title needs to be randomized with the following pattern: bug-{word}-{number}. It doesn't matter that they repeat internally. The bugs should be assigned to a random member of the board and have the “Bug” label. (it will accept 2 parameters: "type" and "description")
 3.	A task: This represents some manual work that needs to be done. It will count with just a title and a category (Maintenance, Research, or Test) each corresponding to a label in trello.  (it will accept 3 parameters: "type", "title" and "category")

 
 # Arguments of the application:
 
 The ids of the board, card or labels can be obtained from the board url after you add ".json" at the end.. Example:  https://trello.com/b/{BOARDID}/the-example-board.json 
 
 The urls are left because they are for general use, I leave it as an example.
 
 --trello.key= --trello.token= --trello.bug.label.id= --trello.test.list.id= --trello.research.list.id= --trello.maintenance.list.id= --trello.toDo.list.id= --trello.board.id= --trello.board.url=https://api.trello.com/1/boards --trello.card.url=https://api.trello.com/1/cards --trello.maintenance.label.id= --trello.research.label.id= --trello.test.label.id=
<details>
  <summary>Screenshot Arguments of eclipse app</summary>
 
![Arguments](https://user-images.githubusercontent.com/57782295/102372370-e5a2d000-3f9d-11eb-8af3-8eaad833b0be.PNG)
 
</details>

Or you can fill the application.properties 

<details>
  <summary>Screenshot application.properties</summary>
 
![app properties](https://user-images.githubusercontent.com/57782295/102374769-7e3a4f80-3fa0-11eb-861a-0fb05c8273e3.PNG)

</details>

# Some URLs and Screenshots

POST BUG \
curl --location --request POST 'http://localhost:8080/api?type=bug&description=Cockpit%20is%20not%20depressurizing%20correctly'

<details>
  <summary>Screenshot to post a bug!</summary>

  ![post bug](https://user-images.githubusercontent.com/57782295/102370402-c73bd500-3f9b-11eb-877d-fca55bc5a458.PNG)

</details>

POST ISSUE \
curl --location --request POST 'http://localhost:8080/api?title=Send%20message&type=issue&description=Let%20pilots%20send%20messages%20to%20Central'

<details>
  <summary>Screenshot to post a issue</summary>
 
![post issue](https://user-images.githubusercontent.com/57782295/102370425-cdca4c80-3f9b-11eb-9eef-d9f35673dcef.PNG)

</details>

POST TASK \
curl --location --request POST 'http://localhost:8080/api?type=task&title=Clean%20the%20Rocket&category=Maintenance'

<details>
  <summary>Screenshot to post a task</summary>
 
![post task](https://user-images.githubusercontent.com/57782295/102370444-d15dd380-3f9b-11eb-9896-e40628066bb3.PNG)

</details>

GET MEMBERS \
curl --location --request GET 'http://localhost:8080/api'

<details>
  <summary>Screenshot to get a members</summary>
 
![get members](https://user-images.githubusercontent.com/57782295/102372384-e89dc080-3f9d-11eb-99bf-310c98ac8f15.PNG)
 
 </details>

