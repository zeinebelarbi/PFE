import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class BillService {
url= environment.apiUrl;
  constructor(private httpClient:HttpClient ) { }
generateReport(data:any){
  return this.httpClient.post(this.url+"/bill/generatedReport",data,{
    headers:new HttpHeaders().set('Content-Type',"application/json") 
  })
}

getPdf(data:any):Observable<Blob>{
return this.httpClient.post(this.url+"/bill/getPdf",data,{responseType:'blob'});
}
getBills(){
  return this.httpClient.get(this.url+"/bill/getBills");
}

delete(id:any){
  return this.httpClient.post(this.url+"/bill/delete/"+id,{
headers:new HttpHeaders().set('Content-Type',"application/json")
  });
}
}

