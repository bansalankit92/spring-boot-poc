import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { User } from 'src/app/models/user';
import { UserDetailsService } from 'src/app/services/user-details.service';
import { UserQueryParams } from 'src/app/models/user-query-params';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.sass']
})
export class UserListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'emailId', 'fullName', 'username', 'mobile'];
  dataSource: any;
userQueryParams =  new UserQueryParams();
  constructor(private userDetailService: UserDetailsService, private toastService: ToastService) { }

  ngOnInit() {
    this.userDetailService.getUsers(this.userQueryParams).subscribe(
      userPage => {
        this.dataSource = new MatTableDataSource<User>(userPage.content);
      }, err => {
        console.log(err);
        this.toastService.showToast('Some error occurred while retrieving user list');
      }
    );
  }

}
