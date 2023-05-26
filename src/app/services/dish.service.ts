import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient,HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DishService {
  url = environment.apiUrl;

  constructor(private httpClient:HttpClient ) { }

  getToken() {
   
    return localStorage.getItem('token');
  }

  add(data:any){
    return this.httpClient.post(this.url +"/dish/add",data,{
      headers:new HttpHeaders().set('Content-Type',"application/json").set('Authorization', 'Bearer ' + this.getToken())
    })
  }

  update(data:any){
    return this.httpClient.post(this.url +"/dish/update",data,{
      headers:new HttpHeaders().set('Content-Type',"application/json").set('Authorization', 'Bearer ' + this.getToken())
    })
  }

  getDishs(){
    return this.httpClient.get(this.url+"/dish/get",{
      headers:new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken())
    });
  }

  updateStatus(data:any){
    return this.httpClient.post(this.url +"/dish/updateStatus",data,{
      headers:new HttpHeaders().set('Content-Type',"application/json").set('Authorization', 'Bearer ' + this.getToken())
    })
  }

  delete (iddish:any){
    return this.httpClient.delete(this.url +"/dish/delete/"+iddish,{
      headers:new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken())
    });
  }

  getDishsByMenucategory(id:any){
    console.log(id)
    return this.httpClient.get(this.url+"/dish/getByMenuCategory/"+id,{
      headers:new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken())
    });
  }

  getById(id:any){
    return this.httpClient.get(this.url+"/dish/getById/"+id,{
      headers:new HttpHeaders().set('Authorization', 'Bearer ' + this.getToken())
    });
  }
}
