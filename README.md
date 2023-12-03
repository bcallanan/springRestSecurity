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

New branch created in conjuction with dockers to synchronize functionality across the repos:  mybankwithBCryptSecurityAndPostgres

  - this new branch starts a 'sequence' in postgres for creating user/customer records. Postgres will auto generate the primaryKey.
  - BCypt Password encoding
  - Registering new accounts thru a post(postman)... I'll update a collection here but its primitive for now.
  - Here's the controler endpoints:
	requestMatchers("/welcome", "/", "/myAccount","/myBalance","/myLoans","/myCards").authenticated()
        requestMatchers("/notices","/contact", "/register").permitAll())

