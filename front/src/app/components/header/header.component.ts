import {
  Component,
  ElementRef,
  HostListener,
  Input,
  OnInit,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionService } from 'src/app/pages/services/session.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  isNavOpen: boolean = false;
  @Input() isAuth: boolean = false;

  constructor(private sessionService: SessionService) {}

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: MouseEvent): void {
    const clickedoutside = (event.target as HTMLElement).classList.contains(
      'aside'
    );
    const clickedOnbar = (event.target as HTMLElement).classList.contains(
      'bar'
    );
    if (!clickedoutside && !clickedOnbar) {
      this.isNavOpen = false;
    }
  }
  openNav() {
    this.isNavOpen = !this.isNavOpen;
  }
}
