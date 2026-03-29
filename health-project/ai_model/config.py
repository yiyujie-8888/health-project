"""
中医体质分类与判定（GB/T 46939-2025）
定义9种体质类别及对应的中文名称
"""

# 体质类别列表（顺序固定，与模型输出索引一致）
CONSTITUTION_TYPES = [
    "平和质",   # 0
    "气虚质",   # 1
    "阳虚质",   # 2
    "阴虚质",   # 3
    "痰湿质",   # 4
    "湿热质",   # 5
    "血瘀质",   # 6
    "气郁质",   # 7
    "特禀质"    # 8
]

# 类别到索引的映射
CONSTITUTION2IDX = {name: idx for idx, name in enumerate(CONSTITUTION_TYPES)}
IDX2CONSTITUTION = {idx: name for idx, name in enumerate(CONSTITUTION_TYPES)}

# 模型输入图像尺寸
IMG_SIZE = 224

# ImageNet 归一化参数（用于预训练模型）
IMAGENET_MEAN = [0.485, 0.456, 0.406]
IMAGENET_STD = [0.229, 0.224, 0.225]