import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '@services/auth/auth.service';
import {Router} from '@angular/router';
import {AlertService} from '@services/alert/alert.service';
import {z, ZodError} from 'zod';
import * as Errors from '../../../../errors';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  form: FormGroup;
  passwordVisible = false;
  readonly signupUser = z.object({
    email: z.string().email(Errors.EMAIL_VALIDATION),
    username: z
      .string()
      .min(3, Errors.USERNAME_MIN_VALIDATION)
      .max(20, Errors.USERNAME_MAX_VALIDATION),
    password: z.string().min(8, Errors.PASSWORD_MAX_VALIDATION),
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private alertService: AlertService
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
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

  async signup() {
    const val = this.form.value;

    try {
      this.signupUser.parse(val);
      this.authService
        .signup(val.email, val.username, val.password)
        .then((data) => {
          console.log('Data', data);
          this.router.navigateByUrl('home');
        })
        .catch((reason) => {
          console.error('Error', reason);
          this.alertService.error(reason.error.message);
        });
    } catch (e) {
      console.log((e as ZodError).issues[0].message);
      this.alertService.error((e as ZodError).issues[0].message);
    }
  }

  backToLogin() {
    this.router.navigateByUrl('login');
  }
}
