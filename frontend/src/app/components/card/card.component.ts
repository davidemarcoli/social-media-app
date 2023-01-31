import { Component, Input } from '@angular/core'

@Component({
  selector: 'dl-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
})
export class CardComponent {
  // Props
  @Input() title?: string
  @Input() subtitle?: string
}
