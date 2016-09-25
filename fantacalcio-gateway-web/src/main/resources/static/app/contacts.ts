import {Component} from 'angular2/core';
import {UserDataService} from './service';

@Component({
    selector: 'contacts-list',
    //directives: [...ROUTER_DIRECTIVES],
    template: `
<h3>Online Contacts</h3>
<!--div class="online-contact" *ngIf="onlineContacts?.length > 0" *ngFor="#con of onlineContacts">
    <img [src]="con.imgUrl" class="img-circle online-contact-img">
    {{ con.name }}
</div -->

<h3>Contacts</h3>
<div class="online-contact" *ngFor="#con of userContacts | async">
    <img [src]="con.imgUrl" class="img-circle offline-contact-img">
    {{ con.name }}
</div>
`
})

export class ContactsList {

    public onlineContacts: any;
    public userContacts: any;

    constructor (public userDataService: UserDataService) {
        this.userDataService.onlineContacts.subscribe(
            (onlineContacts) => {
                this.onlineContacts = onlineContacts;
            });

        this.userContacts = this.userDataService.userContacts;
    }
}