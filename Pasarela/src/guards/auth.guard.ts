import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {
    }

    canActivate(): boolean {
        console.log('user_auth');
        let data_user = localStorage.getItem('data_user');
        if (data_user != null) {
            return true;
        }else{
            this.router.navigate(['/login']);
        } 
    }

}
