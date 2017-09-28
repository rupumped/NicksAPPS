function [m,b] = point2(x1,x2,y1,y2)
p = polyfit([x1,x2],[y1,y2],1);
m = p(1);
b = p(2);
end