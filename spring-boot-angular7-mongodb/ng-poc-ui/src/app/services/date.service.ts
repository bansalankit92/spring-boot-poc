import { Injectable } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable()
export class DateService {
static getCurrentDate() {
  return new Date();
}

static addDaysToDate(dateVal: Date, days: number) {
  return dateVal.setDate(dateVal.getDate() + days);
}

yyyymmddFormat(date) {
  return this.datePipe.transform(date, 'dd/MM/yyyy');
}

getFormattedDate(date, format) {
  return this.datePipe.transform(date, format);
}


  constructor(private datePipe: DatePipe) { }

}
