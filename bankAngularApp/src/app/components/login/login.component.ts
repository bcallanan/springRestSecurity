import { Component, OnInit } from '@angular/core';
import { User } from "src/app/model/user.model";
import { NgForm } from '@angular/forms';
import { LoginService } from 'src/app/services/login/login.service';
import { Router } from '@angular/router';
import { getCookie } from 'typescript-cookie';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  authStatus: string = "";
  user = new User();

  constructor(private loginService: LoginService, private router: Router) {}

  // eslint-disable-next-line @angular-eslint/no-empty-lifecycle-method
  ngOnInit(): void {}

  /**
   * @see login.component.html on submit this api gets called
   */
  validateUser( loginForm: NgForm ) {
	// we then call the login service component ts class
    this.loginService.validateLoginDetails( this.user ).subscribe (

      responseData => {
  
        window.sessionStorage.setItem("Authorization", responseData.headers.get( 'Authorization') ! );

        this.user = <User> responseData.body;
  
        // Record the Session details cookie and csrf details
        let xsrf = getCookie( 'XSRF-TOKEN')!;
        if ( ! xsrf ) {
          xsrf = getCookie( 'X-XSRF-TOKEN')!;
        }
        window.sessionStorage.setItem( "XSRF-TOKEN", xsrf );
      
        // setting 'Auth' here relates to the header being displayed
        // see  header.components.ts
        this.user.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails", JSON.stringify(this.user));
        
        // Redirect to the dashboard
        this.router.navigate(['dashboard']);
      }
    );
  }
}
