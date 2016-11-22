function theta = vecAngle2(v1,v2)
%VECANGLE2 calculate angle between two vectors in R2
%
%   VECANGLE2(V1,V2) returns the angle in radians from V2 to V1 using the
%   four quadrant inverse tangent
%
%   See also ATAN2, ANGLE
%
%   Written by Nick Selby Fall 2016

v1=v1/norm(v1);
v2=v2/norm(v2);
theta=atan2(v2(2),v2(1))-atan2(v1(2),v1(1));
end