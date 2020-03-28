import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class LoginGuard implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate() {
        console.log('user_login');
        let data_user = localStorage.getItem('data_user');
        if (data_user != null) {
            this.router.navigate(['']);
        }else{
            console.log('1');
            return true;
        } 
    }

}
