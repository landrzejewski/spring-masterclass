import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {Api} from "./api";
import { UsersListComponent } from './component/users-list/users-list.component';
import {UserService} from "./service/user.service";
import { UserFormComponent } from './component/user-form/user-form.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    UsersListComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    Api,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
