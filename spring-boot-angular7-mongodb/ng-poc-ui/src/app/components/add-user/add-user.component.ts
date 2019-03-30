import { Component, OnInit } from '@angular/core';
import { UserDetailsService } from 'src/app/services/user-details.service';
import { User } from 'src/app/models/user';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.sass']
})
export class AddUserComponent implements OnInit {

  user: User = new User();
  error = '';
  constructor(private userDetailsServise: UserDetailsService, private toastService: ToastService) { }

  ngOnInit() {
  }
  submitUserDetails() {
    this.userDetailsServise.post(this.user).subscribe(resp => {
     // console.log(resp);
      this.user = resp;
      this.toastService.showToast('Profile Updated Successfully!!!');
    }, err => {
     // console.log("some error occurred while saving", err);
      this.error = err.error_message;
      this.toastService.showToast('Some error occurred. Contact Admin!!!');
    });
  }
}
