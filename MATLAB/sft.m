function fdata = sft(in1,in2,targetF)
%SFT specific Fourier transform
%   FDATA = SFT(T,TDATA,TARGETF) performs a FFT on the time-domain signal
%   TDATA vs. T. Zero-padding is employed to ensure the target frequency
%   TARGETF is directly in the middle of a bin, thereby reducing spectral
%   leakage and providing the most accurate frequency response at N*TARGETF
%   where N=0,1,2, ...
%
%   SFT(TDATA,FS,TARGETF) uses sampling frequency Fs instead of time domain
%   T.
%
%   See also OFT, FFT
%
%   Written by Nick Selby Spring 2016

if numel(in2)==1
    tdata = in1;
    Fs = in2;
else
    t = in1;
    Fs = length(t)./(t(end)-t(1));
    tdata = in2;
end

f = ((0:(numel(tdata)/2))/numel(tdata))'*Fs;
N = ceil((find(f>=targetF,1)-1)*Fs/targetF);
fdata = fft(tdata,N);
end