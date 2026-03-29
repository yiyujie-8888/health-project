# 中医体质分类与判定（GB/T 46939-2025）
CONSTITUTION_TYPES = ['平和质', '气虚质', '阳虚质', '阴虚质', '痰湿质', '湿热质', '血瘀质', '气郁质', '特禀质']
CONSTITUTION2IDX = {name: idx for idx, name in enumerate(CONSTITUTION_TYPES)}
IDX2CONSTITUTION = {idx: name for idx, name in enumerate(CONSTITUTION_TYPES)}
IMG_SIZE = 224
IMAGENET_MEAN = [0.485, 0.456, 0.406]
IMAGENET_STD = [0.229, 0.224, 0.225]
