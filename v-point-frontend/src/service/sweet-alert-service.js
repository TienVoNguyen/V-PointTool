

export class SweetAlertService {


    constructor() { }

    showNotification(icon, title) {
            const Toast = this.swal.mixin({
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
