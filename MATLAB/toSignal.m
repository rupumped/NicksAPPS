function x = toSignal(rssi,phase)
%TOSIGNAL calculates complex signal
%   TOSIGNAL(RSSI,PHASE) returns the complex signal X[n] = A*exp(j*PHASE)
%   given vectors containing RSSI and PHASE data. PHASE is assumed to be
%   measured in radians. The amplitude of the signal is calulated from the
%   RSSI via the following formulation:
%       RSSI = 10log10(P[W]*1000); P = A^2/2/50 = A^2/100 (50 for antenna impedence)
%       => A = sqrt(P[W]*100), P[W] = 10^(RSSI/10)/1000
%       => A = sqrt(10^(RSSI/10)/10)
%
%   See also TOSIGNALD
%
%   Written by Nick Selby Fall 2016

amplitude = sqrt(10.^(rssi/10)/10);
x=amplitude.*exp(1i*phase);
end