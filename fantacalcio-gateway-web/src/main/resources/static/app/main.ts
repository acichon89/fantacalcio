import {bootstrap} from 'angular2/platform/browser';
import {HTTP_PROVIDERS} from 'angular2/http';
import {MailSenderComponent} from './mailsender/send';
import {SendMailService} from './mailsender/sendService';

bootstrap(MailSenderComponent, [HTTP_PROVIDERS, SendMailService]);