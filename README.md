# Exercise Trello App
 
 
 Documentación de Trello: https://developer.atlassian.com/cloud/trello/rest/
 Http:  "http://localhost:8080/api"
 
Se generará un get para obtener todos los miembros del tablero, poder seleccionar uno al azar y poder asignarle un usuario a la tarea



POST crear una tarea:

http://localhost:8080/api?key=be0b2ec392fa7b50165d010986e5b540&token=13a3f2f5375b308caa4351da0b855b81f79717c4f33eb0c0455803c180252ecb&idList=5fd3be679b02582fb2c4da57&name=App test&desc=This is a description&due=2020-12-14
   Parametros:
       key: 
       token: 
       idList: 
       name: App test
       desc: This is a description
       due: 2020-12-14




GET buscar usuarios asignados a una tabla. 

http://localhost:8080/api?key=be0b2ec392fa7b50165d010986e5b540&token=13a3f2f5375b308caa4351da0b855b81f79717c4f33eb0c0455803c180252ecb&boardId=5fd3ae9843f6da44178ef235
   Parametros:
       key: be0b2ec392fa7b50165d010986e5b540
       token: 13a3f2f5375b308caa4351da0b855b81f79717c4f33eb0c0455803c180252ecb
       boardId: 5fd3ae9843f6da44178ef235

