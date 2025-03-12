import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { themeResponse } from 'src/app/pages/models/themes.models';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss'],
})
export class ThemeComponent {
  @Input() theme!: themeResponse;
  @Output() themeClicked = new EventEmitter<themeResponse>();

  constructor(protected router: Router) {}

  btnClicked() {
    this.themeClicked.emit(this.theme);
  }
}
