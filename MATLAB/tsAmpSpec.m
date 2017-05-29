function varargout = tsAmpSpec(varargin)
%SSAMPSPEC single-sided amplitude spectrum
%   [F,FDATA] = TSAMPSPEC(T,FDATA) returns the two-sided amplitude
%   spectrum of the DFT FDATA given its time domain T. The frequency
%   response of the system is stored in FDATA vs. F
%
%   [F,FDATA] = TSAMPSPEC(FDATA,FS) uses sampling frequency FS instead of
%   time domain T.
%
%   [F,FDATA] = TSAMPSPEC(FDATA) returns a normalized frequency F in
%   [-.5,.5]
%
%   TSAMPSPEC(...) shifts FDATA by its Nyquist frequency
%
%   See also SSAMPSPEC, FFT, SFT, OFT
%
%   Written by Nick Selby Spring 2016

narginchk(1,2);
nargoutchk(1,2);
if nargin==1
    fdata=varargin{:};
else
    if numel(varargin{2})==1
        fdata = reshape(varargin{1},1,[]);
        Fs = varargin{2};
    else
        t=varargin{1};
        Fs = (length(t)-1)./(t(end)-t(1));
        fdata=reshape(varargin{2},1,[]);
    end
end

fdata = reshape(fdata,1,[]);
N = numel(fdata);
fdata = [fdata(floor(end/2)+1:end),fdata(1:floor(end/2))]/N;
fdata = abs(fdata);                       % amplitude

if nargout==1
    varargout{1} = fdata;
else
    if nargin==1
        f = linspace(-.5,.5-1/N,N);
    else
        f = (((-(N/2-1):(N/2))-1)/N)*Fs;                    %[Hz] frequency
    end
    f(1)=[];
    fdata(1)=[];
    varargout={f,fdata};
end
end