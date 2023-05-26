import { Component, OnInit } from '@angular/core';
import { BillService } from 'src/app/services/bill.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { CantineService } from 'src/app/services/cantine.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from 'src/app/shared/global.constants';
import { ViewBillDishsComponent } from '../dialog/view-bill-dishs/view-bill-dishs.component';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-view-bill',
  templateUrl: './view-bill.component.html',
  styleUrls: ['./view-bill.component.scss']
})
export class ViewBillComponent implements OnInit {
  displayedColumns: string[] = ['name', 'email', 'paymentMethod', 'total', 'quantity', 'view'];
  dataSource: any;
  responseMessage: any;
  constructor(private billService:BillService,    
    private ngxService: NgxUiLoaderService,
    private dialog:MatDialog,
    private cantineService:CantineService,
    private router: Router

    ) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData(){
    this.billService.getBills().subscribe((response:any)=>{
      this.ngxService.stop();
      this.dataSource = new MatTableDataSource(response);
    },(error:any)=>{
      this.ngxService.stop();
      console.log(error);
      if(error.error?.message){
        this.responseMessage = error.error?.message;
      }else{
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
        })
  }
  applyFilter(event:Event){
    const filterValue =(event.target as HTMLInputElement).value;
    this.dataSource.filter =filterValue.trim().toLowerCase();
    }
    handleViewAction(values:any){
      const dialogConfig =new MatDialogConfig();
      dialogConfig.data ={
        data:values
      }
      dialogConfig.width="100%";
      const dialogRef = this.dialog.open(ViewBillDishsComponent,dialogConfig);
      this.router.events.subscribe(()=>{
        dialogRef.close();
    
      })
      
    }
handleDeleteAction(values:any){
  const dialogConfig = new MatDialogConfig();
  dialogConfig.data={
    message :'delete'+values.name+'bill',
    confirmation:true
  };
  const dialogRef=this.dialog.open(ConfirmationComponent,dialogConfig);
  const sub =dialogRef.componentInstance.onEmitStatusChange.subscribe((response)=>{
    this.ngxService.start();
    this.deleteBill(values.id);
    dialogRef.close()
  })
}
deleteBill(id:any){
this.billService.delete(id).subscribe((response:any)=>{
  this.ngxService.stop();
  this.tableData();
  this.responseMessage = response?.message;
  this.cantineService.openCantine(this.responseMessage,"success");
},(error:any)=>{
  this.ngxService.stop();
      console.log(error);
      if(error.error?.message){
        this.responseMessage = error.error?.message;
      }else{
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);

})
}
downloadReportAction(values:any){
  this.ngxService.start();
  var data ={
    name:values.name,
    email:values.email,
    uuid:values.uuid,
    PaymentMethod:values.PaymentMethod,
    totalAmount:values.total.toString(),
    dishDetails:values.dishDetail
  }
  this.downloadFile(values.uuid,data);
}
downloadFile(fileName:string,data:any){
  this.billService.getPdf(data).subscribe((response)=>{
    saveAs(response,fileName+'.pdf');
    this.ngxService.stop();
  })
}
}
