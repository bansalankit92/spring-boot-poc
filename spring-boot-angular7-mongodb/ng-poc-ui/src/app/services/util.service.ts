import {UrlConstantService} from './url-constant.service';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DomSanitizer } from '@angular/platform-browser';
import { AppConstantsService } from './app-constants.service';

@Injectable()
export class UtilService {

  constructor(private appConstant: AppConstantsService, private sanitizer: DomSanitizer) {}


  public static validateEmail(email) {
    let re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

  public static randomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * max) + min;
  }


}
