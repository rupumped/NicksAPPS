function q = unwrap_revset(p,rev)
%UNWRAP_REVSET unwrap phase angle
%   UNWRAP_REVSET(P,REV) unwraps phases P by changing absolute jumps
%   greater than REV/2 to their REV complement.
%
%   See also UNWRAP
%
%   Written by Nick Selby Fall 2016

ratio=2*pi/rev;
q=unwrap(ratio*p)/ratio;
end