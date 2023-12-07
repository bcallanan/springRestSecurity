# springRestSecurity

Hi, this is a security base repository. that's the intent. All things spring security. 

The first 'hello' world spring security test is the 'rest' project. It's pretty basic but the pom does include:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

 so, the DI for spring security is enabled. Which means that logging into(User/passwd) the port for the spring service is req'd.

 
The next project myBank starts with a h2 database for in-memory and is also a good starter. I didnt set a branch
label for the functional level. But then, I switched to a jdbc enabled security model with also using the docker
repo in my repository for postgres. It's not trivial to set this up so if you need help, ask.

I've place a git tag/branch for both repos: functionalPostgresAuthenticationWithSpring

Update 1)

The bank project is now functional on master with a customized authentication provider with the following:

   - bCrypt Password encoding
   - Custom authentication provider (moved away from the JDBC Authentication provider)
   - ControllerAdvice - providing some level of validation on endpoint payloads
   - Registering new bank account customers (API/Postman enabled)
   - Sequence ordering of new customers
   I've place a git tag/branch for both(This one and dockers) repos: AuthenticationWithSpringUpdate1

Update 2) in progress on master. Added angular single page app. This is an angular 14.2.X web app. As I incorporate more
features and knowledge I'll consider what I need to do to move this forward to 16.x.

![Alt text](./bankAngularApp/angularBankApp.png?raw=true "BC Bank - fictitous bank")

In this update we'll see that we have NodeJS running a single-page-app in Angular. The port in this case has been done using http://localhost:4200. The SpringService that supports the backend with the JPA repository resources is running on http://localhost:7075. These are two different domains because they are running on different port numbers. Yes, they're using localhost but because the port numbers are different we'll have to deal with <i><b>Cross-Origin Resource Sharing</b></i> or <i><b>CORS</b></i> for short.

Other reasons for <i><b>CORS</b></i> are:
  - different scheme (HTTP/HTTPS)
  - different domain (localhost/foo.com)
  - different port*

By default, the browser will block this communication due to <i><b>CORS</b></i>. This is the whole point of this update. The Angular App is not within the same domain space and is running from a separate port.

There are two options:

Option 1)
A browser will perform a <i><b>Preflight</b></i> request to the backend. The backend can then specifiy whether or not the other domain is properly registered to communicate. Spring supports this with a <i><b>CrossOrigin</b></i> annotation. Spring, also, supports the concept of auto-configuration where this domain can be registered in the application.yml file to register the front-end with the back-end origin(eg. http://localhost:4200 ). This annotation is placed on the controller(s). Sounds kind'a clunky and adds overhead.

Option 2)
Spring's Security FilterChain Bean can be instrumented with a CORS configuration with sort'of one-stop-shopping type configuration. Ah, yes!
     
Note: We'll also have to cure: Cross-Site Request Forgery (CSRF) too!
