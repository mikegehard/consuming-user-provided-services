# User Provided Service Example

This repository will demonstrate how to consume a user provided service
in a Spring Boot application using Spring Cloud Connectors.

## Setup

1. Create a [user provided service](https://docs.cloudfoundry.org/devguide/services/user-provided.html) in your space via `cf cups`:

    ```bash
    cf cups my-service -p '{"username":"admin","password":"pa55woRD","url":"http://example.com"}'
    ```
    
    You can confirm the creation of the service:
    
    ```bash
    cf services | grep my-service
    ``` 
    
    Some examples of user provided services:
    
    * Data store being hosted off of Cloud Foundry
    * Web services that do not run on Cloud Foundry 
    
1. You can then bind the service to an application via `cf bind-service`:

    ```bash
    cf bind-service my-app my-service
    ```
    
    You can confirm the service was bound via `cf env`:
    
    ```bash
    cf env my-app
    ```
    
    You are looking for an entry that looks like the following:
    
    ```bash
 
    "user-provided": [
       {
        "credentials": {
         "password": "pa55woRD",
         "url": "http://example.com",
         "username": "admin"
        },
        "label": "user-provided",
        "name": "my-service",
        "syslog_drain_url": "",
        "tags": [],
        "volume_mounts": []
       }
      ]

    ```
    
    You will get one of these "user-provided" entries for each bound user provided service.
    It is keyed off of the "name" attribute. This is OK because you can only bind a service
    once to an application.
