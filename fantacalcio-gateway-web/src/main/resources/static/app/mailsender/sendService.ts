import {Injectable} from 'angular2/core';
import {Http,Headers, RequestOptions, Response} from 'angular2/http';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class SendMailService {
    
    constructor(private http:Http) { }
    
    public sendActivationMail(_email:string, _token:string): Observable<String>  {
        let body = JSON.stringify({ email :  _email, token: _token});
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        
        return this.http.post('http://localhost:8070/sendConfirmAccountEmail', body, options)
                    .map(this.extractData)
                    .catch(this.handleError);
    }
    
    private handleError (error: any) {
      // In a real world app, we might use a remote logging infrastructure
      // We'd also dig deeper into the error to get a better message
      let errMsg = (error.message) ? error.message :
        error.status ? `${error.status} - ${error.statusText}` : 'Server error';
      console.error(errMsg); // log to console instead
      return Observable.throw(errMsg);
    }
    
    private extractData(res: Response) {
        let body = res.json();
        return body.data || { };
      }
}