// mock/index.js
export default [
  {
    url: '/api/user/login', // 匹配 request.js 拼接后的完整路径
    method: 'post',
    response: (req) => {
      const { username, password } = JSON.parse(req.body);
      if (username && password) {
        return {
          code: 200,
          msg: '登录成功',
          data: { token: `mock-token-${Date.now()}` }
        };
      }
      return {
        code: 400,
        msg: '用户名或密码不能为空',
        data: null
      };
    }
  },
  {
    url: '/api/user/info', // 用户信息接口
    method: 'get',
    response: () => {
      return {
        code: 200,
        msg: '获取用户信息成功',
        data: {
          id: 1,
          username: 'admin',
          name: '管理员',
          avatar: 'https://example.com/avatar.jpg',
          roles: ['admin'],
          permissions: ['*']
        }
      };
    }
  }
];