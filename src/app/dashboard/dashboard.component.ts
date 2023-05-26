import { Component, AfterViewInit } from '@angular/core';
import { DashbordService } from '../services/dashbord.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { CantineService } from '../services/cantine.service';
import { GlobalConstants } from '../shared/global.constants';
@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit {
responseMessage:any;
data:any;
	ngAfterViewInit() { }

	constructor(private dashboardService:DashbordService,private ngxService:NgxUiLoaderService,private cantineService:CantineService ) {
	this.ngxService.start();
	this.dashboardData();
	}
dashboardData(){
this.dashboardService.getDetails().subscribe((response:any)=>{
	this.ngxService.stop();
	this.data = response;
},(error:any)=>{
this.ngxService.stop();
console.log(error);
if(error.error?.message){
	this.responseMessage =error.error?.message;
}
else{
	this.responseMessage = GlobalConstants.genericError;
}
this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
})

}
}
