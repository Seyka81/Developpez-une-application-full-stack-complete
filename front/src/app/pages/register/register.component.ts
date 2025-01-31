import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { RegisterRequest } from '../models/registerRequest.models';
import { AuthSuccess } from '../models/authSuccess.models';
import { User } from '../models/user.models';
import { SessionService } from '../services/session.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  public onError = false;
  public form = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    username: ['', [Validators.required, Validators.min(3)]],
    password: ['', [Validators.required, Validators.min(3)]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService
  ) {}

  public submit(): void {
    const registerRequest = this.form.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe({
      next: (response: AuthSuccess) => {
        localStorage.setItem('token', response.token);
        this.authService.me().subscribe({
          next: (user: User) => {
            this.sessionService.logIn(user);
            this.router.navigate(['login']);
          },
          error: () => {
            console.error((this.onError = true));
          },
        });
      },
      error: () => {
        this.onError = true;
      },
    });
  }
}
