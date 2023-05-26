import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { CantineService } from '../services/cantine.service';
import { MatDialogRef } from '@angular/material/dialog';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from '../shared/global.constants';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
hide =true;
confirmPassword =true;
signupForm:any=FormGroup;
responseMessage:any;
constructor(private formBuilder:FormBuilder,private router :Router,  private userservice:UserService,private cantineService:CantineService,public dialogRef:MatDialogRef<SignupComponent>,private ngxService:NgxUiLoaderService ) { 
  
  }

  ngOnInit(): void {
 this.signupForm=this.formBuilder.group({
 username:[null,[Validators.required,Validators.pattern(GlobalConstants.nameRegex)]],
 email:[null,[Validators.required,Validators.pattern(GlobalConstants.emailRegex)]],
password:[null,[Validators.required]],
confirmPassword:[null,[Validators.required]]
})

}
validateSubmit(){
  if(this.signupForm.controls['password'].value !=this.signupForm.controls['confirmPassword'].value){
    return true;
  }
  else{
    return false;
  }
}
handleSubmit(){
  this.ngxService.start();
  var formData =this.signupForm.value;
  var data={
    username:formData.username,
    email:formData.email,
    password:formData.password
  }
  this.userservice.signup(data).subscribe((response:any)=>{
    this.ngxService.stop();
    this.dialogRef.close();
    this.responseMessage =response?.message;
    this.router.navigate(['/']);

  },(error)=>{
    this.ngxService.stop();
    if(error.error?.message){
      this.responseMessage = error.error?.message;
    }else{
      this.responseMessage=GlobalConstants.genericError;
    }
    this.cantineService.openCantine(this.responseMessage,GlobalConstants.error);
  })
}
}
