import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { ThemesService } from '../services/themes.service';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from '../models/user.models';
import { themeResponse } from '../models/themes.models';
import { SubscriptionService } from '../services/subscription.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent implements OnInit {
  form = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required, Validators.min(3)]],
    password: [
      '',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.pattern(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\W_]).+$/),
      ],
    ],
  });
  user: User = {
    id: 0,
    name: '',
    email: '',
    created_at: new Date(),
    updated_at: new Date(),
  };
  themes: themeResponse[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private themesService: ThemesService,
    private subscriptionService: SubscriptionService
  ) {}

  ngOnInit(): void {
    this.authService.me().subscribe((user) => {
      this.user = user;
      this.form.patchValue({
        email: user.email,
        username: user.name,
      });
    });
    this.themesService.getThemes().subscribe((themes) => {
      this.themes = themes.filter((theme) => theme.subscription);
    });
  }
  themeUnsubscribe(theme: themeResponse) {
    this.subscriptionService.unsubscribe(theme).subscribe(() => {
      this.themesService.getThemes().subscribe((themes) => {
        this.themes = themes.filter((theme) => theme.subscription);
      });
    });
  }
  submit() {
    this.authService
      .editprofile(this.user.id, this.form.value)
      .subscribe((user) => {
        this.user = user;
        this.form.get('password')?.reset();
      });
  }
}
