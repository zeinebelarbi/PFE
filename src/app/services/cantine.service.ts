
import { Injectable } from '@angular/core';

import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CantineService {

  constructor(private cantine:MatSnackBar) { }
  openCantine(message:string,action:string){
    if(action=='error'){
this.cantine.open(message,'',{
horizontalPosition:'center',
verticalPosition:'top',
duration:2000,
panelClass:['black-snackbar']
    });  


}
    else{
      if(action=='error'){
        this.cantine.open(message,'',{
        horizontalPosition:'center',
        verticalPosition:'top',
        duration:2000,
        panelClass:['green-snackbar']
            });

    } 
  }
  }
}
