function q = unwrap_revset(p,rev,cutoff,dim)
%UNWRAP_REVSET unwrap phase angle
%   UNWRAP_REVSET(P,REV) unwraps phases P by changing absolute jumps
%   greater than REV/2 to their REV complement.
%
%   UNWRAP_REVSET(P,REV,CUTOFF) and UNWRAP_REVSET(P,REV,CUTOFF,DIM) work
%   like adding the CUTOFF and/or DIM inputs to the UNWRAP function.
%
%   See also UNWRAP
%
%   Written by Nick Selby Fall 2016

ratio=2*pi/rev;
switch nargin
    case 2
        q=unwrap(ratio*p)/ratio;
    case 3
        q=unwrap(ratio*p,cutoff)/ratio;
    case 4
        q=unwrap(ratio*p,cutoff,dim)/ratio;
end