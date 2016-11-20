function [b,a] = peakBandStop(x,N,bw)
%PEAKBANDSTOP Butterworth bandstop filter on highest-amplitude frequency
%   [B,A] = PEAKBANDSTOP(X,N,BW) designs an Nth order bandstop digital
%   Butterworth filter and returns the filter coefficients in length N+1
%   vectors B (numerator) and A (denominator). The coefficients are listed
%   in descending powers of z. The cutoff frequencies Wn are the frequency
%   at which the amplitude of the corresponding frequency component of X is
%   maximized plus or minus BW/2. The highest-amplitude frequency component
%   of X is determined using a single-sided amplitude spectrum from FFT(X)
%
%   See also FILTER, BUTTER, SSAMPSPEC, FFT
%
%   Written by Nick Selby Fall 2016

[f,fdata] = tsAmpSpec(fft(x));
[~,ind] = max(fdata);
[b,a]=butter(N,[max([f(ind)-bw/2,f(2)]),min([f(ind)+bw/2,f(end)])],'stop');
end