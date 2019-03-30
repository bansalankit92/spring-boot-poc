import {UrlConstantService} from './url-constant.service';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable()
export class HttpService {

  constructor(private http: HttpClient) {}

  get(url, httpParams ?: HttpParams): Observable < any > {
    if (httpParams) {
      httpOptions['params'] = httpParams;
    }
    return this.http.get(url, httpOptions);
  }

  post(url, body): Observable < any > {
    return this.http.post(url, body, httpOptions);
  }


  // Update a comment
  put(url, id: string, body: any): Observable < any > {
    return this.http.put(`${url}/${id}`, body, httpOptions); // ...using put request

  }

  putWithoutId(url, body: any): Observable < any > {
    return this.http.put(`${url}`, body, httpOptions);
  }

  postMultipart(url, body): Observable < any > {

    return Observable.create(observer => {
      const formData: FormData = new FormData();
      for (const key in body) {
        formData.append(key, body[key]);
      }
      const xhr: XMLHttpRequest = new XMLHttpRequest();
      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            observer.next(JSON.parse(xhr.response));
            observer.complete();
          } else {
            observer.error(xhr.response);
          }
        }
      };
      xhr.open('POST', url, true);
      xhr.send(formData);
    });
  }


}
