function fdata = sft(t,tdata,targetF)
%SFT specific Fourier transform
%   FDATA = SFT(T,TDATA,TARGETF) performs a FFT on the time-domain signal
%   TDATA vs. T. Zero-padding is employed to ensure the target frequency
%   TARGETF is directly in the middle of a bin, thereby reducing spectral
%   leakage and providing the most accurate frequency response at N*TARGETF
%   where N=0,1,2, ...
%
%   See also OFT, FFT
%
%   Written by Nick Selby Spring 2016

Fs = length(t)./(t(end)-t(1));
f = ((0:(length(t)/2))/length(t))'*Fs;
N = ceil((find(f>=targetF,1)-1)*Fs/targetF);
fdata = fft(tdata,N);
end