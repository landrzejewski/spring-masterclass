import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserModel} from "../model/user.model";
import {Observable} from "rxjs";
import {Api} from "../api";
import {PagedResultModel} from "../model/paged-result.model";

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient, private api: Api) {
  }

  addUser(user: UserModel): Observable<UserModel> {
    return this.httpClient.post<UserModel>(this.api.users, user);
  }

  getUsers(pageNumber = 0, pageSize = 5): Observable<PagedResultModel<UserModel>> {
    const params = { pageNumber: `${pageNumber}`, pageSize: `${pageSize}`, lastNameFragment: "" };
    return this.httpClient.get<PagedResultModel<UserModel>>(this.api.users, { params });
  }

}
