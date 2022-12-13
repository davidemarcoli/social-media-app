import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '@services/alert/alert.service';
import { AuthService } from '@services/auth/auth.service';
import { z, ZodError } from 'zod';
import * as Errors from '../../../../errors';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  passwordVisible = false;

  constructor(
    private fb: FormBuilder,
    private alertService: AlertService,
    private authService: AuthService,
    private router: Router
  ) {
    this.form = this.fb.group({
      username: [
        '',
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(20),
        ],
      ],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  readonly loginUser = z.object({
    username: z
      .string()
      .min(3, Errors.USERNAME_MIN_VALIDATION)
      .max(20, Errors.USERNAME_MAX_VALIDATION),
    password: z.string(),
  });

  async login() {
    const val = this.form.value;

    try {
      this.loginUser.parse(val);
      this.authService.login(val.username, val.password).then((data) => {
        console.log('Data', data);
        this.router.navigateByUrl('home');
      });
    } catch (e) {
      console.log((e as ZodError).issues[0].message);
      this.alertService.error((e as ZodError).issues[0].message);
    }
  }

  navigateToRegister() {
    this.router.navigateByUrl('signup');
  }

  ngOnInit(): void {
    console.log(
      document.location.protocol +
        '//' +
        document.location.hostname +
        ':8081/api/'
    );
  }
}
