# springRestSecurity

Hi, this is a security base repository. that's the intent. All things spring security. Update: The Spring Bank Projects have also been updated with Swagger UI and Swagger API Doc.


The first 'hello' world spring security test is the 'rest' project. It's pretty basic but the pom does include:

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

 So, the DI for spring security is enabled. Which means that logging into(User/passwd) the port for the spring service is req'd.

## Spring Bank(full blown security features) 
The next project myBank starts with a JDBC(postgres) database. I used jdbc as the basis for enabling a spring security model with a docker-compose in my repository for postgres. It's not trivial to set this
up so if you need help, ask.

I've place a git tag/branch for both repos: functionalPostgresAuthenticationWithSpring

Update for Swagger:

<details>
   <summary>(Example Application Yaml here)</summary>

    springdoc:
      show-oauth2-endpoints:
        true
      swagger-ui:
        #configUrl: -- turns on the swagger petstore ui
        #  /swaggerApi
        enabled:
          true
        path:
          "/index.html"
      api-docs:
        enabled:
          true
        path:
          "/api-docs" 
</details>

<details>
  <summary>(Example API-Doc here)</summary>

          {
            "openapi": "3.0.1",
            "info": {
            "title": "OpenAPI definition",
            "version": "v0"
          },
          "servers": [
            {
              "url": "http://192.168.0.29:7075",
              "description": "Generated server url"
            }
          ],
          "paths": {
            "/Contact": {
              "post": {
                "tags": [
                  "contact-controller"
                ],
                "operationId": "getContactInquiryDetails",
                "requestBody": {
                  "content": {
                    "application/json": {
                      "schema": {
                        "$ref": "#/components/schemas/Contact"
                      }
                    }
                  },
                  "required": true
                },
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Contact"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/contact": {
              "post": {
                "tags": [
                  "contact-controller"
                ],
                "operationId": "getContactInquiryDetails_1",
                "requestBody": {
                  "content": {
                    "application/json": {
                      "schema": {
                        "$ref": "#/components/schemas/Contact"
                      }
                    }
                  },
                  "required": true
                },
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Contact"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/welcome": {
              "get": {
                "tags": [
                  "welcome-controller"
                ],
                "operationId": "welcome",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "string"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/*": {
              "get": {
                "tags": [
                  "welcome-controller"
                ],
                "operationId": "welcome_1",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "string"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myLoans": {
              "get": {
                "tags": [
                  "loans-controller"
                ],
                "operationId": "getLoanDetails",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Loan"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myloans": {
              "get": {
                "tags": [
                  "loans-controller"
                ],
                "operationId": "getLoanDetails_1",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Loan"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/mycards": {
              "get": {
                "tags": [
                  "card-controller"
                ],
                "operationId": "getCardDetails",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Card"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myCards": {
              "get": {
                "tags": [
                  "card-controller"
                ],
                "operationId": "getCardDetails_1",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Card"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/mybalance": {
              "get": {
                "tags": [
                  "balance-controller"
                ],
                "operationId": "getBalanceDetails",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/AccountTransaction"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myBalance": {
              "get": {
                "tags": [
                  "balance-controller"
                ],
                "operationId": "getBalanceDetails_1",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/AccountTransaction"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myaccount": {
              "get": {
                "tags": [
                  "account-controller"
                ],
                "operationId": "getAccountDetails",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Account"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/myAccount": {
              "get": {
                "tags": [
                  "account-controller"
                ],
                "operationId": "getAccountDetails_1",
                "parameters": [
                  {
                    "name": "customerId",
                    "in": "query",
                    "required": true,
                    "schema": {
                      "type": "string"
                    }
                  }
                ],
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Account"
                        }
                      }
                    }
                  }
                }
              }
            },
            "/Notices": {
              "get": {
                "tags": [
                  "notice-controller"
                ],
                "operationId": "getNotices",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Notice"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/notices": {
              "get": {
                "tags": [
                  "notice-controller"
                ],
                "operationId": "getNotices_1",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "type": "array",
                          "items": {
                            "$ref": "#/components/schemas/Notice"
                          }
                        }
                      }
                    }
                  }
                }
              }
            },
            "/user": {
              "get": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "put": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_3",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "post": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_2",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "delete": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_5",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "options": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_6",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "head": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_1",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              },
              "patch": {
                "tags": [
                  "login-controller"
                ],
                "operationId": "getUserDetailsAfterLogin_4",
                "responses": {
                  "200": {
                    "description": "OK",
                    "content": {
                      "*/*": {
                        "schema": {
                          "$ref": "#/components/schemas/Customer"
                        }
                      }
                    }
                  }
                }
              }
            }
          },
          "components": {
            "schemas": {
              "Contact": {
                "type": "object",
                "properties": {
                  "contactId": {
                    "type": "string"
                  },
                  "contactName": {
                    "type": "string"
                  },
                  "contactEmail": {
                    "type": "string"
                  },
                  "subject": {
                    "type": "string"
                  },
                  "message": {
                    "type": "string"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "Loan": {
                "type": "object",
                "properties": {
                  "loanNumber": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "loanType": {
                    "type": "string",
                    "enum": [
                      "MORTGAGE",
                      "EQUITY",
                      "LINE_OF_CREDIT",
                      "HOME",
                      "VEHICLE",
                      "PERSONAL"
                    ]
                  },
                  "totalLoanValue": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "amountPaid": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "outstandingBalance": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "startDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "Card": {
                "type": "object",
                "properties": {
                  "cardNumber": {
                    "type": "string"
                  },
                  "cardType": {
                    "type": "string",
                    "enum": [
                      "CREDIT",
                      "DEBT"
                    ]
                  },
                  "cardLimit": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "amountOutstanding": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "amountAvailable": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "AccountTransaction": {
                "type": "object",
                "properties": {
                  "accountNumber": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "transactionDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "transactionSummary": {
                    "type": "string"
                  },
                  "transactionType": {
                    "type": "string",
                    "enum": [
                      "DEPOSIT",
                      "WITHDRAWAL"
                    ]
                  },
                  "transactionAmount": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "closingBalance": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "Account": {
                "type": "object",
                "properties": {
                  "accountNumber": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "accountType": {
                    "type": "string",
                    "enum": [
                      "SAVINGS",
                      "CHECKING"
                    ]
                  },
                  "branchAddress": {
                    "type": "string"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "Notice": {
                "type": "object",
                "properties": {
                  "noticeId": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "noticeSummary": {
                    "type": "string"
                  },
                  "noticeDetails": {
                    "type": "string"
                  },
                  "noticeBeginDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "noticeEndDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "updateDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  }
                }
              },
              "Customer": {
                "required": [
                  "emailAddress",
                  "pwd",
                  "role"
                ],
                "type": "object",
                "properties": {
                  "customerId": {
                    "type": "integer",
                    "format": "int32"
                  },
                  "name": {
                    "type": "string"
                  },
                  "emailAddress": {
                    "type": "string"
                  },
                  "mobileNumber": {
                    "type": "string"
                  },
                  "pwd": {
                    "type": "string",
                    "writeOnly": true
                  },
                  "role": {
                    "type": "string"
                  },
                  "createDate": {
                    "type": "string",
                    "format": "date-time"
                  },
                  "registrationToken": {
                    "type": "string"
                  }
                }
              }
            }
          }
        }
</details>


### Update 1 - Custom Authentication

The bank project is now functional on master with a customized authentication provider with the following:

   - bCrypt Password encoding
   - Custom authentication provider (moved away from the JDBC Authentication provider)
   - ControllerAdvice - providing some level of validation on endpoint payloads
   - Registering new bank account customers (API/Postman enabled)
   - Sequence ordering of new customers
   I've place a git tag/branch for both(This one and dockers) repos: AuthenticationWithSpringUpdate1

### Update 2 - Angular single-page-app, CORS and CSRF

This was an angular 14.2.X web app. As I incorporated more features and integrated Keycloak, I had to upgrade to Angular 17.0.8. The main area to focus on in the implementation is:  <i><b>MyBankSecurityConfig</b></i> 

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
   
    User X logs into Site Y with their private user credentials. Site Y examines the credentials as a successful login. Site Y
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

### Update 3 - Authorities and Roles

Spring stores Authorities and Roles inside the GrantedAuthority. A single method is available
providing the Authority the user is assigned. Up until this point <i><b>SimpleGrantedAuthority</b></i>
has been used for the authentication provider.

Three spring security authority apis exist:
 1) hasAuthority() - accepts a single authority
 1) hasAnyAuthority() - accepts multiple authorities
 1) Access() - using Spring Expression Language(SpEL) it provides unlimited options for configuring authorities which are not possible with the other two options.
 
 
 
Authority's are individual authorized privileges (Ex: VIEWACCOUNT, VIEWCARDS). Roles are a group of privileges/actions than can be used as a basis for providing organized administration of authorization rights. (Ex: ROLE_ADMIN, ROLE_USER) Roles are a course grain application of secure authorization levels.

The names of the authorities and roles are arbitrary here. The names can be customized oer the business requirements. 

Three Spring Security Role apis exist which are similar to the three authority APiI's listed above. 
 1) hasRole() - accepts a single role
 1) hasAnyRole() - accepts multiple roles
 1) Access() - again, using Spring Expression Language(SpEL) it provides unlimited options for configuring roles. 
 
 Repos: <b> dockers and springrestsecurity</b> are in sync w/ branch tag : <b>AuthenticationWithSpringUpdate3</b>
 
### Update 4 - Servlets & Filters
 
One of the most popular servlet containers is Apache Tomcat. Servlet containers like Tomcat convert HTTP Messages into servlet requests and send those requests to the serlet methods as parameters. The same happens with the response but in the reverse order.

In this implementation update, it would be helpful to enable security debugging.
Note: this is just for this section and should be used with care. 

    @EnableWebSecurity(debug=true)
   
    and
   
    logging:
      level:
        org:
          springframework:
            security:
              web:
                FilterChainProxy:
                  DEBUG
    
Filters can be used to intercept these request/response messages and perform some operations on them as before/after business logic. Filters can be user to enforce security during these operations. These filters are stacked in a filter chain. Default stack ordering (17 filters):

    Security filter chain: [
     DisableEncodeUrlFilter
     ForceEagerSessionCreationFilter
     ForceEagerSessionCreationFilter
     WebAsyncManagerIntegrationFilter
     SecurityContextPersistenceFilter
     HeaderWriterFilter
     CorsFilter <-- in the initial impl of the bank app in the repo this was disabled 
     CsrfFilter <-- in the initial impl of the bank app in the repo this was disabled
     LogoutFilter
     UsernamePasswordAuthenticationFilter
     DefaultLoginPageGeneratingFilter
     DefaultLogoutPageGeneratingFilter
     BasicAuthenticationFilter 
     CsrfCookieFilter    <---- from last update
     RequestCacheAwareFilter
     SecurityContextHolderAwareRequestFilter
     AnonymousAuthenticationFilter
     SessionManagementFilter
     ExceptionTranslationFilter
     AuthorizationFilter]


Filters provide an excellent opportunity to provide input validation, tracing/auditing and reporting. Multi-factor OTP as well.

Filter API's in update will be:

    addFilterBefore(filter, class) - add filter before specified class type
    addFilterAfter( filter, class) - add filter after specified class type
    addFilterAt( filter, class) - add filter at the location of the specified
            class type. This option should be used with care cause it is randomly
            executed along with the filter it is positioned alongside with. It does not
            replace the filter positioned at. It is essentially at the same position as
            or alongside with.
   
In the last update, we had already addressed the inclusion of handling the CSRF attacks by adding a CSRF Token into the headers along with cookie. So, that is how the filter got added into list of the default filter chain stack above.

	.addFilterAfter( new CsrfCookieFilter(), BasicAuthenticationFilter.class)
	
	cookie: JSESSIONID=D4DB68AAD1E627C601A27D45C0B497B7; XSRF-TOKEN=108a195b-9df1-47c9-b489-d5894cb9e25b
	
Without slowing down the login process with some type of JDBC request to log
the login request in the DB. We can easily log the login request to a suitable logger.

    .addFilterAfter( new LoggingFilterAfterAuthorityFilter(), BasicAuthenticationFilter.class)
    
This will give traceability of logins into a SPLUNK logger for searching depending on how the logback config might be logged.

Repo: <b>springrestsecurity</b> w/ branch tag : <b>AuthenticationWithSpringUpdate4</b>, there were no changes in the dockers.
 
### Update 5 - JWT and Token based authentication

In prior updates the JSession ID or cookie has been used as a form of authentication
where the user could stay validated while the cookie was still within the expiration period. The JSession ID or cookie is created per session and doesn't contain much or
any information relative to the ongoing login session.

In this update, JSON Web Tokens(JWT) will added to the bank app. This token is an additional token to the CSRF token. There are several advantages and special features that this design provides. It has the advantage of providing user related data within the token to reduce caching session information elsewhere. JWT Tokens have 3 parts separated with '.' tokens:

    - Header
    - Payload
    - Signature( Optional )
    
JWT Token encryption schemes might include something like the following(@see https://security.stackexchange.com/questions/79577/whats-the-difference-between-hmac-sha256key-data-and-sha256key-data):

    HMACSCHA256(base64UrlEncoded(header) + "." base64URLEncoded(payload), secret)
    
Is the prior updates, the session management was does with a JSession ID evey time in every request. With JWT Token, token enforcement is Stateless.
 	
    .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS)
 
The token will now be challenged every time. This will be accomplished with the generation of a JWT during the initial login. The token will first be returned in the response as part of the header.

You'll not the 3 parts of the JWT Token below. The first section is the algorithm used for the body. UUDecode base64 for "eyJhbGciOiJIUzI1NiJ9". Shows the content as:

    {"alg":"HS256"}
    
The 2nd part(payload) and 3rd(Signature) part(s) require the key. Just like in ssh, you never give out your private keys.

    Authorization: 		eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJCQyBCYW5rIiwic3ViIjoiQkMgSldUIFRva2VuIi   		widXNlcm5hbWUiOiJjYWxsYW5hbmtpZHNAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOiJST     		0xFX1VTRVIsUk9MRV9BRE1JTiIsImlhdCI6MTcwMjI1MTg1OSwiZXhwIjoxNzAyMjUzNjU5
    	fQ.uKQdrmp60XfYtkv7ufSwmBk3a0lIfDz-oeQ8Ef7C77U
    X-XSRF-TOKEN: 88326a75-8d1d-4824-9f39-3964e7a329c2

As you can see the XSRF token is still present in the headers.

With the additional filters being added into the Filter Chain. We now have several more after the authentication filter. And one additional one before that will validate the JWT Token on all requests.
 
     Security filter chain: [
	  DisableEncodeUrlFilter
	  WebAsyncManagerIntegrationFilter
	  SecurityContextHolderFilter
	  HeaderWriterFilter
	  CorsFilter
	  CsrfFilter
	  LogoutFilter
	  UsernamePasswordAuthenticationFilter
	  DefaultLoginPageGeneratingFilter
	  DefaultLogoutPageGeneratingFilter
	  JWTTokenValidatorFilter    <--- request validator is not tested on user login but every time there after
	  CustomRequestFilterBefore <--- Adhoc filter
	  BasicAuthenticationFilter
	  CsrfCookieFilter   <--- generates the XSRF/CSRF token
	  JWTTokenGenerationFilter <--- Is generated on user login but is not generated until the next login after token expiration
	  JWTTokenValidationFilter <--- Is not validated on user login but there after and stores the token in the SecurityContext
	  LoggingFilterAfterAuthorityFilter <--- logging filter to log logins
	  RequestCacheAwareFilter
	  SecurityContextHolderAwareRequestFilter
	  AnonymousAuthenticationFilter
	  SessionManagementFilter
	  ExceptionTranslationFilter
	  AuthorizationFilter ]
  
Expired Token example:

     io.jsonwebtoken.ExpiredJwtException: JWT expired at 2023-12-11T00:13:14Z. Current
     time: 2023-12-11T01:03:10Z, a difference of 2996834 milliseconds.  Allowed clock skew:
     0 milliseconds.
     
Repo: <b>springrestsecurity</b> w/ branch tag : <b>AuthenticationWithSpringUpdate5</b>, there were no changes in the dockers.

### Update 6 - Method level security

Method level security starts with the SpringBootApplication and/or @Configuration implementations. This form of security acts as a second level of security. All method level security is accomplished with SPING AOP. Here Aspect programming is allowing an interceptor to interrogate the security enforcement being implemented on a method at 'runtime'.  

There are several examples @annotations and all of them have different rules:

    @EnabledMethodSecurity - Goes on top of the SpringApplication or at the top of the @Configuration classes.
    
    @EnabledMethodSecurity(prePostEnabled = true )
     
Method level security provides 2 common authorization rules:

    - Invocation authorization: validates if someone can invoke a method base on their
      roles and authorities.
    - Filter authorization: validate what a method can receive thru it's parameters and
      what the invoker can receive back from the method post logic execution.    
      

#### Invocation athorization
There are many annotation options with method level security, while the main two options are likely to handle 95% of the requirements:

    - @PreAuthorize - pre auth means the methods authorization is examined before it is executed.
    - @PostAuthorize - post auth means the methods authorization is examined after it is executed.
      Like being able to get the result. Limited use of this type of method security annotation IMO.
      
#### Filter authorization
      
     - @PreFilter - pre means the methods authorization is examined before it is executed. Apparently
       here the params should be of type Collection interface...List/Set etc
     		@preFilter( "filterObject.contactName != 'test'"
     		public List< COntact> SaveContactInquiryDetails( @RequestBody List<Contact> contacts ) { 
     		   // Business Ligic
     		   Return contacts;
     		}  
       This method will avoid processing any contact in the list where the contactName = test. I'm
       not sure the usefulness here. My .02!	 
     		
    - @PostFilterAuthorize - same deal, Limited use of this type of method security annotation IMO. Even
      more so given the functionality this api provides
      
Repo: <b>springrestsecurity</b> the changes here were insignificant IMO. There also werent any in dockers.


### Update 7 - OAuth2, OpenID & Keycloak

There are Lots of different use cases for OAuth2. It provides a SSO level of authentication across a number of different platforms with a single authentication/authorization server. @see http://oauth.net/2. I've included some easy review items from the link. 

To support multiple security configurations without deleting or altering code too much we'll introduce a application property to alter the security config being used. We'll refactor MyBankSecurityConfig.java to have two SecurityFilterChain Beans:

  - defaultJWTSecurityFilterChain - JWT Token and all the filters previously discussed
  - defaultOAuthSecurityFilterChain - OAuth Tokens using the Keycloak Auth Server and OpenID 
 
 The password encoder bcrypt will also be shut-off when OAuth is being used.
 
 For OAuth the AuthenticationProvider class is also disabled.
 
 This spring boot auto-config will allow for both configurations to be employed by changing the boolean value in the application.yml file.
 
    - spring:
        security:
          config:
            oauth:
              true
            jwt:
              false

At the top of the methods the following annotation will enable one or the other. 

	@Bean
	@ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
	SecurityFilterChain defaultJWTSecurityFilterChain(HttpSecurity http) throws Exception {

    and
    
    @Bean
	@ConditionalOnProperty(name="spring.security.config.oauth", havingValue = "true")
	SecurityFilterChain defaultOAuthSecurityFilterChain(HttpSecurity http) throws Exception {
	
	and
	
	@Bean
	@ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
	public PasswordEncoder passwordEncoder() {
	
	and
	
	@Component
    @ConditionalOnProperty(name="spring.security.config.jwt", havingValue = "true")
    class AccountSecurityAuthenticationProvider implements AuthenticationProvider {
	
    
##### OAuth Framework:

  - Access Tokens: No particular format and various OAuth servers have chosen many different formats for their access tokens. Access tokens may be either "bearer tokens" or "sender-constrained" tokens. Sender-constrained tokens require the OAuth client to prove possession of a private key in some way in order to use the access token, such that the access token by itself would not be usable. 

  	- There are a number of properties of access tokens that are fundamental to the security model of OAuth:
		- Access tokens must not be read or interpreted by the OAuth client. The OAuth client is not the intended audience of the token.
		- Access tokens do not convey user identity or any other information about the user to the OAuth client.
		- Access tokens should only be used to make requests to the resource server. Additionally, ID tokens must not be used to make requests to the resource server
		
  - Refresh tokens: a string that the OAuth client can use to get a new access token without the user's interaction. A refresh token must not allow the client to gain any access beyond the scope of the original grant. The refresh token exists to enable authorization servers to use short lifetimes for access tokens without needing to involve the user when the token expires. (this is also a grant type)

  - OAuth Scope: Scope is a mechanism in OAuth2 to limit an application's access to a user's account. An application can request one or more scopes, this information is then presented to the user in the consent screen, and the access token issued the application will be limited to the scopes granted. The OAuth specification allows the authorization server or user to modify the scopes granted to the application compared to what is requested, although there are not many examples of services doing this in practice.
  	- Some mainstream services using scopes are:
  		- Slack
		- GitHub
		- Google
		- FitBit
  		
  
##### OAuth 2.0 Grant Types (3 main grant types we care about):

  - Authorization Code: The Authorization Code grant type is used by confidential and public clients to exchange an authorization code for an access token. After the user returns to the client via the redirect URL, the application will get the authorization code from the URL and use it to request an access token.
  
  - PKCE(Proof Key for Code Exchange): PKCE is required for all OAuth clients using the authorization code flow. It's an extension to the Authorization Code flow to prevent CSRF and authorization code injection attacks. So, what you get here addresses the CSRF stuff, done earlier, that really isn't/wasn't part of the JWT Feature set. It's still never free either way. PKCE was originally intended for mobile exchanges but because of the ability to prevent code injection it was useful for OAuth client security.
  
  	- See https://developer.pingidentity.com/en/tools/pkce-code-generator.html
  
  - Client Credentials: this grant type is used by clients to obtain an access token outside of the context of a user. This is typically used by clients to access resources about themselves rather than to access a user's resources. Client Credentials grant is used when applications request an access token to access their own resources, not on behalf of a user.
  
  - Device Code: Non-Browser based devices, TV's etc. Sort of out-of-scope here.

  
##### KEYCloak Auth Server - Docker(bitNami) Keycloak Latest(Quarkus) and Postgres

   ![Alt text](./bankAngularApp/oauthsequence.jpg?raw=true "OAuth Grant Type Authentication")

   
The Angular Webapp is required to redirect to the Keycloak URL for the bcbankwebclient type for a credential login. Upon successful login the webapp client will then enhance the OAuth Authorization Grant Request with a PKCE flow. This will mitigate the security concerns over the Authorization code flow alone. Public web clients cannot securely store the client secret in javascript. Therefore, this next section will adhere to the additional layer of security with the use of a Proof Key for Code Exchange(PKCE).

Once the user clicks the login, the client app creates a crytographically-random <i><b>code-challenge - 3</b></i> and from this generates a <i><b>code_verifier - 5</b></i> See the sequence diagram below:

   ![Alt text](./bankAngularApp/oauth_with_PKCE_sequence.jpg?raw=true "OAuth Grant with PKCE enhancements")
 
The <i><b>code_challenge</b></i> is a base64-url-encoded string of the SHA256 hash of the <i><b>code_verifier</b></i>. 

The redirect to the Auth Server now contains the code_challenge that is the cached by the Auth Server(step 3). The Auth Server will then send back the <i><b>auth_code</b></i> to the client app (step 4). The <i><b>auth_code</b></i> is only good for a single use.  

In step 5, the client app sends the <i><b>auth_code</b></i> and the <i><b>code-verifier</i></b> back to the Auth server. The Auth server will then verify the <i><b>code_challenge</b></i> with the <i><b>code_verifier</b></i>. If valid, it will respond with the ID Token and bearer access token (step 6). Optionally, (depending on design and requirements) also send back a Refresh Token.


Steps 2 & 3 get enhanced with the following details:

  - <u><i><b>cient_id</b></i></u>: this is the client string id or name for the client within the auth_server.
  - <u><i><b>redirect_uri</b></i></u>: the URI value which the auth server needs to redirect after the successful authentication. 
  - <u><i><b>scope</b></i></u>: similar to authorities. This specifies the level of access the client or web app is requesting, like, READ.  
  - <u><i><b>state</b></i></u>: CSRF token value to protect from CSRF Attacks as has been discussed previously.
  - <u><i><b>response_type</b></i></u>: The grant type: code, which indicate that this is a authorization code grant type.
  - <u><i><b>code_challenge</b></i></u>: XXXXXXXXXXXX - The code challenge generated as previously described. 
  - <u><i><b>code_challenge_method</b></i></u>: S256 
  - example:
  	- https://authorization-server.com/authorize?response_type=code&client_id=nmOUhSQwkI2vZIVJXuQ3xIP0&redirect_uri=https://oauth.com/playground/authorization-code-with-pkce.html&scope=photo+offline_access&state=AgyjdAI8qOfDLVLM&code_challenge=WVfCwZRLylAm69hiD48g2O4h4nboOJGQ4iDOtpUZHpQ&code_challenge_method=S256
        https://oauth.com/playground/auth-dialog.html?response_type=code&client_id=nmOUhSQwkI2vZIVJXuQ3xIP0&redirect_uri=https://oauth.com/playground/authorization-code-with-pkce.html&scope=photo+offline_access&state=AgyjdAI8qOfDLVLM&code_challenge=WVfCwZRLylAm69hiD48g2O4h4nboOJGQ4iDOtpUZHpQ&code_challenge_method=S256

Step 5 gets enhanced with the following details:
  
   - <u><i><b>code</b></i></u>: the auth_code received from step4.
   - <u><i><b>client_id</b></i></u>: The client name or id in the auth server. This isn't a credential.
   - <u><i><b>grant_type</b></i></u>: the value of "authorization_code" which identifies the request as a auth code grant type. 
   - <u><i><b>redirect_uri</b></i></u>: the redirect of the client after the successful authentication. 
   - <u><i><b>code_verifier</b></i></u>: The code verifier for the PKCE request. This was generated by the client app along with the code challenge in step 3.
   - example:
   	 - POST https://authorization-server.com/token
	   - params:
		
		grant_type=authorization_code
		&client_id=nmOUhSQwkI2vZIVJXuQ3xIP0
		&client_secret=UWW4txsUk3s6IS4Hroht1_lzTgBRNcyhtAKsrW_lJEilVpdo   <--- optional
		&redirect_uri=https://oauth.com/playground/authorization-code-with-pkce.html
		&code=4SwEnoQv9R6A4dcEnSXTIE2XjgQyYtA2oKdn4rgcRUX7zFHr
		&code_verifier=oJwVg53w0atEzhAGsn8CSeTB2pZ9EP6ArAVTU8TE48JWHcCv
   		
   	- Response payload:
	   	{"token_type": "Bearer",
		 "expires_in": 86400,
		 "access_token": "ieRfWbUNygjNyB1aBP78wDOa9HKNfV4KMYpUSQvoSBxsHHu00cq1YExddGh3050bRhIjn1jI",
		 "scope": "photo offline_access",
		 "refresh_token": "bH7sUcC0_MQqowQ1azGmhU-w"}
   		
   Try the same: 
        
        https://oauth.com/playground/authorization-code-with-pkce.html
        
KeyCloak Angular

![Alt text](./bankAngularApp/oauthWithKeycloak.jpg?raw=true "KeyCloak Env")
