import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenucategoryService {
  private url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  getMenuCategorys(): Observable<any> {
    return this.httpClient.get(this.url + '/menucategory/get');
  }

  add(data: any): Observable<any> {
    return this.httpClient.post(this.url + '/menucategory/add', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  update(data: any): Observable<any> {
    return this.httpClient.post(this.url + '/menucategory/update', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getFilteredMenuCategorys(): Observable<any> {
    return this.httpClient.get(this.url + '/menucategory/get?filterValue=true');
  }
}
