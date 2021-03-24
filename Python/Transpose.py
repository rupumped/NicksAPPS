import torch

class Transpose(torch.nn.Module):
	def __init__(self):
		super(Transpose, self).__init__()

	def forward(self, x):
		return torch.transpose(x, 0,1)