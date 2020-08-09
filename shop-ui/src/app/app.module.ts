import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {Api} from "./api";
import { UsersListComponent } from './component/users-list/users-list.component';
import {UserService} from "./service/user.service";
import { UserFormComponent } from './component/user-form/user-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UsersSearchComponent } from './component/users-search/users-search.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersListComponent,
    UserFormComponent,
    UsersSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    Api,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
