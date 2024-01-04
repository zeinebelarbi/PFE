import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CantineService } from 'src/app/services/cantine.service';
import { MenucategoryService } from 'src/app/services/menucategory.service';
import { GlobalConstants } from 'src/app/shared/global.constants';


@Component({
  selector: 'app-menucategory',
  templateUrl: './menucategory.component.html',
  styleUrls: ['./menucategory.component.scss']
})
export class MenucategoryComponent implements OnInit {
onAddMenuCategory = new EventEmitter();
onEditMenuCategory=new EventEmitter();
menucategoryForm:any =FormGroup;
dialogAction:any="Add";
action:any ="Add";
responseMessage:any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData:any,private formBuilder:FormBuilder,
  private menucategoryService:MenucategoryService,
  public dialogRef:MatDialogRef<MenucategoryComponent>,
  private cantineService:CantineService){}


  ngOnInit(): void {
    this.menucategoryForm=this.formBuilder.group({
    name:[null,[Validators.required]]  
    });
    if(this.dialogData.action ==='Edit'){
      this.dialogAction="Edit";
      this.action="Update"
      this.menucategoryForm.patchValue(this.dialogData.data);

    }
  }
handleSubmit(){
  if(this.dialogAction==="Edit"){
    this.edit();
  }
  else{
    this.add();
  }
}
add(){
  var formData = this.menucategoryForm.value;
  var data={
   menucategoryname: formData.name
  }
  this.menucategoryService.add(data).subscribe((response:any)=>{
    this.dialogRef.close();
    this.onAddMenuCategory.emit();
    this.responseMessage=response.message;
    this.cantineService.openCantine(this.responseMessage,"success");
  },(error)=>{

 this.dialogRef.close();

  console.error(error);
  if(error.error?.message){
    this.responseMessage = error.error?.message;
  }else {
    this.responseMessage=GlobalConstants.genericError;
  }
  this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
  });
}
edit(){
  var formData = this.menucategoryForm.value;
  var data={
    idMenuCategory:this.dialogData.data.idMenuCategory,
    menucategoryname: formData.name
  }
  this.menucategoryService.update(data).subscribe((response:any)=>{
    this.dialogRef.close();
    this.onEditMenuCategory.emit();
    this.responseMessage=response.message;
    this.cantineService.openCantine(this.responseMessage,"success");
  },(error)=>{

 this.dialogRef.close();

  console.log(error);
  if(error.error?.message){
    this.responseMessage = error.error?.message;
  }else {
    this.responseMessage=GlobalConstants.genericError;
  }
  this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
  });
}

}
