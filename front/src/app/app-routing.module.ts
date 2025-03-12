import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './guards/auth.guard';
import { UnauthGuard } from './guards/unauth.guard';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { NewarticleComponent } from './pages/newarticle/newarticle.component';
import { UserComponent } from './pages/user/user.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  { path: 'home', canActivate: [UnauthGuard], component: HomeComponent },
  {
    path: 'register',
    component: RegisterComponent,
  },
  { path: 'login', component: LoginComponent },
  { path: 'articles', canActivate: [AuthGuard], component: ArticlesComponent },
  {
    path: 'newarticle',
    canActivate: [AuthGuard],
    component: NewarticleComponent,
  },
  { path: 'themes', canActivate: [AuthGuard], component: ThemesComponent },
  { path: 'user', canActivate: [AuthGuard], component: UserComponent },
  { path: '**', redirectTo: '' },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
