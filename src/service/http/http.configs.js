import http from './http.service';
import config from './config';

export const login = (body) => {
    let url = config.path.baseUrl + config.path.login;
    return http(url,body)
}