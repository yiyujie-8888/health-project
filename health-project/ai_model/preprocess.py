"""
舌诊图像预处理工具类
支持缩放、归一化、灰度化（可选）、数据增强
"""

import cv2
import numpy as np
from PIL import Image
import torch
from torchvision import transforms
from .config import IMG_SIZE, IMAGENET_MEAN, IMAGENET_STD


class ImagePreprocessor:
    """图像预处理类（训练/验证/推理统一接口）"""

    def __init__(self, img_size=IMG_SIZE, grayscale=False, is_train=False):
        """
        Args:
            img_size: 目标图像尺寸
            grayscale: 是否转换为灰度图（保留单通道并复制为3通道）
            is_train: 是否为训练模式（启用数据增强）
        """
        self.img_size = img_size
        self.grayscale = grayscale
        self.is_train = is_train
        self._build_transforms()

    def _build_transforms(self):
        """构建 torchvision transforms 序列"""
        transform_list = []

        # 1. 转换为 PIL 图像（如输入为numpy数组或路径，在调用时处理）
        # 实际在 __call__ 中先处理灰度/尺寸，此处定义后续步骤

        # 2. 数据增强（仅训练模式）
        if self.is_train:
            transform_list.extend([
                transforms.RandomResizedCrop(self.img_size, scale=(0.8, 1.0)),
                transforms.RandomHorizontalFlip(p=0.5),
                transforms.RandomRotation(15),
                transforms.ColorJitter(brightness=0.2, contrast=0.2, saturation=0.2),
            ])
        else:
            transform_list.append(transforms.Resize((self.img_size, self.img_size)))

        # 3. 转换为张量并归一化
        transform_list.append(transforms.ToTensor())
        transform_list.append(transforms.Normalize(mean=IMAGENET_MEAN, std=IMAGENET_STD))

        self.transform = transforms.Compose(transform_list)

    def _preprocess_pil(self, pil_img):
        """处理 PIL 图像：灰度化（可选）、应用 transforms"""
        if self.grayscale:
            # 转为灰度（单通道）
            pil_img = pil_img.convert('L')
            # 复制为3通道以匹配模型输入
            pil_img = pil_img.convert('RGB')
        return self.transform(pil_img)

    def __call__(self, image):
        """
        统一接口：支持 PIL.Image / numpy.ndarray (BGR或RGB) / 文件路径
        Returns:
            torch.Tensor: (3, IMG_SIZE, IMG_SIZE) 预处理后的图像张量
        """
        # 1. 统一转为 PIL Image
        if isinstance(image, str):
            # 文件路径
            pil_img = Image.open(image).convert('RGB')
        elif isinstance(image, np.ndarray):
            # OpenCV 读取为 BGR，需转为 RGB
            if len(image.shape) == 3 and image.shape[2] == 3:
                image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
            pil_img = Image.fromarray(image)
        elif isinstance(image, Image.Image):
            pil_img = image.convert('RGB')
        else:
            raise TypeError(f"Unsupported image type: {type(image)}")

        # 2. 应用预处理流程
        tensor = self._preprocess_pil(pil_img)
        return tensor


# 数据增强独立示例（如需更丰富增强，可使用 albumentations）
class DataAugmentation:
    """可选的高级数据增强（基于 albumentations）"""
    def __init__(self, img_size=IMG_SIZE):
        import albumentations as A
        self.transform = A.Compose([
            A.RandomResizedCrop(img_size, img_size, scale=(0.8, 1.0)),
            A.HorizontalFlip(p=0.5),
            A.Rotate(limit=15),
            A.ColorJitter(brightness=0.2, contrast=0.2, saturation=0.2, p=0.5),
            A.Normalize(mean=IMAGENET_MEAN, std=IMAGENET_STD),
        ])

    def __call__(self, image):
        # image: numpy array (H,W,C) RGB
        augmented = self.transform(image=image)
        tensor = torch.from_numpy(augmented['image']).permute(2,0,1).float()
        return tensor


# 简单测试
if __name__ == "__main__":
    # 创建预处理器（验证模式）
    preprocessor = ImagePreprocessor(is_train=False)
    dummy_img = np.random.randint(0, 255, (300, 400, 3), dtype=np.uint8)
    tensor = preprocessor(dummy_img)
    print(f"Preprocessed tensor shape: {tensor.shape}")  # (3, 224, 224)