<template>
    <div class="scroll-container">
        <div id="2_1" class="frame-2_1">
            <div id="2_359" class="symbol-2_359">
                <div id="2_347" class="rectangle-2_347">
                    <input 
                        type="text" 
                        class="input-field"
                        placeholder="请输入用户名"
                        v-model="loginForm.username"
                    />
                </div>
                <div id="2_349" class="rectangle-2_349">
                    <input 
                        type="password" 
                        class="input-field"
                        placeholder="请输入您的密码"
                        v-model="loginForm.password"
                    />
                </div>
                <p id="2_355" class="paragraph-2_355">{{ "用户名：" }}</p>
                <p id="2_356" class="paragraph-2_356">{{ "密码：" }}</p>
                <div id="2_357" class="text-2_357" @click.stop="handleForgotPassword">
                    <p id="2_357_0" class="paragraph-2_357_0">
                        <span id="2_357_0_1" class="span-2_357_0_1">{{ "忘记密码？" }}</span>
                        <span id="2_357_0_2" class="span-2_357_0_2">{{ "找回" }}</span>
                    </p>
                </div>
                <button id="5_685" class="frame-5_685" @click="handleLogin">
                    {{ "登录/注册" }}
                </button>
            </div>
            <p id="3_18" class="paragraph-3_18">{{ "欢迎登录" }}</p>
            <div id="3_65" class="vector-3_65"></div>
            <div id="5_652" class="frame-5_652">
                <div id="5_657" class="vector-5_657"></div>
                <div id="5_656" class="text-5_656">
                    <p id="5_656_0" class="paragraph-5_656_0">
                        <span id="5_656_0_1" class="span-5_656_0_1">{{ "    我已阅读关于此app的" }}</span>
                        <span id="5_656_0_2" class="span-5_656_0_2" @click="handleUserAgreement">{{ "用户协议" }}</span>
                        <span id="5_656_0_3" class="span-5_656_0_3">{{ "与" }}</span>
                        <span id="5_656_0_4" class="span-5_656_0_4" @click="handlePrivacyPolicy">{{ "隐私政策" }}</span>
                        <span id="5_656_0_5" class="span-5_656_0_5">{{ "，并同意协议内容" }}</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'
import { getUserInfo } from '../api/userApi'
import request from '@/utils/request'
import { ElMessage } from 'element-plus'

const router = useRouter()
const userStore = useUserStore()

const loginForm = ref({
  username: '',
  password: ''
})

const handleLogin = async () => {
  try {
    if (!loginForm.value.username || !loginForm.value.password) {
      ElMessage.warning('用户名或密码不能为空！')
      return
    }
    const res = await request.post('/api/user/login', {
      username: loginForm.value.username,
      password: loginForm.value.password
    })
    console.log('登录接口返回结果：', res)
    
    if (res.code === 200) {
      userStore.setToken(res.data.token)
      router.push('/select')
    }
  } catch (error) {
    console.error('登录失败：', error)
  }
}

const handleForgotPassword = () => {
	if(loginForm.value.username)
	{
		router.push('/forget')
	}
	else{
		ElMessage.warning('请输入用户名')
	}
}

const handleUserAgreement = () => {
  ElMessage.info('用户协议页面开发中')
}

const handlePrivacyPolicy = () => {
  ElMessage.info('隐私政策页面开发中')
}
</script>

<style scoped>
.scroll-container {
    height: 100%;
    width: 100%;
    overflow: auto;
}

.frame-2_1 {
    width: 430px;
    height: 932px;
    overflow: hidden;
    position: relative;
    flex-shrink: 0;
    background-color: rgba(255, 247, 242, 1);
    margin: 0 auto;
}

.symbol-2_359 {
    width: 329px;
    height: 330px;
    position: absolute;
    left: 50.6666259765625px;
    top: 380.66668701171875px;
    background-color: transparent;
}

.rectangle-2_347 {
    width: 375px;
    height: 78px;
    position: absolute;
    left: -23px;
    top: 32px;
    border-radius: 10px;
    background-color: rgba(239, 229, 225, 1);
    display: flex;
    align-items: center;
    padding: 0 20px;
    box-sizing: border-box;
}

.rectangle-2_349 {
    width: 375px;
    height: 78px;
    position: absolute;
    left: -23px;
    top: 142px;
    border-radius: 10px;
    background-color: rgba(239, 229, 225, 1);
    display: flex;
    align-items: center;
    padding: 0 20px;
    box-sizing: border-box;
}

.input-field {
    width: 100%;
    height: 100%;
    border: none;
    background: transparent;
    font-size: 25px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.8);
    outline: none;
}

.input-field::placeholder {
    color: rgba(0, 0, 0, 0.5);
}

.paragraph-2_355 {
    font-size: 22px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 1);
    width: 100px;
    height: 41px;
    position: absolute;
    left: -23px;
    top: 0px;
    margin: 0;
}

.paragraph-2_356 {
    font-size: 22px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 1);
    width: 100px;
    height: 41px;
    position: absolute;
    left: -23px;
    top: 110px;
    margin: 0;
}

.text-2_357 {
    font-size: 20px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: Regular;
    color: rgba(0, 0, 0, 1);
    width: 178px;
    height: 41px;
    position: absolute;
    left: 94px;
    top: 220px;
    cursor: pointer;
}

.paragraph-2_357_0 {
    line-height: 28.740001678466797px;
    position: relative;
    flex-shrink: 0;
    margin: 0;
}

.span-2_357_0_1 {
    font-size: 20px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 1);
    position: relative;
    flex-shrink: 0;
}

.span-2_357_0_2 {
    font-size: 20px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(203, 100, 100, 1);
    position: relative;
    flex-shrink: 0;
}

.frame-5_685 {
    width: 375.0000305175781px;
    height: 78.00000762939453px;
    overflow: hidden;
    position: absolute;
    left: -22.6666259765625px;
    top: 252.33331298828125px;
    border-radius: 20px;
    background-color: rgba(92, 147, 139, 1);
    border: none;
    font-size: 28px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    color: rgba(255, 255, 255, 1);
    cursor: pointer;
    transition: opacity 0.3s;
}

.frame-5_685:hover {
    opacity: 0.9;
}

.frame-5_685:active {
    opacity: 0.8;
}

.paragraph-3_18 {
    font-size: 50px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    text-align: center;
    color: rgba(63, 96, 91, 1);
    width: 221px;
    height: 111px;
    position: absolute;
    left: 104.6666259765625px;
    top: 269.66668701171875px;
    margin: 0;
}

.vector-3_65 {
    width: 505.39715576171875px;
    height: 203.74530029296875px;
    background-image: url("/src/assets/组件 1.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: -53.030029296875px;
    top: -84.05889892578125px;
}

.frame-5_652 {
    width: 467px;
    height: 36px;
    overflow: hidden;
    position: absolute;
    left: 0px;
    top: 734px;
    background-color: rgba(255, 255, 255, 0);
}

.vector-5_657 {
    width: 16px;
    height: 16px;
    background-image: url("");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: 16px;
    top: 10px;
}

.text-5_656 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: Medium;
    color: rgba(115, 115, 115, 1);
    width: auto;
    height: auto;
    position: absolute;
    left: 44px;
    top: 6px;
    white-space: nowrap;
    flex-grow: 0;
}

.paragraph-5_656_0 {
    line-height: 18.680999755859375px;
    position: relative;
    flex-shrink: 0;
    margin: 0;
}

.span-5_656_0_1 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: 500;
    color: rgba(115, 115, 115, 1);
    white-space: pre-wrap;
    position: relative;
    flex-shrink: 0;
}

.span-5_656_0_2 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: 500;
    color: rgba(203, 100, 100, 1);
    position: relative;
    flex-shrink: 0;
    cursor: pointer;
}

.span-5_656_0_3 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: 500;
    color: rgba(115, 115, 115, 1);
    position: relative;
    flex-shrink: 0;
}

.span-5_656_0_4 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: 500;
    color: rgba(203, 100, 100, 1);
    position: relative;
    flex-shrink: 0;
    cursor: pointer;
}

.span-5_656_0_5 {
    font-size: 13px;
    font-family: "Source Han Serif CN-Medium", serif;
    font-weight: 500;
    color: rgba(115, 115, 115, 1);
    position: relative;
    flex-shrink: 0;
}
</style>
