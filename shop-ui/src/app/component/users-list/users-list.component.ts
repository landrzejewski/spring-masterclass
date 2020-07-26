import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {PagedResultModel} from "../../model/paged-result.model";
import {UserModel} from "../../model/user.model";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent {

  pagedResult: PagedResultModel<UserModel>;

  constructor(private userService: UserService) {
    this.reload(0);
  }

  private reload(pageNumber: number) {
    this.userService.getUsers(pageNumber)
      .subscribe(pagedResult => this.pagedResult = pagedResult, error => console.log(error));
  }

  previous() {
    this.reload(this.pagedResult.pageNumber - 1);
  }

  next() {
    this.reload(this.pagedResult.pageNumber + 1);
  }

}
