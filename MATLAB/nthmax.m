function [y,i] = nthmax(x,n)
for j=1:n
    [y,i] = max(x);
    x(i) = -Inf;
end
end