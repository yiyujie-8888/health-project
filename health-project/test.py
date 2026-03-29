"""
测试脚本：验证模型前向传播和预处理流程
"""
import sys
import os

# 将当前脚本所在目录（项目根目录）添加到 Python 模块搜索路径
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

print("Current working directory:", os.getcwd())
print("sys.path:", sys.path)

import torch
import numpy as np
from ai_model.model import get_model
from ai_model.preprocess import ImagePreprocessor
from ai_model.utils import predict_prob
from ai_model.config import IDX2CONSTITUTION, IMG_SIZE


def test_model_forward():
    """测试模型前向传播"""
    print("1. 测试模型前向传播...")
    model = get_model(num_classes=9, pretrained=False)  # 使用随机权重
    dummy_input = torch.randn(2, 3, IMG_SIZE, IMG_SIZE)
    output = model(dummy_input)
    assert output.shape == (2, 9), f"输出形状错误: {output.shape}"
    print("✓ 模型前向传播正常，输出形状:", output.shape)


def test_preprocessing():
    """测试图像预处理流程"""
    print("\n2. 测试图像预处理...")
    preprocessor = ImagePreprocessor(is_train=False)
    # 模拟随机图像（numpy）
    dummy_img = np.random.randint(0, 255, (300, 400, 3), dtype=np.uint8)
    tensor = preprocessor(dummy_img)
    assert tensor.shape == (3, IMG_SIZE, IMG_SIZE), f"预处理张量形状错误: {tensor.shape}"
    print("✓ 图像预处理正常，张量形状:", tensor.shape)


def test_inference():
    """测试完整推理流程"""
    print("\n3. 测试完整推理流程...")
    # 创建模型和预处理器
    model = get_model(num_classes=9, pretrained=False)
    preprocessor = ImagePreprocessor(is_train=False)

    # 生成随机图像并推理
    dummy_img = np.random.randint(0, 255, (300, 400, 3), dtype=np.uint8)
    tensor = preprocessor(dummy_img)
    probs, pred_idx, pred_label = predict_prob(model, tensor)

    print("预测概率（前3高）:")
    top3 = sorted(enumerate(probs), key=lambda x: x[1], reverse=True)[:3]
    for idx, prob in top3:
        print(f"  {IDX2CONSTITUTION[idx]}: {prob:.4f}")
    print(f"预测体质: {pred_label} (索引 {pred_idx})")
    print("✓ 推理流程正常")


if __name__ == "__main__":
    test_model_forward()
    test_preprocessing()
    test_inference()
    print("\n所有测试通过！环境与模型框架运行正常。")