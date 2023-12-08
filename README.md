# springRestSecurity

Hi, this is a security base repository. that's the intent. All things spring security. 

The first 'hello' world spring security test is the 'rest' project. It's pretty basic but the pom does include:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

 So, the DI for spring security is enabled. Which means that logging into(User/passwd) the port for the spring service is req'd.

 
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

Update 2) Added angular single page app. This is an angular 14.2.X web app. As I incorporate more
features, I'll consider what I need to do to move this forward to 16.x.

<!--![Alt text](./bankAngularApp/angularBankApp.png?raw=true "BC Bank - fictitious bank") -->

In this update, we'll see that we now have NodeJS running a single-page-app in Angular. The port, in this case, is configured as http://localhost:4200. Then, the Spring Micro-Service that supports the back-end, with a JPA repository(postgres), is running on http://localhost:7075. These are two different domains running with different port numbers. Yes, they're using localhost but because the port numbers are different we'll have to deal with <i><b>Cross-Origin Resource Sharing</b></i> or <i><b>CORS</b></i> for short.

Other reasons for <i><b>CORS</b></i> are:
  - different scheme (HTTP/HTTPS)
  - different domain (localhost/foo.com)
  - different port*

![Alt text](./bankAngularApp/preflight.png?raw=true "Preflight error in the debugger")

By default, the browser will block this communication due to <i><b>CORS</b></i>. This is the whole point of this update, to decipher what actions a developer needs to perform to prevent CORS and, also, CSRF.

### CORS -Cross-Origin Resource Sharing
 The Angular App is not within the same domain space and is running from a separate port.

There are two main options for CORS when using SpringSecurity, Origin based access and Global Config. In both cases, the browser will perform a <i><b>Preflight</b></i> request to the back-end. The back-end can then specify whether or not the other domain is properly registered to communicate. It's the back-end's responsibility to handle this situation. 

#### Option 1 - Origin based access:
  Spring supports this with a <i><b>CrossOrigin</b></i> annotation. This annotation is placed on the rest controller(s). Sounds
  kind'a clunky to me and adds overhead to manage each controller class.

#### Option 2 - Global Config:
  Spring's Security FilterChain Bean can be instrumented with a CORS configuration with a Cors Source Configuration. Ah, yes!

  In both options, Spring, also, supports the concept of auto-configuration where domains can be registered in the
  <i>application.yml</i> file to register the front-end with the back-end origin(eg. http://localhost:4200 ). I prefer
  the option of defining properties in yml format. It provides an easy to read format and organizes attributes better IMO.

  	Variable injection:
  	
  	@Value("${spring.security.cors.domains}")
	public List<String> domains;
  
    Application yml syntax (each '.' is an indented level):
    
    spring:
      security:
        cors:
          domains:
            # comma separated list
            http://localhost:4200
   
  Spring also supports other options as well:
  
   - PropertySource annotation:

      @Configuration
      @PropertySource("classpath:foo.properties")
      public class PropertiesWithJavaConfig {} </verbatim>
   - Dynamic selection at runtime:

       @PropertySource({ "classpath:persistence-${envTarget:mysql}.properties" })
    
         
Note: We'll also have to cure: Cross-Site Request Forgery (CSRF)


