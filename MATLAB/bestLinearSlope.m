function m = bestLinearSlope(x,y)
%BESTLINEARSLOPE the least-squares slope of a regression line whose
%y-intercept is zero
%
%   M = BESTLINEARSLOPE(X,Y) returns the slope of a best fit regression
%   line which intersects the origin. This function is meant to be used to
%   eliminate y-intercepts of regression lines from data
%
%   See also polyfit
%
%   Written by Nick Selby Spring 2016

x = reshape(x,numel(x),1);
y = reshape(y,numel(y),1);
m = (x'*x)\x'*y;
end