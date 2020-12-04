# Products and Customers

Products and Customers is a simple web application that manages the products a vendor or distributor has under use by customers, and also manages the customers using those products

# Installation
This is a Maven/Spring Boot project with default WAR settings

Spring Boot will populate your MySQL file.  Just create the file and if different, change in the application.properties file

Required roles table will auto-populate on first run


[comment]: # (mvn spring-boot:run)


# Usage

UI documentation here:  [User Documentation](README-USER.md)



Spring Security Demo Project that includes:

  * Two roles: Admin and standard users
  * Admin manages roles of any other admin and all users
  * Web page showing all users
  * Web page showing details of any user
  * Automated Admin creation in case of no admins


Horse Ranch Community is a brokerage service for equestrians connecting enthusiasts with owners of horse ranches available for subscription.
Sign up here and apply to automatically be assigned the browser role, and request access as one or both of these roles:
Guest: Looking to subscribe or have already subscribe to one or more ranches
Owner: Have one or more ranches to be offered on a subscription basis

Business parameters:

  * Horse Ranch constraints on properties
    * Number of Acres
      * Minimum:  2; because less than that is not really enough for barn, horse arena, etc
      * Maximum:  100,000; because anything entered larger than that is very likely a mistake
    * Horse Capacity; number of horses that can be kept on property
      * Minimum:  2; because no fun riding alone
      * Maximum:  1,000; because anything larger is likely a mistake
    * People Capacity; number of subscriber people who can be accommodated at once...taking into account parking, etc.
      * Minimum:  2; because no fun riding alone
      * Maximum:  100; because anything larger is likely a mistake
    *  Subscription Price; should be rounded to whole dollars
      * Minimum $100; because anything less is not practical
      * Maximum $100,000; because even if 5 people stayed continuously for a year, that is an almost incredibly high price
    * Ranch Name Min/max
      * Minimum:  5 characters, no restriction on type
      * Maximum:  10 characters
    * Ranch Description; Should provide all the information a prospect should want before calling for more information
      * Minimum:  5 characters
      * Maximum:  250 characters
      

Other Business notes:

* A single subscriber represents the payer and up to 4 guests for any visit.  More than four would require special permission

    


Note:  constraints will be stored at top of service classes used for validations and possible in entity class with annotations such as @Min


Developer side features:
  * Roles table self populates  see Google Doc starting 10/13/20
  * Security between Owners and Guests and between Admins and others
  * Validation for price allows user to enter $ and commas, but results stored as string
  * Spring Validator including custom methods used with messages.properties
  * Admin table column view limits Admin message to 25 characters.  Clicking user will show full message



Scratch from harvesting from Google Doc for project...stuff to consider:

======
validations and how I did them...the various files and such https://docs.google.com/document/d/1xkcgmE0IytNXpEwyOu2qVhqVfUUyiZxS2uJaD6Zplas/edit#bookmark=id.cqu3fy4hmknq 

10/4/20

Added classes:  SetupService and SetupDataBaseData.  Added code to those and RoleRepository for auto populating roles table.  Created a couple of methods to check if a role already exists.  Code will check for each of the needed four and add any ones that don't exist.  Will pass the role_explanation to the method that will check then affirm the role

Created class:  RanchValidator

.....more information on how validation works on 10/7 and circa dates...seems to have finished 10/13/20

======
October....tested all with Postman to confirm authorization even for the JSP controllers

October....built in guard code for several Controller methods so only the auth could run the method..includes October 25
seems to have been complete by end of October

11/4/20 began work on controller changes in prep for RESTful upgrades of endpoints for JSP methods and also setting the API controllers



Lots of features being considered for future

used state diagrams and UML


===
used for last logged in
Add a wrapper class for User like the below so we can keep track of last log in for each user:
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
And also needed for tracking last login something like:  public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent>




====
Hiding portions of output in JSP as in:
			<security:authorize access="hasAnyRole('ADMIN','OWNER')">
				<div class="col-20">
					<a href="/owners/auth">Ranches You Own</a><!-- for Owner s ownersPage.jsp -->
				</div>
			</security:authorize>

====
this explains one reason the project was delayed:
8/29/20 returning from many days after completing SS book yesterday which I started 8/10/20

====
Note to Admin ...
9/5/20 added new field to User.java:  noteToAdmin.  Purpose is to allow user to communicate desired roles to admin


registration:  passwords must match...as username is email, can't register with email already in database

====
We discussed whether project was useful with csrf disabled.  Richard's
out:  put in readme that I don't support a user agent that browser

We were not able to figure how to log in using Postman, so eventually disabled csrf using:
				//two lines below temp commented on 9/21/20
				.anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll()
				
				.and().httpBasic();
		http.csrf().disable();

===End documenting Monday night 9/21/20 session with Richard


>>>Completed getting custom validations to work….first one:  number of acres...used Validator and RanchService and messages.properties

10/12/20 Monday....left pasted in
======


















  