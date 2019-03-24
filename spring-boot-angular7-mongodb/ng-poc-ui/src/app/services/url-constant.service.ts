import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class UrlConstantService {

  public readonly URL_API_PATH = '/api';
  public readonly BACKEND_BASE_URL_API_PATH = environment.REST_API_LOCATION + this.URL_API_PATH;
  public readonly BACKEND_BASE_URL = environment.REST_API_LOCATION;
  public readonly USER_URL = '/user';
  public readonly USERNAME_START_URL = this.USER_URL + '/';

  public readonly USERNAME_END_URL = '/username';
  public readonly URL_SEPARATOR = '/';



  constructor() { }

}
