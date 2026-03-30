<template>
    <div class="scroll-container">
        <div id="24_4" class="frame-24_4">
            <div id="24_5" class="vector-24_5"></div>
            <p id="25_1" class="paragraph-25_1">{{ "智护杏林" }}</p>
            <p id="25_2" class="paragraph-25_2">{{ "健康同行" }}</p>
        </div>
    </div>
</template>
<script lang="ts" setup>
	import { onMounted } from 'vue'
	import { useRouter } from 'vue-router'
	
	const router = useRouter()
	
	onMounted(() => {
	  setTimeout(() => {
	    router.push('/login')
	  }, 4000)
	})
</script>
<style>
.scroll-container {
    height: 100%;
    width: 100%;
    overflow: auto;
}
.frame-24_4 {
    width: 430px;
    height: 932px;
    overflow: hidden;
    position: relative;
    flex-shrink: 0;
    background-color: rgba(255, 247, 242, 1);
}
.vector-24_5 {
    width: 505.3971710205078px;
    height: 203.74530029296875px;
    background-image: url("/src/assets/组件 1.png");
    background-size: 100% 100%;
    background-repeat: no-repeat;
    position: absolute;
    left: -53.030029296875px;
    top: -84.05889892578125px;
}
.paragraph-25_1 {
    font-size: 48px;
    font-family: "SimSun", "宋体", serif;  /* 改为宋体 */
    font-weight: 400;
    text-align: center;
    line-height: 74px;
    color: rgba(92, 147, 139, 1);
    width: 61px;
    height: 300px;
    position: absolute;
    left: 138px;
    top: 166px;
}
.paragraph-25_2 {
    font-size: 48px;
    font-family: "SimSun", "宋体", serif;  /* 改为宋体 */
    font-weight: 400;
    text-align: center;
    line-height: 74px;
    color: rgba(92, 147, 139, 1);
    width: 61px;
    height: 300px;
    position: absolute;
    left: 231px;
    top: 385px;
}
</style>