import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AppConstantsService } from '../services/app-constants.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(private router: Router, private appConstantsService: AppConstantsService) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      const user = true;
      if (user) {
        return true;
      } else {
        this.router.navigate([this.appConstantsService.HOME]);
        return false;
      }
  }
}
