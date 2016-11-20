function varargout = tsAmpSpec(varargin)
%SSAMPSPEC single-sided amplitude spectrum
%   [F,FDATA] = TSAMPSPEC(T,FDATA) returns the two-sided amplitude
%   spectrum of the DFT FDATA given its time domain T. The frequency
%   response of the system is stored in FDATA vs. F
%
%   [F,FDATA] = TSAMPSPEC(FDATA) returns a normalized frequency F in [-1,1]
%
%   TSAMPSPEC(T,FDATA) and TSAMPSPEC(FDATA) shifts FDATA by its Nyquist
%   frequency
%
%   See also FFT, SFT, OFT
%
%   Written by Nick Selby Spring 2016

narginchk(1,2);
nargoutchk(1,2);
if nargin==1
    fdata=varargin{:};
else
    t=varargin{1};
    fdata=varargin{2};
end

N = numel(fdata);
fdata = [fdata(floor(end/2)+1:end),fdata(1:floor(end/2))]/N;
fdata = abs(fdata);                       % amplitude

if nargout==1
    varargout{1} = fdata;
else
    if nargin==1
        f=linspace(-1,1-2/N,N);
    else
        Fs = length(t)./(t(end)-t(1));
        f = (((-(N/2-1):(N/2))-1)/N)'*Fs;                    %[Hz] frequency
    end
    f(1)=[];
    fdata(1)=[];
    varargout={f,fdata};
end
end