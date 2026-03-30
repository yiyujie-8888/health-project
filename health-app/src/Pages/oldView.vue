<template>
    <div class="scroll-view">
        <div id="3_320" class="frame-3_320">
            <!-- 顶部导航栏背景 -->
            <div id="5_70" class="rectangle-5_70"></div>
            
            <!-- 绑定码按钮 -->
		<div id="16_7" class="vector-16_7" @click.stop="click_5_71"></div>
            <p id="16_8" class="paragraph-16_8" @click.stop="click_5_71">{{ "绑定码" }}</p>
            
            <!-- 用户头像 -->
            <div id="5_71" class="vector-5_71"></div>
            
            <!-- 用户名 -->
            <p id="5_92" class="paragraph-5_92">{{ "张三" }}</p>
            
            <!-- 轮播图区域 -->
            <div id="5_120" class="frame-5_120">
                <div class="carousel-container" ref="carouselContainer">
                    <div class="carousel-slides" ref="carouselSlides" :style="{ transform: `translateX(-${currentSlide * 100}%)` }">
                        <div class="carousel-slide">
					<img src="" alt="轮播图1" class="carousel-image">
				</div>
				<div class="carousel-slide">
					<img src="" alt="轮播图2" class="carousel-image">
				</div>
				<div class="carousel-slide">
					<img src="" alt="轮播图3" class="carousel-image">
				</div>
				<div class="carousel-slide">
					<img src="" alt="轮播图4" class="carousel-image">
				</div>
				<div class="carousel-slide">
					<img src="" alt="轮播图5" class="carousel-image">
				</div>
                    </div>
                </div>
                <div class="carousel-dots">
                    <div 
                        v-for="(dot, index) in slides" 
                        :key="index"
                        class="carousel-dot"
                        :class="{ active: index === currentSlide }"
                        @click="goToSlide(index)"
                    ></div>
                </div>
            </div>
            
            <!-- 便捷功能标题 -->
            <p id="5_96" class="paragraph-5_96">{{ "便捷功能" }}</p>
            
            <!-- 面部检测卡片 -->
            <div id="5_97" class="rectangle-5_97" @click.stop="click_5_97">
                <div id="35_39" class="vector-35_39"></div>
                <p id="6_23" class="paragraph-6_23">{{ "面部检测" }}</p>
            </div>
            
            <!-- AI问答卡片 -->
            <div id="5_99" class="rectangle-5_99" @click.stop="click_5_99">
                <div id="6_15" class="vector-6_15"></div>
                <div id="6_24" class="text-6_24">
                    <p id="6_24_0" class="paragraph-6_24_0">
                        <span id="6_24_0_1" class="span-6_24_0_1">{{ "AI" }}</span>
                    </p>
                    <p id="6_24_1" class="paragraph-6_24_1">
                        <span id="6_24_1_1" class="span-6_24_1_1">{{ "问答" }}</span>
                    </p>
                </div>
            </div>
            
            <!-- 心理测试卡片 -->
            <div id="5_100" class="rectangle-5_100" @click.stop="click_5_100">
                <div id="6_21" class="vector-6_21"></div>
                <p id="6_25" class="paragraph-6_25">{{ "心理测试" }}</p>
            </div>
            
            <!-- 箭头图标 -->
            <div id="35_2" class="vector-35_2"></div>
            <div id="35_5" class="vector-35_5"></div>
            
            <!-- 养老教程标题 -->
            <p id="5_101" class="paragraph-5_101">{{ "养老教程" }}</p>
            
            <!-- 养老教程卡片 -->
            <div id="5_102" class="rectangle-5_102"></div>
            <div id="5_103" class="rectangle-5_103"></div>
            
            <!-- 音乐推荐标题 -->
            <p id="5_104" class="paragraph-5_104">{{ "音乐推荐" }}</p>
            
            <!-- 音乐推荐卡片 -->
            <div id="5_105" class="rectangle-5_105"></div>
            <div id="5_106" class="rectangle-5_106"></div>
            <div id="5_107" class="rectangle-5_107"></div>
            
            <!-- 底部导航栏 -->
            <div id="5_376" class="frame-5_376">
                <div id="5_377" class="frame-5_377" @click.stop="click_5_377">
                    <p id="5_378" class="paragraph-5_378">{{ "主页" }}</p>
                    <div id="5_379" class="rectangle-5_379"></div>
                </div>
                <div id="5_380" class="frame-5_380" @click.stop="click_5_380">
                    <p id="5_382" class="paragraph-5_382">{{ "家庭" }}</p>
                </div>
                <div id="5_383" class="frame-5_383" @click.stop="click_5_383">
                    <p id="5_385" class="paragraph-5_385">{{ "我的" }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { useRouter } from "vue-router";
import { ref, onMounted, onUnmounted } from 'vue';
const router = useRouter();

// 轮播图相关
const currentSlide = ref(0);
const slides = ref([1, 2, 3, 4, 5]);
const carouselContainer = ref(null);
const carouselSlides = ref(null);
let carouselInterval = null;

// 轮播图方法
const goToSlide = (index) => {
    currentSlide.value = index;
};

const nextSlide = () => {
    currentSlide.value = (currentSlide.value + 1) % slides.value.length;
};

const startCarousel = () => {
    carouselInterval = setInterval(nextSlide, 3000); // 每3秒自动切换
};

const stopCarousel = () => {
    if (carouselInterval) {
        clearInterval(carouselInterval);
    }
};

// 生命周期
onMounted(() => {
    startCarousel();
});

onUnmounted(() => {
    stopCarousel();
});

// 绑定码点击
const click_5_71 = () => {
    console.log("绑定码 clicked");
};

// 面部检测点击
const click_5_97 = () => {
    console.log("面部检测 clicked");
};

// AI问答点击
const click_5_99 = () => {
    console.log("AI问答 clicked");
};

// 心理测试点击
const click_5_100 = () => {
    console.log("心理测试 clicked");
};

// 主页导航
const click_5_377 = () => {
    console.log("主页 clicked");
};

// 家庭导航
const click_5_380 = () => {
    console.log("家庭 clicked");
};

// 我的导航
const click_5_383 = () => {
    console.log("我的 clicked");
};
</script>

<style scoped>
.scroll-view {
    height: 100%;
    width: 100%;
    overflow: auto;
	-webkit-overflow-scrolling: touch;
	-ms-overflow-style: none;
	scrollbar-width: none;
}

.scroll-view::-webkit-scrollbar {
    display: none;
}

.frame-3_320 {
    width: 430px;
    min-height: 1300px;
    height: auto;
    overflow: visible;
    position: relative;
    flex-shrink: 0;
    background-color: rgba(255, 247, 242, 1);   
	margin: 0 auto;
	padding-bottom: 100px;
}

/* 顶部导航栏 */
.rectangle-5_70 {
    width: 430px;
    height: 105px;
    position: fixed;
    left: 50%;
    top: 0px;
    transform: translateX(-50%);
    border-radius: 0 0 10px 10px;
    box-shadow: 0px 4px 4px 0px rgba(92, 147, 139, 0.48);
    background-color: rgba(92, 147, 139, 1);
    z-index: 1000;
}

/* 绑定码 */
.vector-16_7{
	height: 59px;
	width: 100px;
	position: fixed;
	left: calc(50% + 90px);
	top: 10px;
	cursor: pointer;
	background-image: url("/src/assets/绑定码.png");
	margin: 0;
    z-index: 1001;
}
.paragraph-16_8 {
    font-size: 25px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(255, 255, 255, 1);
    position: fixed;
    left: calc(50% + 107px);
    top: 60px;
    cursor: pointer;
    margin: 0;
    z-index: 1001;
}

/* 用户头像 */
.vector-5_71 {
    width: 65px;
    height: 65px;
    background-color: rgba(255, 255, 255, 1);
    border-radius: 50%;
    position: fixed;
    left: calc(50% - 200px);
    top: 34px;
	background-image: url("/src/assets/老人默认图.png");
	background-size: cover; /* 关键：使图片覆盖整个容器 */
	background-position: center; /* 关键：使图片居中显示 */
	background-repeat: no-repeat; /* 防止图片重复 */
    z-index: 1001;
}

/* 用户名 */
.paragraph-5_92 {
    font-size: 38px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(255, 255, 255, 1);
    position: fixed;
    left: calc(50% - 125px);
    top: 45px;
    margin: 0;
    z-index: 1001;
}

/* 轮播图 */
.frame-5_120 {
    width: 385px;
    height: 205px;
    overflow: hidden;
    position: absolute;
    left: 22px;
    top: 120px;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    background-color: rgba(197, 197, 197, 1);
}

.carousel-container {
    width: 100%;
    height: 100%;
    overflow: hidden;
    position: relative;
}

.carousel-slides {
    display: flex;
    width: 100%;
    height: 100%;
    transition: transform 0.5s ease-in-out;
}

.carousel-slide {
    flex: 0 0 100%;
    height: 100%;
    background: linear-gradient(135deg, rgba(92, 147, 139, 0.8) 0%, rgba(19, 82, 73, 0.9) 100%);
    display: flex;
    align-items: center;
    justify-content: center;
}

.carousel-dots {
    position: absolute;
    bottom: 15px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    gap: 8px;
    z-index: 10;
}

.carousel-dot {
    width: 11px;
    height: 11px;
    border-radius: 50%;
    background-color: rgba(255, 255, 255, 0.5);
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.carousel-dot.active {
    background-color: rgba(255, 255, 255, 1);
}

/* 便捷功能标题 */
.paragraph-5_96 {
    font-size: 38px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(19, 82, 73, 1);
    position: absolute;
    left: 19px;
    top: 340px;
    margin: 0;
}

/* 功能卡片 */
.rectangle-5_97,
.rectangle-5_99,
.rectangle-5_100 {
    width: 120px;
    height: 229px;
    position: absolute;
    top: 399px;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    background-color: rgba(255, 255, 255, 1);
    cursor: pointer;
    transition: transform 0.3s, box-shadow 0.3s;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    padding-top: 20px;
}

.rectangle-5_97:hover,
.rectangle-5_99:hover,
.rectangle-5_100:hover {
    transform: translateY(-5px);
    box-shadow: 0px 8px 12px 0px rgba(0, 0, 0, 0.3);
}

.rectangle-5_97 {
    left: 19px;
}

.rectangle-5_99 {
    left: 155px;
}

.rectangle-5_100 {
    left: 291px;
}

/* 卡片图标 */
.vector-35_39 {
    width: 100px;
    height: 100px;
    background-color: rgba(255,255,255,1);
    border-radius: 10px;
    margin-bottom: 10px;
	background-image: url("/src/assets/面部检测.png"); 
	background-size: cover; /* 关键：使图片覆盖整个容器 */
	background-position: center; /* 关键：使图片居中显示 */
	background-repeat: no-repeat; /* 防止图片重复 */
}

.vector-6_15 {
    width: 100px;
    height: 100px;
    background-color: rgba(255,255,255,1);
    border-radius: 10px;
    margin-bottom: 10px;
	background-image: url("/src/assets/AI问答.png"); 
	background-size: cover; /* 关键：使图片覆盖整个容器 */
	background-position: center; /* 关键：使图片居中显示 */
	background-repeat: no-repeat; /* 防止图片重复 */
}

.vector-6_21 {
    width: 100px;
    height: 100px;
    background-color: rgba(255,255,255,1);
    border-radius: 10px;
    margin-bottom: 10px;
	background-image: url("/src/assets/心理测试.png"); 
	background-size: cover; /* 关键：使图片覆盖整个容器 */
	background-position: center; /* 关键：使图片居中显示 */
	background-repeat: no-repeat; /* 防止图片重复 */
}

/* 卡片文字 */
.paragraph-6_23 {
    font-size: 43px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    line-height: 55px;
    color: rgba(19, 82, 73, 1);
    text-align: center;
    margin: 0;
}

.text-6_24 {
    font-size: 43px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    text-align: center;
    line-height: 55px;
    color: rgba(19, 82, 73, 1);
}

.paragraph-6_24_0,
.paragraph-6_24_1 {
    margin: 0;
}

.span-6_24_0_1,
.span-6_24_1_1 {
    font-size: 43px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(19, 82, 73, 1);
}

.paragraph-6_25 {
    font-size: 43px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    line-height: 55px;
    color: rgba(19, 82, 73, 1);
    text-align: center;
    margin: 0;
}

/* 箭头图标 */
.vector-35_2,
.vector-35_5 {
    width: 51px;
    height: 47px;
    position: absolute;
    background-image: url("/src/assets/右箭头.png"); 
}

.vector-35_2 {
    left: 363px;
    top: 343px;
}

.vector-35_5 {
    left: 361px;
    top: 648px;
}

/* 养老教程标题 */
.paragraph-5_101 {
    font-size: 38px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(19, 82, 73, 1);
    position: absolute;
    left: 19px;
    top: 665px;
    margin: 0;
}

/* 养老教程卡片 */
.rectangle-5_102{
	width: 184px;
	    height: 300px;
	    position: absolute;
	    top: 715px;
	    border-radius: 20px;
	    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
	    background: linear-gradient(135deg, rgba(92, 147, 139, 0.6) 0%, rgba(19, 82, 73, 0.8) 100%);
	background-image: url("/src/assets/八段锦new.png");
	background-size: contain;
	background-position: center;
	background-repeat: no-repeat;
}
.rectangle-5_103 {
    width: 184px;
    height: 300px;
    position: absolute;
    top: 715px;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    background: linear-gradient(135deg, rgba(92, 147, 139, 0.6) 0%, rgba(19, 82, 73, 0.8) 100%);
	background-image: url("/src/assets/太极拳new.png");
	background-size: contain;
	background-position: center;
	background-repeat: no-repeat;
}

.rectangle-5_102 {
    left: 19px;
}

.rectangle-5_103 {
    left: 224px;
}

/* 音乐推荐标题 */
.paragraph-5_104 {
    font-size: 38px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    color: rgba(19, 82, 73, 1);
    position: absolute;
    left: 19px;
    top: 1030px;
    margin: 0;
    z-index: 100;
}

/* 音乐推荐卡片 */
.rectangle-5_105,
.rectangle-5_106,
.rectangle-5_107 {
    width: 120px;
    height: 150px;
    position: absolute;
    top: 1080px;
    border-radius: 20px;
    box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
    background: linear-gradient(135deg, rgba(92, 147, 139, 0.6) 0%, rgba(19, 82, 73, 0.8) 100%);
    cursor: pointer;
    transition: transform 0.3s, box-shadow 0.3s;
    z-index: 100;
    opacity: 1;
    visibility: visible;
}

.rectangle-5_105:hover,
.rectangle-5_106:hover,
.rectangle-5_107:hover {
    transform: translateY(-5px);
    box-shadow: 0px 8px 12px 0px rgba(0, 0, 0, 0.3);
}

.rectangle-5_105 {
    left: 19px;
	background-image: url("/src/assets/音乐1.png");
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

.rectangle-5_106 {
    left: 155px;
	background-image: url("/src/assets/音乐2.png");
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

.rectangle-5_107 {
    left: 291px;
	background-image: url("/src/assets/音乐3.png");
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

/* 底部导航栏 */
.frame-5_376 {
    width: 430px;
    height: 90px;
    position: fixed;
    left: 50%;
    bottom: 0px;
    transform: translateX(-50%);
    display: flex;
    flex-direction: row;
    gap: 46px;
    justify-content: center;
    align-items: center;
    padding: 16px 24px 4px 24px;
    border-radius: 10px 10px 0 0;
    background-color: rgba(92, 147, 139, 1);
	z-index: 1000;
}

.frame-5_377,
.frame-5_380,
.frame-5_383 {
    width: 104px;
    height: 40px;
    position: relative;
    flex-shrink: 0;
    cursor: pointer;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.paragraph-5_378,
.paragraph-5_382,
.paragraph-5_385 {
    font-size: 38px;
    font-family: "Source Han Serif CN", serif;
    font-weight: 700;
    letter-spacing: -1px;
    line-height: 22px;
    color: rgba(255, 255, 255, 1);
    margin: 0;
}

.paragraph-5_378 {
    color: rgba(255, 255, 255, 0.9);
}

.rectangle-5_379 {
    width: 60px;
    height: 2px;
    background-color: rgba(180, 208, 255, 1);
    margin-top: 14px;
}
</style>
