import { Routes } from '@angular/router';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
    {path:"",component:LoginComponent},
    {path:"employee/:id",component:AdminpageComponent},
    // {path:"login",component:LoginComponent}
];
