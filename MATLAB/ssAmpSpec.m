function varargout = ssAmpSpec(varargin)
%SSAMPSPEC single-sided amplitude spectrum
%   [F,FDATA] = SSAMPSPEC(T,FDATA) returns the single-sided amplitude
%   spectrum of the DFT FDATA given its time domain T. The frequency
%   response of the system is stored in FDATA vs. F
%
%   [F,FDATA] = SSAMPSPEC(FDATA) returns a normalized frequency F in [0,1]
%
%   SSAMPSPEC(T,FDATA) and SSAMPSPEC(FDATA) folds FDATA along its Nyquist
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

N = length(fdata);
fdata = fdata(1:floor(N/2)+1);
fdata = fdata/N;
fdata(2:end-1) = 2*fdata(2:end-1);
fdata = abs(fdata);                       % amplitude

if nargout==1
    varargout{1} = fdata;
else
    if nargin==1
        f=linspace(0,1,N/2+1);
    else
        Fs = length(t)./(t(end)-t(1));
        f = ((0:(N/2))/N)'*Fs;                    %[Hz] frequency
    end
    varargout={f,fdata};
end
end