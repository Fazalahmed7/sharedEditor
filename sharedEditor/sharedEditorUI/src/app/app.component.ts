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
	url: string;
	constructor(private http : HttpClient,private appService:AppService ){
		
	}
	ngOnInit() {
		let urlDetails = window.location.href
		let arr = urlDetails.split("/");
		let name = arr[arr.length-1];
		this.appService.callService(this.entityURL+"/"+name).subscribe(it=>{
			console.log(it)
			this.url = this.entityURL+"/"+JSON.stringify(it);
			console.log(this.url)
		});
	}
  mouseEnter(){
	 
	  
  }
  title = 'sharedEditorUI';
}
