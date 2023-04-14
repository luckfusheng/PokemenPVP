import axios from 'axios'
import router from '@/router'
import {Message} from 'element-ui'

const request = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL
})

// const whiteList = ['/user/login','/code']
// request.interceptors.request.use(config=>{
//     if(whiteList.indexOf(config.url)>=0){
//         return config
//     }
//     config.headers["Authorization"] = sessionStorage.getItem('token')
//     return config
// }, e => { 
//     return Promise.reject(e)
// })
//axios 响应拦截器
request.interceptors.response.use(resp => { 
    const { data } = resp
    
    // const { code , message } = data
    // if(code == 401){
    //     sessionStorage.clear()
    //     Message.error(message)
    //     router.push('/login')
    //     return  Promise.reject(message)
    // }
    return data
}, e => { 
    return Promise.reject(e)
})

export default request