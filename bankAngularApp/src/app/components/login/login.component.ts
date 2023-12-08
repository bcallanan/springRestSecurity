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
  model = new User();

  constructor(private loginService: LoginService, private router: Router) {

   }

  ngOnInit(): void {

  }

  /**
   * @see login.component.html on submit this api gets called
   */
  validateUser(loginForm: NgForm) {
	// we then call the login service component ts class
    this.loginService.validateLoginDetails(this.model).subscribe(
      responseData => {
        this.model = <any> responseData.body;
        
        // Record the Session details cookie and csrf details
        let xsrf = getCookie( 'XSRF-TOKEN')!;
        window.sessionStorage.setItem( "XSRF-TOKEN", xsrf );
        
        // setting 'Auth' here relates to the header being displayed
        // see  header.components.ts
        this.model.authStatus = 'AUTH';
        window.sessionStorage.setItem("userdetails",JSON.stringify(this.model));
        
        // Redirect to the dashboard
        this.router.navigate(['dashboard']);
      });

  }

}
