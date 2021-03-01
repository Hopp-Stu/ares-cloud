import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid, rememberMe) {
  const data = {
    username,
    password,
    code,
    uuid,
    rememberMe
  }
  return request({
    url: '/ares/login',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/ares/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/ares/loginOut',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/ares/kaptcha',
    method: 'get'
  })
}