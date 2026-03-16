import torch
import torchvision
import cv2
from PIL import Image

print("✅ Python 环境正常")
print(f"PyTorch 版本: {torch.__version__}")
print(f"TorchVision 版本: {torchvision.__version__}")
print(f"OpenCV 版本: {cv2.__version__}")
print(f"Pillow 版本: {Image.__version__}")

if torch.cuda.is_available():
    print(f"✅ CUDA 可用，设备: {torch.cuda.get_device_name(0)}")
else:
    print("⚠️ CUDA 不可用，将使用 CPU")