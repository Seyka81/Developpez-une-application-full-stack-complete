import { Component, OnInit } from '@angular/core';
import { ThemesService } from '../services/themes.service';
import { themeResponse } from '../models/themes.models';
import { SubscriptionService } from '../services/subscription.service';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesComponent implements OnInit {
  constructor(
    private themesService: ThemesService,
    private subscriptionService: SubscriptionService
  ) {}
  themes: BehaviorSubject<themeResponse[]> = new BehaviorSubject<
    themeResponse[]
  >([]);

  ngOnInit(): void {
    this.getThemes();
  }

  getThemes() {
    this.themesService.getThemes().subscribe((themes: themeResponse[]) => {
      this.themes.next(themes);
    });
  }

  themeClick(theme: themeResponse) {
    if (theme.subscription) {
      this.subscriptionService.unsubscribe(theme).subscribe(() => {
        this.getThemes();
      });
    } else {
      this.subscriptionService.subscribe(theme).subscribe(() => {
        this.getThemes();
      });
    }
  }
}
