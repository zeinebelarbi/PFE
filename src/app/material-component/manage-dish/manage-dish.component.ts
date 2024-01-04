import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { CantineService } from 'src/app/services/cantine.service';
import { DishService } from 'src/app/services/dish.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from 'src/app/shared/global.constants';
import { DishComponent } from '../dialog/dish/dish.component';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';
@Component({
  selector: 'app-manage-dish',
  templateUrl: './manage-dish.component.html',
  styleUrls: ['./manage-dish.component.scss']
})
export class ManageDishComponent implements OnInit {
  displayedColumns: string[] = ['dishname', 'menucategoryname', 'description', 'price', 'edit'];
  dataSource: any;
  //length1:any;  
  responseMessage: any;

  constructor(private dishService: DishService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private cantineService: CantineService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData() {
    this.dishService.getDishs().subscribe((response:any)=>{
    this.ngxService.stop();
    this.dataSource = new MatTableDataSource(response);
     } ,(error:any)=>{
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
  applyFilter(event:Event){
    const filterValue =(event.target as HTMLInputElement).value;
    this.dataSource.filter =filterValue.trim().toLowerCase();
    }
    handleAddAction(){
      const dialogConfig = new MatDialogConfig();
    
       dialogConfig.data={
        action:'Add'
       };
       dialogConfig.width="850px";
       const dialogRef = this.dialog.open(DishComponent,dialogConfig);
       this.router.events.subscribe(()=>{
        dialogRef.close();
       });
       const sub =dialogRef.componentInstance.onAddDish.subscribe((response)=>{
        this.tableData();

       })
    }
    
    handleEditAction(values:any){
      const dialogConfig = new MatDialogConfig();
    
       dialogConfig.data={
        action:'Edit',
        data:values
       };
       dialogConfig.width="850px";
       const dialogRef = this.dialog.open(DishComponent,dialogConfig);
       this.router.events.subscribe(()=>{
        dialogRef.close();
       });
       const sub =dialogRef.componentInstance.onEditDish.subscribe((response)=>{
        this.tableData();
        
       })
    } 
    handleDeleteAction(values:any){
      console.log(values)
      const dialogConfig = new MatDialogConfig();
      dialogConfig.data={
       message:'delete'+values.name+'dish',
       confirmation:true
       }
       const dialogRef = this.dialog.open(ConfirmationComponent,dialogConfig);
       const sub =dialogRef.componentInstance.onEmitStatusChange.subscribe((response)=>{
        this.ngxService.start();
        this.deleteDish(values.iddish);
        dialogRef.close();
       })
    }
    deleteDish(id:any){
      this.dishService.delete(id).subscribe((response:any)=>{
        this.ngxService.stop();
        this.tableData();
        this.responseMessage=response?.message;
        this.cantineService.openCantine(this.responseMessage,"success")
      },(error:any)=>{
        this.ngxService.stop();
      console.log(error);
      if(error.error?.message){
        this.responseMessage = error.error?.message;
      }else{
        this.responseMessage = GlobalConstants.genericError;
      }
      this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);

      });
     

    }
    onChange(status:any,id:any){
      this.ngxService.start();
      var data={
        status:status.toString(),
        id:id
      }
      this.dishService.updateStatus(data).subscribe((response:any)=>{
        this.ngxService.stop(); 
        this.responseMessage=response?.message;
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
    }


