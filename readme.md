
- the application will run on port 8081
- `docker-compose up -d` command will fetch all dependent images and also build the image for messenger application.
- The docker volume is not configured, so data will be lost after the container stops.
- UUID is used for session management and stored in a **redis cache**.
- The `x-token` present in the response of `\login` api is the auth token used for session managment.
- `/get/unread,/send/text/user,/logout` APIs require `x-token:<<auth-token>>` and `x-username:<<username>>` in the HTTP header of the request for user login validation.
- Servlet filter with name `SessionFilter.class` is used to validate the sessions.
- The APIs do not have the capability to handle partial request body. This can be added later in future.
- Please use the request body as mentioned in the requirement [PDF file](./Simple%20Messenger%20System%20%5BBACKEND%5D.pdf).
- `Mysql` docker image is used for db.
- `redis` docker image is used for cache.
- [Postman collection](./messenger-app.postman_collection.json) used for testing.