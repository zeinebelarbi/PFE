import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { CantineService } from 'src/app/services/cantine.service';
import { MenucategoryService } from 'src/app/services/menucategory.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from 'src/app/shared/global.constants';
import { MenucategoryComponent } from '../dialog/menucategory/menucategory.component';
@Component({
  selector: 'app-manage-menucategory',
  templateUrl: './manage-menucategory.component.html',
  styleUrls: ['./manage-menucategory.component.scss']
})
export class ManageMenucategoryComponent implements OnInit {

displayedColumns:string[]=['name','edit'];
dataSource: any;
responseMessage:any;

  constructor(private menucategoryService:MenucategoryService,
    private ngxService:NgxUiLoaderService,
    private dialog :MatDialog,
    private cantineService: CantineService,
    private router: Router) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
tableData(){
 this.menucategoryService.getMenuCategorys().subscribe((response:any)=>{
this.ngxService.stop() ;
console.log(response);
this.dataSource = new MatTableDataSource(response);
  },(error:any)=>{
this.ngxService.stop();
console.log(error.error?.message);
if(error.error?.message){
  this.responseMessage = error.error?.message;
}else{
  this.responseMessage = GlobalConstants.genericError;
}
this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
  })
}
applyFilter(event: Event) {
  const filterValue = (event.target as HTMLInputElement).value;
  this.dataSource.filter = filterValue.trim().toLowerCase();
}

handleAddAction(){
  const dialogConfig =new MatDialogConfig();
  dialogConfig.data ={
    action:'Add'
  };
  dialogConfig.width= "850px";
  const dialogRef = this.dialog.open(MenucategoryComponent,dialogConfig);
  this.router.events.subscribe(()=>{
    dialogRef.close();

  });
  const sub =dialogRef.componentInstance.onAddMenuCategory.subscribe((response)=>{
    this.tableData();
  })
}
handleEditAction (values:any){
    const dialogConfig =new MatDialogConfig();
    dialogConfig.data ={
      action:'Edit',
      data:values

    };
    dialogConfig.width= "850px";
    const dialogRef = this.dialog.open(MenucategoryComponent,dialogConfig);
    this.router.events.subscribe(()=>{
      dialogRef.close();
  
    });
    const sub =dialogRef.componentInstance.onEditMenuCategory.subscribe((response)=>{
      this.tableData();
    })
}
}
