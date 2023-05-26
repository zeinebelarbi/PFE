import { Component } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ChangePasswordComponent } from 'src/app/material-component/dialog/change-password/change-password.component';
import { ConfirmationComponent } from 'src/app/material-component/dialog/confirmation/confirmation.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: []
})
export class AppHeaderComponent {
   role :any;
   constructor(private router:Router,
    private dialog:MatDialog) {
  }
  logout(){
    const dialogConfig =new MatDialogConfig();
    dialogConfig.data = {
      message:'Are you sure you want to Logout', // Change this to the full message you want to display
      confirmation:true
    };
    const dialogRef = this.dialog.open(ConfirmationComponent,dialogConfig);
    const sub =dialogRef.componentInstance.onEmitStatusChange.subscribe((response)=>{
    dialogRef.close();
    localStorage.clear();
    this.router.navigate(['/']);
  })
}
  ChangePassword() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    dialogConfig.autoFocus = false; 
    this.dialog.open(ChangePasswordComponent, dialogConfig);
  }
}
