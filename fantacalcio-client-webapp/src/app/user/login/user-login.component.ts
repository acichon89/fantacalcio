import { Component } from '@angular/core';

import { UserLoginDTO } from './user-login.model';
import { UserService } from '../user.service';

@Component({
    moduleId: module.id,
    selector: 'user-login',
    templateUrl: 'user-login.html',
    providers: [UserService]
})
export class UserLoginComponent {

    public userLoginDTO: UserLoginDTO;

    constructor(private userService: UserService){
        this.userLoginDTO = new UserLoginDTO();
    }

    login() {
        alert('login!');
    }
}