function v = stretch(v,new_size)
%STRETCH stretches a vector to a new length
%   STRETCH(V,NEW_SIZE) returns a vector of length NEW_SIZE containing the
%   elements in vector V repeated
%
%   Examples:
%       stretch(1:3,6)
%           -> [1     1     2     2     3     3]
%       stretch(1:3,9)
%           -> [1     1     1     2     2     2     3     3     3]
%       stretch(1:6,8)
%           -> [1     2     3     3     4     4     5     6]
%
%   Note: This is NOT the standard implementation of interp1 and linspace
%
%   See also IMRESIZE, INTERP1, LINSPACE

s = size(v);
v = interp1(0.5:1:numel(v)-0.5, v, linspace(numel(v)/new_size,numel(v)-numel(v)/new_size,new_size), 'nearest','extrap');
s(s~=1) = new_size;
v = reshape(v,s);
end