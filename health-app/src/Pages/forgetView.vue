<template>
    <div class="scroll-container">
        <div id="3_33" class="frame-3_33">
            <p id="3_59" class="paragraph-3_59">{{ "新密码：" }}</p>
            <p id="3_60" class="paragraph-3_60">{{ "再次输入新密码：" }}</p>
            <p id="3_36" class="paragraph-3_36">{{ "忘记密码" }}</p>
            <div id="3_65" class="vector-3_65"></div>
            <button id="5_479" class="frame-5_479" @click="handleReset">
                {{ "确定重置" }}
            </button>
            <div id="5_498" class="frame-5_498">
                <input 
                    type="password" 
                    class="input-field"
                    placeholder="请输入密码"
                    v-model="formData.newPassword"
                />
            </div>
            <div id="5_508" class="frame-5_508">
                <input 
                    type="password" 
                    class="input-field"
                    placeholder="请输入密码"
                    v-model="formData.confirmPassword"
                />
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

const formData = ref({
    newPassword: '',
    confirmPassword: ''
})

const handleReset = () => {
    if (!formData.value.newPassword || !formData.value.confirmPassword) {
        ElMessage.warning('请输入密码！')
        return
    }
    if (formData.value.newPassword !== formData.value.confirmPassword) {
        ElMessage.warning('两次输入的密码不一致！')
        return
    }
    ElMessage.success('密码重置成功！')
    router.push('/login')
}
</script>

<style scoped>
.scroll-container {
    height: 100%;
    width: 100%;
    overflow: auto;
}

.frame-3_33 {
    width: 430px;
    height: 932px;
    overflow: hidden;
    position: relative;
    flex-shrink: 0;
    background-color: rgba(255, 247, 242, 1);
    margin: 0 auto;
}

.paragraph-3_59 {
    font-size: 22px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 1);
    width: 100px;
    height: 41px;
    position: absolute;
    left: 27.6666259765625px;
    top: 380.66668701171875px;
    margin: 0;
}

.paragraph-3_60 {
    font-size: 22px;
    font-family: "Source Han Serif CN-Regular", serif;
    font-weight: 400;
    color: rgba(0, 0, 0, 1);
    width: 187px;
    height: 41px;
    position: absolute;
    left: 27.6666259765625px;
    top: 490.66668701171875px;
    margin: 0;
}

.paragraph-3_36 {
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

.frame-5_479 {
    width: 375px;
    height: 78px;
    overflow: hidden;
    position: absolute;
    left: 27.6666259765625px;
    top: 632.6666870117188px;
    border-radius: 30px;
    background-color: rgba(92, 147, 139, 1);
    border: none;
    font-size: 28px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    color: rgba(255, 255, 255, 1);
    cursor: pointer;
    transition: opacity 0.3s;
}

.frame-5_479:hover {
    opacity: 0.9;
}

.frame-5_479:active {
    opacity: 0.8;
}

.frame-5_498 {
    width: 375px;
    height: 78px;
    overflow: hidden;
    position: absolute;
    left: 27.6666259765625px;
    top: 412.66668701171875px;
    border-radius: 10px;
    background-color: rgba(239, 229, 225, 1);
    display: flex;
    align-items: center;
    padding: 0 11px;
    box-sizing: border-box;
}

.frame-5_508 {
    width: 375px;
    height: 78px;
    overflow: hidden;
    position: absolute;
    left: 27.6666259765625px;
    top: 522.6666870117188px;
    border-radius: 10px;
    background-color: rgba(239, 229, 225, 1);
    display: flex;
    align-items: center;
    padding: 0 10px;
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
</style>
