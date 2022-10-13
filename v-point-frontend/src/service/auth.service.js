import {jsonRequest} from '@/commons/http-common'

class AuthService {
    login(user) {
        return jsonRequest
            .post('/auth/signin', {
                username: user.username,
                password: user.password
            })
            .then(response => {
                console.log(response)
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    autologin(param) {
        const url = 'account/autologin/' + param
        return jsonRequest
            .get(url)
            .then(response => {
                console.log(response)
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return jsonRequest.post('/auth/signup', {
            username: user.username,
            email: user.email,
            password: user.password,
            fullName: user.fullName
        });
    }
}

export default new AuthService();