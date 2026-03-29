"""
舌诊图片预处理工具
任务3：图片缩放、归一化、灰度化、数据增强
"""

import cv2
import numpy as np
import random


class TonguePreprocessor:
    def __init__(self, target_size=(224, 224)):
        self.target_size = target_size

    def load_image(self, img_path):
        img = cv2.imread(img_path)
        if img is None:
            raise ValueError(f"无法加载图片: {img_path}")
        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        return img

    def to_gray(self, img):
        return cv2.cvtColor(img, cv2.COLOR_RGB2GRAY)

    def resize(self, img):
        return cv2.resize(img, self.target_size)

    def normalize(self, img):
        return img.astype(np.float32) / 255.0

    def data_augmentation(self, img):
        """
        数据增强：随机翻转、旋转、亮度调整
        """
        # 随机水平翻转
        if random.random() > 0.5:
            img = cv2.flip(img, 1)
        
        # 随机旋转 (-15° 到 15°)
        angle = random.uniform(-15, 15)
        h, w = img.shape[:2]
        M = cv2.getRotationMatrix2D((w//2, h//2), angle, 1)
        img = cv2.warpAffine(img, M, (w, h))
        
        # 随机亮度调整 (0.8 到 1.2)
        brightness = random.uniform(0.8, 1.2)
        img = np.clip(img * brightness, 0, 255).astype(np.uint8)
        
        return img

    def get_info(self, img_path):
        img = self.load_image(img_path)
        return {
            'shape': img.shape,
            'dtype': img.dtype,
            'min_value': img.min(),
            'max_value': img.max(),
            'mean_value': img.mean()
        }


if __name__ == "__main__":
    import os
    
    print("=" * 50)
    print("舌诊图片预处理工具（任务3核心功能测试）")
    print("=" * 50)
    
    preprocessor = TonguePreprocessor(target_size=(224, 224))
    
    # 查找测试图片
    test_images = []
    data_path = "../data"
    
    if os.path.exists(data_path):
        for file in os.listdir(data_path):
            if file.endswith(('.jpg', '.png', '.jpeg')):
                test_images.append(os.path.join(data_path, file))
    
    # 如果没有图片，生成测试图
    if not test_images:
        print("未找到测试图片，生成测试图...")
        test_img = np.random.randint(0, 255, (300, 300, 3), dtype=np.uint8)
        test_path = "test_tongue.jpg"
        cv2.imwrite(test_path, cv2.cvtColor(test_img, cv2.COLOR_RGB2BGR))
        test_images = [test_path]
        print(f"已生成测试图片: {test_path}")
    
    test_img_path = test_images[0]
    print(f"\n测试图片: {test_img_path}")
    
    # 1. 加载图片
    img = preprocessor.load_image(test_img_path)
    print(f"1. 加载成功 | 形状: {img.shape}")
    
    # 2. 灰度化
    gray_img = preprocessor.to_gray(img)
    print(f"2. 灰度化成功 | 形状: {gray_img.shape}")
    
    # 3. 缩放
    resized_img = preprocessor.resize(img)
    print(f"3. 缩放成功 | 形状: {resized_img.shape}")
    
    # 4. 归一化
    norm_img = preprocessor.normalize(resized_img)
    print(f"4. 归一化成功 | 数值范围: [{norm_img.min():.2f}, {norm_img.max():.2f}]")
    
    # 5. 数据增强（测试三次，展示不同效果）
    print("\n5. 数据增强测试（3次随机增强）:")
    for i in range(3):
        augmented = preprocessor.data_augmentation(resized_img.copy())
        print(f"   第{i+1}次增强 | 形状: {augmented.shape}, 数值范围: [{augmented.min()}, {augmented.max()}]")
    
    print("\n任务3核心功能验证完成！")