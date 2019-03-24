import {User} from '../models/user';
import { Injectable, EventEmitter } from '@angular/core';

@Injectable()
export class BroadcastService {
  // private user: User;
  // private redirectTo: string;
  private _user: User;
  private _redirectTo: string;
  searchEvent: EventEmitter<string> = new EventEmitter();

  constructor() {}

  public get user(): User {
    return this._user;
  }

  public set user(value: User) {
    this._user = value;
  }

  public get redirectTo(): string {
    return this._redirectTo;
  }

  public set redirectTo(value: string) {
    this._redirectTo = value;
  }

  emitSearchEvent(val: string) {
    this.searchEvent.emit(val);
  }
  getSearchEvent(): EventEmitter < string > {
    return this.searchEvent;
  }
}

