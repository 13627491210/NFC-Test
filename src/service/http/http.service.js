import config from './config.json';

export default async function feachApi(url,body){
    let method = "GET";
    let headers = {
        "Accept":"application/json",
        "Content-Type":"application/json"
    }
    if(body) method = "POST";

    return fetch(url,{
        method:method,
        headers:headers,
        body:JSON.stringify(body)
    }).then(function(response){
        if(response.status == 401){
            return {
                status:'error'
            }
        }
        return response.json();
    }).catch(error => {
        console.error(error);
    })
}