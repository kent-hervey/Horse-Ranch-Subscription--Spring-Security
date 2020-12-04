# Horse Ranch Community

Horse Ranch Community is a brokerage service for equestrians connecting enthusiasts with owners of horse ranches available for subscription.

# Installation
This is a Maven/Spring Boot project with default WAR settings

Spring Boot will populate your MySQL file.  Just create the file and if different, change in the application.properties file

Required roles table will auto-populate on first run


[comment]: # (mvn spring-boot:run)


# Usage, JSP

UI documentation here:  [User Documentation](README-USER.md)


# Application Organization
  
  * Entity Classes include
    * HorseRanch
    * Role
    * User
    * UserHorseRanch (join or middle table)
    * UserRole (join or middle table)
  * Repositories as appropriate corresponding to Entity Classes
  * User and Ranch Services
  * Services related to auto role population
  * Controllers:  
    * User and Ranch for JSP front end
    * API controllers partially complete that will be available for other front ends such as React

# Site Organization 

  * login/reg/administration pages
  * Owner section currently consisting of 
    * Viewing owner's page dominated by list of owned ranches
    * Details of a ranch
    * Create, Update, and Delete capabilities
  * Guest section currently consisting of
    * Viewing list of all ranches with links to each
    * Viewing any specific ranch with opportunity to subscribe to any, and find contact information for owners

# Security Features

  * Fully implemented Spring Security in this Spring Boot application
  * Authentication using ApplicationListener and other resources
    * email address as username
  * Validation using Spring Security Validator interface
    * utilizing message.properties
  * JSP uses <security: authorize> to hide/show text/links based on role of viewer

<p>&nbsp;</p>

# Potential Future Features:

  * Many possibilities; A few:
    * OAuth
    * Guests and Owners see list of current admins on their personal page so they can be contacted
    * More information on each subscription (stored in subscription table--middle table between user and horseranch) such as expiration date and actual paid amount
    * Guests see summary of all their subscriptions