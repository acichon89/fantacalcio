import { Routes } from '@angular/router';

import { UserRegistrationComponent } from './register/user-register.component';
import { UserLoginComponent } from './login/user-login.component';

export const UserRoutes: Routes = [
  { path: 'register', component: UserRegistrationComponent },
  { path: 'login', component: UserLoginComponent }
];
