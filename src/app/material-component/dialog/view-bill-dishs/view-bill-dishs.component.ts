import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-view-bill-dishs',
  templateUrl: './view-bill-dishs.component.html',
  styleUrls: ['./view-bill-dishs.component.scss']
})
export class ViewBillDishsComponent implements OnInit {
  displayedColumns:string[]=['name','menucategory','price','quantity','total'];
  dataSource: any;
  data:any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any,public dialogRef:MatDialogRef<ViewBillDishsComponent>) { }

  ngOnInit() {
    this.data=this.dialogData.data;
    this.dataSource=JSON.parse(this.dialogData.data.dishDetail);
    console.log(this.dialogData.data);
  }
}
