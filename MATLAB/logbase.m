function y = logbase(b,x)
%LOGBASE Arbitrary base logarithm
%   Y = LOGBASE(B,X) is the base B logarithm of the elements of X.
%
%   See also LOG, LOG10, LOG2.

y = log(x) ./ log(b);
end