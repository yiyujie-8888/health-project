"""
模型推理、权重管理等辅助函数
"""

import torch
import torch.nn.functional as F
from .config import IDX2CONSTITUTION


def predict_prob(model, image_tensor, device='cpu'):
    """
    对单张图像进行预测，返回各类别概率
    Args:
        model: 训练好的模型
        image_tensor: (3, H, W) 预处理后的张量
        device: 设备
    Returns:
        probs: numpy array, shape (9,)
        pred_idx: int, 预测类别索引
        pred_label: str, 预测体质名称
    """
    model.eval()
    model.to(device)
    with torch.no_grad():
        if image_tensor.dim() == 3:
            image_tensor = image_tensor.unsqueeze(0)  # 添加 batch 维度
        image_tensor = image_tensor.to(device)
        logits = model(image_tensor)
        probs = F.softmax(logits, dim=1).cpu().numpy()[0]
        pred_idx = int(probs.argmax())
        pred_label = IDX2CONSTITUTION[pred_idx]
    return probs, pred_idx, pred_label


def save_checkpoint(model, optimizer, epoch, save_path):
    """保存模型检查点"""
    torch.save({
        'epoch': epoch,
        'model_state_dict': model.state_dict(),
        'optimizer_state_dict': optimizer.state_dict(),
    }, save_path)


def load_checkpoint(model, checkpoint_path, device='cpu'):
    """加载模型检查点"""
    checkpoint = torch.load(checkpoint_path, map_location=device)
    model.load_state_dict(checkpoint['model_state_dict'])
    return model, checkpoint.get('epoch', 0)