export class Category {
  id: number = 0;
  name: string = '';
  color: string = '';

  constructor(id: number = 0, name: string, color: string) {
    this.id = id;
    this.name = name;
    this.color = color;
  }
}
