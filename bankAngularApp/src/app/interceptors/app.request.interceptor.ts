import { Injectable } from '@angular/core';
import { HttpInterceptor,HttpRequest,HttpHandler,HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { User } from 'src/app/model/user.model';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  user = new User();
  constructor(private router: Router) {}

  intercept( req: HttpRequest<any>, next: HttpHandler) {
	  
    let httpHeaders = new HttpHeaders();
    
    //Check the session storage for the user. 
    if ( sessionStorage.getItem('userdetails')){
      this.user = JSON.parse(sessionStorage.getItem('userdetails')!);
    }
    

    // the trickery here is whether the user has email. then its a login
    if ( this.user && this.user.password && this.user.emailAddress ) {
      httpHeaders = httpHeaders.append('Authorization', 'Basic ' +
    		  window.btoa( this.user.emailAddress + ':' + this.user.password ));
    } else {
        // Check the session storage for the authorization header. It wont be there during login
        // so we drop to the else and get it.
        let authorization = sessionStorage.getItem('Authorization');
        if ( authorization ) {
        	// this is the JWT Token
        	httpHeaders = httpHeaders.append('Authorization', authorization );
        }
    }

    let xsrf = sessionStorage.getItem( 'XSRF-TOKEN' );
    // get the xsrf token from the sesion storage
    // if present put in the header
    if ( xsrf ) {
      httpHeaders = httpHeaders.append( 'X-XSRF-TOKEN', xsrf );
    }
    
    httpHeaders = httpHeaders.append('X-Requested-With', 'XMLHttpRequest');
    const xhr = req.clone({
      headers: httpHeaders
    });
  return next.handle( xhr ).pipe( tap (
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status !== 401) {
            return;
          }
          this.router.navigate(['dashboard']);
        }
      }));
  }
}