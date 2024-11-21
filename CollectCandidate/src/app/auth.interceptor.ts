import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("api calling......")
  console.log(req.url)
  const token=sessionStorage.getItem('access_token');
  console.log("interceptor",token)  

  const authReq=token? req.clone({
    setHeaders:{
      Authorization:`${token}`
    },
  }) : req
  // console.log("request without token",req);
  return next(authReq);
};
