import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UsersListComponent} from "./component/users-list/users-list.component";
import {UserFormComponent} from "./component/user-form/user-form.component";

const routes: Routes = [
  {
    path: 'users',
    component: UsersListComponent
  },
  {
    path: 'add-user',
    component: UserFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
