import { Routes, RouterModule } from '@angular/router';

import { HomeRoutes } from './home/index';
import { TodolistRoutes } from './todolist/index';
import { UserRoutes } from './user/user.routes';

const appRoutes: Routes = [
    ...HomeRoutes,
    ...TodolistRoutes,
    ...UserRoutes
];

export const appRoutingProviders: any[] = [

];

export const routing = RouterModule.forRoot(appRoutes);
