import {Component} from 'angular2/core';
import {SendMailService} from 'app/mailsender/sendService';

@Component({
    selector: 'mail-sender',
    templateUrl: 'app/mailsender/template.html'
})
export class MailSenderComponent {
    public email: string = 'acichon89@gmail.com';
    public token: string = 'aaa-bbb-ccc';
    public sth: string;
    public sth2: string;
    
    constructor(private sendService:SendMailService) { }
    
    sendMail(): void {
        alert(this.email + ' - '+this.token);
        this.sendService.sendActivationMail(this.email, this.token).subscribe(
            response => this.sth = response,
            error => this.sth2 = error);         
    }
}