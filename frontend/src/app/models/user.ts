export class User {
  id: string;
  email: string;
  username: string;
  password: string;
  roles: {id: number, name: string}[];

  constructor(id: string, email: string, username: string, password: string, roles: {id: number, name: string}[] = []) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.password = password;
    this.roles = roles;
  }
}
