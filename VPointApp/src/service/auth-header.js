export default function authHeader(data) {
    let user = JSON.parse(localStorage.getItem('user'));
    if (user && user.token) {
        return { Authorization: 'Bearer ' + user.token,
            data:data};
    } else {
        return '';
    }
}