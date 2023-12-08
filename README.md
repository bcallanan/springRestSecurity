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

### Update 1 - Custom Authentication

The bank project is now functional on master with a customized authentication provider with the following:

   - bCrypt Password encoding
   - Custom authentication provider (moved away from the JDBC Authentication provider)
   - ControllerAdvice - providing some level of validation on endpoint payloads
   - Registering new bank account customers (API/Postman enabled)
   - Sequence ordering of new customers
   I've place a git tag/branch for both(This one and dockers) repos: AuthenticationWithSpringUpdate1

### Update 2 - Angular single-page-app, CORS and CSRF

This is an angular 14.2.X web app. As I incorporate more features, I'll consider what I need to do to move this forward to 16.x.

<!--![Alt text](./bankAngularApp/angularBankApp.png?raw=true "BC Bank - fictitious bank") -->

In this update, we'll see that we now have NodeJS running a single-page-app implemented in Angular. The port, in this case, is configured as http://localhost:4200. Then, the Spring Micro-Service that supports the back-end, with a JPA repository(postgres), is running on http://localhost:7075. These are two different domains running with different port numbers. Yes, they're using localhost but because the port numbers are different we'll have to deal with <i><b>Cross-Origin Resource Sharing</b></i> or <i><b>CORS</b></i> for short.

Other reasons for <i><b>CORS</b></i> are:
  - different scheme (HTTP/HTTPS)
  - different domain (localhost/foo.com)
  - different port*

![Alt text](./bankAngularApp/preflight.png?raw=true "Preflight error in the debugger")

By default, the browser will block this communication due to <i><b>CORS</b></i>. This is the whole point of this update, to decipher what actions a developer needs to perform to prevent CORS and, also, CSRF.

### CORS - Cross-Origin Resource Sharing
 The Angular App is not within the same domain space and is running from a separate port.

There are two main options for CORS when using SpringSecurity, Origin based access and Global Config. In both cases, the browser will perform a <i><b>Preflight</b></i> request to the back-end. The back-end can then specify whether or not the other domain is properly registered to communicate. It's the back-end's responsibility to handle this situation. 

#### Option 1 - Origin based access:
  Spring supports this with a <i><b>CrossOrigin</b></i> annotation. This annotation is placed on the rest controller(s). Sounds
  kind'a clunky to me and adds overhead to manage each controller class.

#### Option 2 - Global Config:
  Spring's Security FilterChain Bean can be instrumented with a CORS configuration with a Cors Source Configuration. Ah, yes!

  In both options, Spring, also, supports the concept of auto-configuration where domains can be registered in the
  <i>application.yml</i> file to register the front-end with the back-end origin(eg. http://localhost:4200 ). I prefer
  the option of defining properties in yml format. It provides an easy to read format and organizes attributes better
  IMO.

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
   
  Spring supports lots of other options as well:
  
   - PropertySource annotation:

    @Configuration
    @PropertySource("classpath:foo.properties")
    public class PropertiesWithJavaConfig {}
   - Dynamic selection at runtime:

    @PropertySource({ "classpath:persistence-${envTarget:mysql}.properties" })
   - Multi-property:

    @PropertySource("classpath:foo.properties")
    @PropertySource("classpath:bar.properties")
    public class PropertiesWithJavaConfig {}
    or
    @PropertySources({
    @PropertySource("classpath:foo.properties"),
    @PropertySource("classpath:bar.properties")})
    public class PropertiesWithJavaConfig {
     
### CSRF/XSRF - Cross-Site Request Forgery (CSRF)

A typical Cross-Site Request Forgery attack aims to perform an operation in a web application on behalf of a user without
their explicit consent. The attack isn't necessarily trying to steal the user's credentials or assume their identity. The attack is exploiting the user's access grants thru the use of stored cookies in the browser to carry out an action without their will. The user may have no idea they are being attacked. CSRF Attacks are not a concern on GET Actions.

  - Example:
   
    User X logs into Site Y with their private user credentials. Site Y examines the credentials as a successful login. Site X
    then provides User X with a cookie that is stored into the user's local browser cache until such time the cookie expires or
    the user logs out.
    
    The intent here is that Site Y is trusting user X for the expiration period of the cookie so the user would not have to
    re-authenticate if they navigated away from the site or hit refresh(F5) in the browser.
    
    User X proceeds to navigate to other URLs within the expiration period of the cookie and clicks on a some hyperlink.
    Unknown to User X, the hyperlink had an embedded form that executed an action on behalf of User X that exploited the
    'still' authenticated cookie.
    
    Here's an example of what a CSRF form might look like:
    ~~~ 
      <form action="https://siteX.com/changeEmail"
        method="POST" id="form">
          <input type="hidden" name="email" value="user@evil.com">
      </form>
      <script>
        document.getElementById('form').submit()
      </script>
Just a suggestion - Self preservation here would be to log-out of your critical accounts when you've completed what you
were doing. Not just closing a tab and waiting for the cookie to expire.     

#### Responsibility
So, who owns this critical feature requirement to enforce or prevent CSRF Attack... Well, it's both Front-end and back-end!

##### Back-end responsibility
To prevent the attack, the back-end needs a way to determine if the request is legitimately generated via the application's user interface.

A proper way to achieve this is through the use of a CSRF token. A CSRF token is a secure random token that is used
to prevent CSRF attacks. The token needs to be unique per user session and should be of a large random value to make
to difficult to decipher.

So, now, User X receives the cookie as part of the authentication and still expires, as usual, but also gets a CSRF token.

##### Front-end responsibility
The front-end developer needs to track the initial CSRF token received during authentication. Then, manually insert that CRSF token into the request header or pay-load.

This is hand-shake design agreement between front-end and back-end on how this enforcement will be handled.
Together, the authentication expiration period of the cookie is then backed up by the CSRF Token. The form
submitted in attack from the hyperlink is then rejected with a Status:403.

##### Back-end responsibility
What's additional here is that this form of attack should also be tracked/logged/alerted on the back-end as a cyber-attack. 
Safeguards are there to prevent evil. It's also good to know when they are occurring.
   
Repos: <b> dockers and springrestsecurity</b> are in sync w/ branch tag : <b>AuthenticationWithSpringUpdate2</b>
