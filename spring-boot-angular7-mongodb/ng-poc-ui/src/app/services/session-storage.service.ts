import { Injectable } from '@angular/core';
import { AppConstantsService } from 'src/app/services/app-constants.service';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  static setSearchUser(value: string) {
    sessionStorage.setItem(AppConstantsService.LOCAL_SEARCH_USER, value);
  }
  static getSearchUser(): string {
    return sessionStorage.getItem(AppConstantsService.LOCAL_SEARCH_USER);
  }

  static clearSearchUser() {
    return sessionStorage.removeItem(AppConstantsService.LOCAL_SEARCH_USER);
  }

  constructor() { }


}
