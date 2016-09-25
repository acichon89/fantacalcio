import {Injectable} from 'angular2/core';
import {Http,Headers} from 'angular2/http';
import {Observable,BehaviorSubject,Subject} from 'rxjs/Rx';

@Injectable()
export class UserDataService {

    public loginInUser: Subject<UserStatus> = new BehaviorSubject<UserStatus>(null);
    public currentUser: UserStatus;
    public onlineContacts: Subject<UserContact> = new BehaviorSubject<UserContact>(null);
    public userContacts: Subject<UserContact> = new BehaviorSubject<UserContact>(null);

    constructor(public http:Http) {
        //this.loadUserStatus(); // this is lower in the Component... I have not included it here
        this.pollContacts();
    }

    private pollContacts() : any {

        var headers = new Headers();
        headers.append('Content-Type', 'application/json');

        console.log('init');
        return Observable.interval(3000)
            .startWith('')
            .switchMap(() => this.http.get('./app/restservice/contacts', {headers: headers}))
            //.startWith() - Would this allow me to kick off the service when the Component is loaded / called?
            .subscribe((data: any) => {
              console.log('test');
                data = data.json();
                this.onlineContacts.next(data.onlineContacts);
                this.userContacts.next(data.allContacts);
            });

    }
}