import numpy as np

def cdf(data, bins=50):
    count, bins_count = np.histogram(data, bins=bins)
    pdf = count / sum(count)
    cdf = np.cumsum(pdf)
    return bins_count[1:], cdf