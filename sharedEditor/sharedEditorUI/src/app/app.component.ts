import { Component,OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse,HttpParams,HttpHeaders } from '@angular/common/http';
import {AppService} from './app.service';
import {environment} from '../environments/environment';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
	entityURL = environment.URL+"/api/create"
	text:string;
	constructor(private http : HttpClient,private appService:AppService ){
		
	}
	ngOnInit() {
		this.appService.callService(this.entityURL+"/123");
	}
  mouseEnter(){
	 
	  
  }
  title = 'sharedEditorUI';
}
