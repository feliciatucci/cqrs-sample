# **CQRS application sample** 
## An online plant shop

This is a Java web application that using [AxonFramework](http://axonframework.org) implements a cqrs  pattern sample. 
For details read [**DeveloperWork Article**](https://developer.ibm.com)

## Architecture
This sample is constituted of two components deployable in 2 war files.

1. **CQRS-ShoppingCartApp**
This is the application containing the aggregate object, the commands and command handlers, query manager.

2. **CQRS-ShoppingCartApp-EventHandler**
This is the application containing the eventListener

This application has been created to be deployed on Bluemix PaaS using two platform services: DB2 and MongoDb.

## Deployment on Bluemix PaaS
Login to Bluemix with your account.
Push java application for CQRS-ShoppingCartApp and one CQRS-ShoppingCartApp-EventHandler.

Create a DB2 Service and a MongoDb service.

Bind DB2 to CQRS-ShoppingCartApp. 

From **VCAP_SERVICES** capture the jndiName of db2 service and insert it in the dataSource section into app/wlp/usr/servers/defaultServer/server.xml

    <dataSource id='db2-SQL Database-jx' jdbcDriverRef='db2-driver' jndiName='jdbc/SQL Database-jx' statementCacheSize='30' transactional='true'>
	....
    </dataSource>


## Deployment on Bluemix Containers
From Bluemix console create a container space. From Bluemix CLI logon.

Build an image from your Dockerfile. The command returns an image ID.
```
docker build -t image_name
```

Tag the image with your private namespace in the IBM Containers registry.
```
docker tag image_name registry.ng.bluemix.net/cqrs_sample/image_name:image_tag
```
(N.B. cqrs_sample is the name of my namespace)

The image name is optional. If it is not specified, the image is tagged with latest.

Push this image to the IBM Containers registry:
```
docker push registry.ng.bluemix.net/cqrs_sample/image_name:image_tag
```
You can create a container from this image in the Bluemix Catalog, or with the following command:
```
cf ic run -p <ip-address>:<container-port>:<container-port> --name <container_name> registry.ng.bluemix.net/<namespace>/<image_name>

```

### Tomcat Container
Using the dockerfile  contained in git repository, and following the previous guideline create and run the Tomcat container

### MongoDB Container
Refer to https://docs.docker.com/engine/examples/mongodb/ for any details about mongodb container.

Using the dockerfile  contained in git repository, and following the previous guideline create and run the MongoDb container
```
cf ic run -p 172.17.0.4:27017:27017 --name mongo_001 registry.ng.bluemix.net/<namespace>/mongo_001
```

#### Checking out the logs of a MongoDB container
Usage: cf ic logs <name for container>
```
cf ic logs mongo_001
```
#### Playing with MongoDB
```
mongo --port 27017
```

### Rabbit-MQ Container
Refer to *https://docs.docker.com/engine/examples/mongodb/* for any details about mongodb container

Run docker rabbit
```
cf ic run -d --hostname my-rabbit --name some-rabbit rabbitmq
```
### Run docker rabbit-admin
to start rabbit admin:
```
cf ic run -d --hostname my-rabbit --name some-rabbit-admin rabbitmq-management
```

## License

The source code is available under the MIT license, which is in the LICENSE file in the root of the repository.


