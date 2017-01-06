import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Rx';
import { UserRegistrationDTO } from './register/user-register.model';

@Injectable()
export class UserService {

    constructor (private http: Http) {}

    register(dto: UserRegistrationDTO): Observable<string> {
        return this.http.post('localhost:8060/user/register', dto).map(response => response.json());
    }

}