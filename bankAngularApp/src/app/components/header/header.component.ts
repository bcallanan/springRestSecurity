import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user.model';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';
import { publishFacade } from '@angular/compiler';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  
  user = new User();

  public isLoggedIn = false;
  public userKeycloakProfile: KeycloakProfile | null = null;

  constructor( private readonly keycloakService: KeycloakService ) {}

  public async ngOnInit() {

    this.isLoggedIn = await this.keycloakService.isLoggedIn();

    if ( this.isLoggedIn ) {
      this.userKeycloakProfile = await this.keycloakService.loadUserProfile();
      this.user.authStatus = 'AUTH';
      this.user.name = this.userKeycloakProfile.firstName || "";
      window.sessionStorage.setItem( "userDetails", JSON.stringify( this.user));
    }
  }
  /**
   * logout
   */
  public logout() {
    let redirectUri: string = environment.getRedirectUrl() + "/home";
    this.keycloakService.logout( redirectUri );
  }

  /**
   * login
   */
  public login(): void {
    this.keycloakService.login();
  }
}