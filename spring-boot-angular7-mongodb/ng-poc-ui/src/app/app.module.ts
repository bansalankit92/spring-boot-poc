import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MyHttpInterceptor } from './interceptor/my-http-interceptor';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule,
  MatIconModule, MatListModule, MatTableModule,
  MatSnackBarModule, 
  MatInputModule,
  MatCardModule} from '@angular/material';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddUserComponent } from './components/add-user/add-user.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { ToastService } from './services/toast.service';
import { FormsModule } from '@angular/forms';
import { UserDetailsService } from './services/user-details.service';
import { UrlConstantService } from './services/url-constant.service';
import { HttpService } from './services/http.service';

@NgModule({
  declarations: [
    AppComponent,
    UserDashboardComponent,
    AddUserComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTableModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatCardModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MyHttpInterceptor,
      multi: true
    },
    ToastService,
    UserDetailsService,
    HttpService,
    UrlConstantService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
