
export class SweetAlertService {

    constructor() { }

    showNotification(icon, title) {
        // tslint:disable-next-line:only-arrow-functions
            const Toast = this.Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });
            Toast.fire({
                icon: icon,
                title: title
            });
    }
}
