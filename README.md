## Description

This is project of chat application build using Java EE - JMS. 

Current version let for communication between 2 clients and sending files via chat command line. 

Features planned to add:
- storing history in DB using Hibernate,
- creating private rooms (using JAX-RS)

Communication is performed via console;

## Requirements

- JAVA 17
- Lombok
- Wildfly 26.0.1
- JPA

## Setup

To start the application you need to follow this steps:
1. Build PostgreSql using docker-compose.yml - int the terminal type in docker-compose up
2. Start the application server - JBoss/Wildfly
3. Run the ChatClient file - in the command line you need to specify name;

