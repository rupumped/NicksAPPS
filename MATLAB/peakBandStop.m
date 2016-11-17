function [b,a] = peakBandStop(x,N,bw)
[f,fdata] = ssAmpSpec(fft(x));
[~,ind] = max(fdata);
[b,a]=butter(N,[max([f(ind)-bw/2,f(1)]),min([f(ind)+bw/2,f(end)])],'stop');
end