import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { SignupComponent } from '../signup/signup.component';
import { ForgotpasswordComponent } from '../forgot-password/forgotpassword.component';
import { LoginComponent } from '../login/login.component';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private dialog :MatDialog,
    private userService:UserService,
    private router :Router) { }

  ngOnInit(): void {
    this.userService.checkToken().subscribe((Response:any)=>{
      this.router.navigate(['canteen/dashbord']);
    },(error:any)=>{
      console.log(error);
    })
  }
handleSignupAction(){
  const dialogconfig = new MatDialogConfig();
  dialogconfig.width = "550px";
  this.dialog.open(SignupComponent,dialogconfig);
}
handleForgotPasswordAction(){
  const dialogconfig = new MatDialogConfig();
  dialogconfig.width = "550px";
  this.dialog.open(ForgotpasswordComponent,dialogconfig);
}

handleLoginAction(){
  const dialogconfig = new MatDialogConfig();
  dialogconfig.width = "550px";
  this.dialog.open(LoginComponent,dialogconfig);
}
}
