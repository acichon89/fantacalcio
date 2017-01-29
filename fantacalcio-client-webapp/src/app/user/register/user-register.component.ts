import { Component } from '@angular/core';

import { UserRegistrationDTO } from './user-register.model';
import { UserService } from '../user.service';

@Component({
    moduleId: module.id,
    selector: 'user-register',
    templateUrl: 'user-register.html',
    providers: [UserService]
})
export class UserRegistrationComponent {

    public userRegistrationDTO: UserRegistrationDTO;

    constructor(private userService: UserService){
        this.userRegistrationDTO = new UserRegistrationDTO();
    }

    register() {
        this.userService.register(this.userRegistrationDTO).subscribe(message => alert(message), error => alert("chuj"));
    }
}
