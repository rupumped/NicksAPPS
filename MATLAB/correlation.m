function c = correlation(a,b)
%CORRELATION vector correlation
%   CORRELATION(A,B) returns the correlation of column vectors A and B
%   using the following formula:
%       <a,b>/sqrt(||a||*||b||)
%
%   See also CORR, CORRCOEF

c = a.'*b/(norm(a)*norm(b));
end