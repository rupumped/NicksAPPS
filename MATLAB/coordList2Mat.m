function [ampMatrix,xVals,yVals] = coordList2Mat(x_pos,y_pos,amp)
%COORDLIST2MAT transforms a vector with corresponding coordinate lists into
%a mesh for surface plotting
%
%   [AMPMATRIX,XVALS,YVALS] = COORDLIST2MAT(X_POS,Y_POS,AMP) reshapes
%   vector AMP into mesh AMPMATRIX using coordinates [X_POS,Y_POS].
%   Outputs XVALS and YVALS are the unique x and y position coordinates.
%
%   See also DSEARCHN, MESHGRID, RESHAPE, UNIQUE
%
%   Written by Nick Selby Fall 2015

xVals = unique(x_pos);
yVals = unique(y_pos);

ampMatrix = zeros(numel(yVals),numel(xVals));

for i = 1:numel(x_pos)
    Ix = dsearchn(xVals',x_pos(i));
    Iy = dsearchn(yVals',y_pos(i));
    ampMatrix(Iy,Ix) = amp(i);
end
end