import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private snackBar: MatSnackBar) {}

  showToast(message: string, action = '') {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
