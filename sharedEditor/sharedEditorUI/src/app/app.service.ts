import { Injectable } from '@angular/core';
//import { Observable } from "rxjs/Observable";
import { HttpClient, HttpErrorResponse,HttpParams,HttpHeaders } from '@angular/common/http';
import {environment} from '../environments/environment';
import { map } from 'rxjs/operators';

@Injectable()
export class AppService {
	constructor(private http: HttpClient){}
	callService(url) {
		return this.http.get(url).pipe(map((response: Response) => response.json()));
	}
}