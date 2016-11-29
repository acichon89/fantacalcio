import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { UserRegistrationComponent } from './register/user-register.component';

@NgModule({
    declarations: [
        UserRegistrationComponent
    ],
    imports: [
        FormsModule,
        BrowserModule
    ],
    exports: [
        UserRegistrationComponent
    ]
})
export class UserModule {
}
