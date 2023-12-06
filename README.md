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
