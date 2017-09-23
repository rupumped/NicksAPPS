function [y,i] = nthmin(x,n)
for j=1:n
    [y,i] = min(x);
    x(i) = Inf;
end
end