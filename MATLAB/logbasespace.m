function y = logbasespace(d1, d2, base, n)
%LOGSPACE Logarithmically spaced vector.
%   LOGSPACE(X1, X2, BASE) generates a row vector of 50 logarithmically
%   equally spaced points between decades BASE^X1 and BASE^X2.  If X2
%   is pi, then the points are between BASE^X1 and pi.
%
%   LOGSPACE(X1, X2, BASE, N) generates N points.
%   For N = 1, LOGSPACE returns BASE^X2.
%
%   Class support for inputs X1,X2:
%      float: double, single
%
%   See also LOGSPACE, LINSPACE, COLON.

%   Modified from LOGSPACE (Copyright 1984-2012 The MathWorks, Inc.)

if nargin == 3
    n = 50;
end

if d2 == pi || d2 == single(pi) 
    d2 = logbase(base,d2);
end

y = base .^ linspace(d1, d2, n);
