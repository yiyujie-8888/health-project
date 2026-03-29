"""
基于 MobileNetV2 的舌诊体质分类模型
输出9种体质类别的概率分布
"""

import torch
import torch.nn as nn
from torchvision import models
from .config import CONSTITUTION_TYPES, IMG_SIZE


class ConstitutionClassifier(nn.Module):
    """9种体质分类模型（MobileNetV2骨干网络）"""

    def __init__(self, num_classes=9, pretrained=True):
        super(ConstitutionClassifier, self).__init__()
        # 加载预训练的 MobileNetV2
        self.backbone = models.mobilenet_v2(pretrained=pretrained)

        # 替换分类头：MobileNetV2 最后一层输出通道数为1280
        in_features = self.backbone.classifier[1].in_features
        self.backbone.classifier = nn.Sequential(
            nn.Dropout(0.2),
            nn.Linear(in_features, num_classes)
        )

    def forward(self, x):
        """
        Args:
            x: (batch, 3, IMG_SIZE, IMG_SIZE) 输入图像张量
        Returns:
            logits: (batch, num_classes) 未归一化的分数
        """
        return self.backbone(x)


def get_model(num_classes=9, pretrained=True):
    """工厂函数：创建并返回模型实例"""
    model = ConstitutionClassifier(num_classes, pretrained)
    return model


# 简单测试模型前向传播
if __name__ == "__main__":
    model = get_model()
    dummy_input = torch.randn(2, 3, IMG_SIZE, IMG_SIZE)
    output = model(dummy_input)
    print(f"Output shape: {output.shape}")  # 应为 (2, 9)