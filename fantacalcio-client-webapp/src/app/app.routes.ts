import { provideRouter, RouterConfig } from '@angular/router';

import { HelloComponent } from './hello/hello.component';
import { CalendarComponent } from './calendar/calendar.component';
import { HomeComponent } from './home/home.component';
import {LoginComponent} from './login/login.component';

const routes: RouterConfig = [
    {path: '', component: HomeComponent},
    {path: 'calendar', component: CalendarComponent},
    {path: 'hello', component: HelloComponent},
    {path: 'login', component: LoginComponent}
];

export const appRouterProviders = [
    provideRouter(routes)
];