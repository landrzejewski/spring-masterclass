import {Component, EventEmitter, Output} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../../service/user.service";
import {debounceTime, filter, map} from "rxjs/operators";
import {Observable} from "rxjs";
import {UserModel} from "../../model/user.model";

@Component({
  selector: 'app-users-search',
  templateUrl: './users-search.component.html',
  styleUrls: ['./users-search.component.css']
})
export class UsersSearchComponent {

  searchForm = new FormGroup({
    'search': new FormControl()
  })

  @Output()
  search = new EventEmitter()

  constructor(private userService: UserService) {
    this.searchForm.get('search').valueChanges
      .pipe(debounceTime(500))
      //.pipe(filter((text) => text.length >= 3))
      //.pipe(map((text) => text.toLowerCase()))
      .subscribe((text) => this.searchUsers(text), (error) => console.log(error));
  }

  private searchUsers(text: string) {
    const result = this.userService.getUsers(text);
    this.search.emit(result);
  }

}
