function x = toSignalD(rssi,phase)
%TOSIGNAL calculates complex signal
%   TOSIGNALD(RSSI,PHASE) returns the complex signal
%   X[n] = A*exp(j*PHASE*pi/180) given vectors containing RSSI and PHASE
%   data. PHASE is assumed to be measured in degrees. The amplitude of the
%   signal is calulated from the RSSI via the following formulation:
%       RSSI = 10log10(P[W]*1000); P = A^2/2/50 = A^2/100 (50 for antenna impedence)
%       => A = sqrt(P[W]*100), P[W] = 10^(RSSI/10)/1000
%       => A = sqrt(10^(RSSI/10)/10)
%
%   See also TOSIGNAL
%
%   Written by Nick Selby Fall 2016

x=toSignal(rssi,phase*pi/180);
end