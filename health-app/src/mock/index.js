import Mock from 'mockjs';
// mock/index.js
// 拦截 POST /api/user/login 请求
Mock.mock('/api/user/login', 'post', (req) => {
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
});
// 2. 新增：拦截用户信息接口（GET 请求）
Mock.mock('/api/user/info', 'get', (req) => {
  // 模拟：从请求头中获取 token（真实场景中前端会把 token 放在请求头）
  const token = req.headers.authorization?.replace('Bearer ', '') || '';
  
  // 简单校验 token 是否有效（模拟真实后端的 token 校验逻辑）
  if (token && token.startsWith('mock-token-')) {
    // Mock.js 随机生成用户信息（也可以返回固定数据）
    return Mock.mock({
      code: 200,
      msg: '获取用户信息成功',
      data: {
        id: '@id', // 随机生成用户 ID
        username: 'admin', // 固定用户名（也可以用 @name 随机生成）
        nickname: '@cname', // 随机生成中文昵称
        avatar: '@image(64x64, #42b983, #fff, admin)', // 随机生成头像图片
        roles: ['admin'], // 用户角色（用于权限控制）
        permissions: ['user:list', 'user:add', 'user:edit'], // 权限列表
        email: '@email' // 随机生成邮箱
      }
    });
  } else {
    // token 无效/不存在时返回错误
    return {
      code: 401,
      msg: 'token 无效或未登录，请先登录',
      data: null
    };
  }
});