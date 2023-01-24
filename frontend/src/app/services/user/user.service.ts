import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { environment } from '~/environments/environment'
import { User } from '@models/user'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(
      environment.apiUrl + 'users/username/' + username,
    )
  }
}
