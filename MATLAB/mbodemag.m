function measurement = mbodemag(varargin)
%MBODEMAG adds the single-sided amplitude spectrum to a measurement
%structure
%   MEASUREMENT = MBODEMAG(MEASUREMENT) performs the OFT on the measurement
%   and returns the resultant single-sided amplitude spectrum in the freq
%   and ampFFT fields
%
%   MEASUREMENT = MBODEMAG(MEASUREMENT,'optimal') is the same as
%   MEASUREMENT = MBODEMAG(MEASUREMENT)
%
%   MEASUREMENT = MBODEMAG(MEASUREMENT,'specific',TARGETF) performs the SFT
%   on the measurement using target frequency TARGETF and returns the
%   resultant single-sided amplitude spectrum in the freq and ampFFT fields
%
%   See also OFT, SFT
%
%   Written by Nick Selby Spring 2016
measurement = varargin{1};
if nargin == 1
    ftType = 'optimal';
else
    ftType = varargin{2};
end
switch ftType
    case 'optimal'
        measurement.ampFFT = oft(measurement.tdata);
    case 'specific'
        measurement.ampFFT = sft(measurement.t,measurement.tdata,varargin{3});
end
[measurement.freq,measurement.ampFFT] = ssAmpSpec(measurement.t,measurement.ampFFT);
% plot(measurement.freq,measurement.ampFFT)
% xlim([0,3*2.1e6])
% title(num2str(measurement.Coords(1)))
% pause
end