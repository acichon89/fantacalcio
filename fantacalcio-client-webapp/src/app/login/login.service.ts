import { Injectable, Inject } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {
    
    constructor(private _http: Http) {}

    login(username: string, password : string){
        alert('from service '+username+','+password);
    }
}