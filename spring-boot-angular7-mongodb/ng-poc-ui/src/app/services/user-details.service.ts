import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {UrlConstantService} from './url-constant.service';
import {HttpService} from './http.service';
import { HttpParams } from '@angular/common/http';
import { HttpParamsOptions } from '@angular/common/http/src/params';
import { UserDetailsDto } from '../models/user-details-dto';
import { Pageable } from '../models/pageable';
import { User } from '../models/user';
import { UserQueryParams } from '../models/user-query-params';


@Injectable()
export class UserDetailsService {

  url = '';

  constructor(private httpService: HttpService, private urlConstantsService: UrlConstantService) {
    this.url = this.urlConstantsService.BACKEND_BASE_URL_API_PATH +
      this.urlConstantsService.USER_URL;
  }

  get(username): Observable < UserDetailsDto > {
    return this.httpService.get(this.urlConstantsService.BACKEND_BASE_URL_API_PATH +
      this.urlConstantsService.USERNAME_START_URL + username +
      this.urlConstantsService.USERNAME_END_URL);
  }

  post(body): Observable < any > {
    return this.httpService.post(this.url, body);
  }

  put(id: string, body: any): Observable < any > {
    return this.httpService.put(this.url, id, body);
  }
  putUser(body: any): Observable < any > {
    return this.httpService.putWithoutId(this.url, body);
  }

  getUsers(queryParams: UserQueryParams): Observable < Pageable > {
    const httpParams: HttpParamsOptions = {fromObject: queryParams as object} as HttpParamsOptions;
    return this.httpService.get(this.url, new HttpParams(httpParams));
  }

  getAll(): Observable<User> {
    return this.httpService.get(this.url);
  }

}
