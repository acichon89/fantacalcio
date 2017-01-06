import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { UserRegistrationComponent } from './register/user-register.component';
import { UserLoginComponent } from './login/user-login.component';

@NgModule({
    declarations: [
        UserRegistrationComponent,
        UserLoginComponent
    ],
    imports: [
        FormsModule,
        BrowserModule
    ],
    exports: [
        UserRegistrationComponent,
        UserLoginComponent
    ]
})
export class UserModule {
}
