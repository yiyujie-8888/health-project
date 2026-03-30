<template>
    <div class="scroll-container">
        <div id="3_62" class="frame-3_62">
            <div id="3_66" class="vector-3_66"></div>
            <div id="5_407" class="frame-5_407" @click.stop="click_5_407">
                <div id="5_410" class="frame-5_410"></div>
            </div>
            <div id="3_305" class="vector-wrapper-3_305">
                <div id="3_305" class="vector-3_305"></div>
            </div>
            <div id="3_138" class="rectangle-3_138"></div>
            <p id="3_139" class="paragraph-3_139">{{ "我是老人" }}</p>
            <div id="3_118" class="vector-3_118"></div>
            <div id="5_443" class="frame-5_443" @click.stop="click_5_443">
                <div id="5_444" class="frame-5_444"></div>
            </div>
            <div id="5_445" class="vector-wrapper-5_445">
                <div id="5_445" class="vector-5_445"></div>
            </div>
            <div id="5_450" class="rectangle-5_450"></div>
            <p id="5_451" class="paragraph-5_451">{{ "我是青年" }}</p>
            <div id="5_413" class="frame-5_413" @click.stop="click_5_413">
                <div id="5_414" class="frame-5_414"></div>
            </div>
            <div id="5_415" class="vector-wrapper-5_415">
                <div id="5_415" class="vector-5_415"></div>
            </div>
            <div id="5_420" class="rectangle-5_420"></div>
            <p id="5_421" class="paragraph-5_421">{{ "我是子女" }}</p>
            <div id="3_228" class="vector-3_228"></div>
            <div id="3_278" class="frame-3_278">
                <div id="3_279" class="vector-3_279"></div>
            </div>
            <p id="9_1" class="paragraph-9_1">{{ "请选择您的身份" }}</p>
        </div>
    </div>
</template>
<script lang="ts" setup>
import { useRouter } from "vue-router";
import { useUserStore } from '../stores/user'
const router = useRouter();
const userStore = useUserStore();

const click_5_407 = () => {
    // 老人身份
    userStore.setPlatformType('old');
    router.push('/old')
};

const click_5_443 = () => {
    // 青年身份
    userStore.setPlatformType('young');
    router.push({
        path: '/',
        query: {
            from: "5:443",
            et: "c"
        }
    });
};

const click_5_413 = () => {
    // 子女身份
    userStore.setPlatformType('son');
    router.push({
        path: '/',
        query: {
            from: "5:413",
            et: "c"
        }
    });
};
</script>
<style>
.scroll-container {
    height: 100%;
    width: 100%;
    overflow: auto;
}
.frame-3_62 {
    width: 430px;
    height: 932px;
    overflow: hidden;
    position: relative;
    flex-shrink: 0;
    background-color: rgba(255, 247, 242, 1);
}
.vector-3_66 {
    width: 505.3970947265625px;
    height: 203.74530029296875px;
    background-image: url("/src/assets/组件 1.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: -53.3333740234375px;
    top: -84.33331298828125px;
}
.frame-5_407 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 32.6666259765625px;
    top: 154.66668701171875px;
    border-radius: 20px 20px 20px 20px;
    box-shadow: 0px 4px 5px 4px rgba(92, 147, 139, 0.5);
    background-color: rgba(198, 224, 207, 1);
}
.frame-5_410 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 0px;
    top: 0px;
    border-radius: 4px 4px 4px 4px;
    background-color: rgba(198, 224, 207, 1);
}
.vector-3_305 {
    position: absolute;
    inset: 0% 0% 0% 0%;
    background-image: url("/src/assets/箭头.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
.vector-wrapper-3_305 {
    position: absolute;
    left: 354.6666259765625px;
    top: 218.66668701171875px;
    width: 60px;
    height: 60px;
}
.rectangle-3_138 {
    width: 145px;
    height: 145px;
    position: absolute;
    left: 53.6666259765625px;
    top: 173.66668701171875px;
    border-radius: 10px 10px 10px 10px;
    background-color: rgba(255, 255, 255, 1);
}
.paragraph-3_139 {
    font-size: 64px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    line-height: 85px;
    color: rgba(43, 108, 99, 1);
    width: 170px;
    height: 171px;
    position: absolute;
    left: 226.6666259765625px;
    top: 95px;
}
.vector-3_118 {
    width: 120px;
    height: 131.00001525878906px;
    background-image: url("/src/assets/老人默认图.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: 65.6666259765625px;
    top: 180.66668701171875px;
}
.frame-5_443 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 32.6666259765625px;
    top: 600.6666870117188px;
    border-radius: 20px 20px 20px 20px;
    box-shadow: 0px 4px 5px 4px rgba(92, 147, 139, 0.5);
    background-color: rgba(198, 224, 207, 1);
}
.frame-5_444 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 0px;
    top: 0px;
    border-radius: 4px 4px 4px 4px;
    background-color: rgba(198, 224, 207, 1);
}
.vector-5_445 {
    position: absolute;
    inset: 0% 0% 0% 0%;
    background-image: url("/src/assets/箭头.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
.vector-wrapper-5_445 {
    position: absolute;
    left: 354.6666259765625px;
    top: 664.6666870117188px;
    width: 60px;
    height: 60px;
}
.rectangle-5_450 {
    width: 145px;
    height: 145px;
    position: absolute;
    left: 53.6666259765625px;
    top: 619.6666870117188px;
    border-radius: 10px 10px 10px 10px;
    background-color: rgba(255, 255, 255, 1);
}
.paragraph-5_451 {
    font-size: 64px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    line-height: 85px;
    color: rgba(43, 108, 99, 1);
    width: 170px;
    height: 171px;
    position: absolute;
    left: 226.6666259765625px;
    top: 540px;
}
.frame-5_413 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 35.6666259765625px;
    top: 377.66668701171875px;
    border-radius: 20px 20px 20px 20px;
    box-shadow: 0px 4px 5px 4px rgba(92, 147, 139, 0.5);
    background-color: rgba(198, 224, 207, 1);
}
.frame-5_414 {
    width: 364px;
    height: 187px;
    overflow: hidden;
    position: absolute;
    left: 0px;
    top: 0px;
    border-radius: 4px 4px 4px 4px;
    background-color: rgba(198, 224, 207, 1);
}
.vector-5_415 {
    position: absolute;
    inset: 0% 0% 0% 0%;
    background-image: url("/src/assets/箭头.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
}
.vector-wrapper-5_415 {
    position: absolute;
    left: 357.6666259765625px;
    top: 441.66668701171875px;
    width: 60px;
    height: 60px;
}
.rectangle-5_420 {
    width: 145px;
    height: 145px;
    position: absolute;
    left: 56.6666259765625px;
    top: 396.66668701171875px;
    border-radius: 10px 10px 10px 10px;
    background-color: rgba(255, 255, 255, 1);
}
.paragraph-5_421 {
    font-size: 64px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    line-height: 85px;
    color: rgba(43, 108, 99, 1);
    width: 170px;
    height: 171px;
    position: absolute;
    left: 229.6666259765625px;
    top: 320px;
}
.vector-3_228 {
    width: 119.5850830078125px;
    height: 134.94841766357422px;
    background-image: url("/src/assets/子女默认图.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: 65.6666259765625px;
    top: 403.66668701171875px;
}
.frame-3_278 {
    width: 166px;
    height: 155px;
    overflow: hidden;
    position: absolute;
    left: 41.6666259765625px;
    top: 618.6666870117188px;
}
.vector-3_279 {
    width: 145.5755615234375px;
    height: 145.44081115722656px;
    background-image: url("/src/assets/青年默认图.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: 12.82941246032715px;
    top: 2.941176414489746px;
}
.paragraph-9_1 {
    font-size: 38px;
    font-family: "Source Han Serif CN-Bold", serif;
    font-weight: 700;
    color: rgba(19, 82, 73, 1);
    width: 295px;
    height: 48px;
    position: absolute;
    left: 79.6666259765625px;
    top: 70px;
}
</style>
