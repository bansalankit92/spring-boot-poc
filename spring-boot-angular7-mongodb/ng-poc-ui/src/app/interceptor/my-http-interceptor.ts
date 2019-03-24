import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpResponse } from '@angular/common/http';

import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class MyHttpInterceptor implements HttpInterceptor {
  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    const updatedRequest = request.clone({
        headers: request.headers.set('Authorization', 'Basic PD46PD4=')
      });
    return next.handle(updatedRequest).pipe(
        tap(
          event => {
            // logging the http response to browser's console in case of a success
            if (event instanceof HttpResponse) {
            }
          },
          error => {
            // logging the http response to browser's console in case of a failuer
            if (event instanceof HttpResponse) {
            }
          }
        )
      );
  }
}
