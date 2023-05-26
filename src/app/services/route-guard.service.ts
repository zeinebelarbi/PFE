import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { CantineService } from './cantine.service';
import jwt_decode from "jwt-decode";

import { GlobalConstants } from '../shared/global.constants';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService {

  constructor(public auth:AuthService,public router:Router,
   private cantineService:CantineService ) { }
   canActivate(route:ActivatedRouteSnapshot):boolean{
    let expectedRoleArray = route.data;
    expectedRoleArray = expectedRoleArray.expectedRole;
   const token:any=localStorage.getItem('token');
   var tokenPayload:any;
  try{
    tokenPayload=jwt_decode(token);
  }
  catch(err){
    localStorage.clear();
this.router.navigate(['/']);
  }
  let expectedRole='';
  for(let i=0; i<expectedRoleArray.length;i++){
    if (expectedRoleArray[i]== tokenPayload.role){
      expectedRole= tokenPayload.role;
    }
  }
  if (tokenPayload.role.trim() == 'user' || tokenPayload.role.trim() == 'admin') {
    if (this.auth.isAuthenticated() && tokenPayload.role.trim() == expectedRole) {
      return true;
    } else {
      this.cantineService.openCantine(GlobalConstants.unauthorized, GlobalConstants.error);
      return false;
    }
  } else {
    this.router.navigate(['/']);
    localStorage.clear();
    return false;
  }
  
}
}
