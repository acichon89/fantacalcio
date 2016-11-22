import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { LoginService} from './login.service';

@Component({
    selector: 'login',
    templateUrl: 'login.component.html',
    directives: [ROUTER_DIRECTIVES]
})
export class LoginComponent {

    public username : string;
    public password : string;

    //constructor(private loginService: LoginService) {}

    public login() : void {
        //this.loginService.login(this.username, this.password);
    }
    
}