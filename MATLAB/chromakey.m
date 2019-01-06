function img = chromakey(img,rgb_key,rgb_rep,tol)
%CHROMAKEY replace one color in an image with another
%   CHROMAKEY(IMG,RGB_KEY,RGB_REP) returns an a new image that takes IMG
%   and replaces all pixels of the color represented by the three-element
%   uint8 vector RGB_KEY with pixels of the color RGB_REP.
%
%   CHROMAKEY(...,TOL) applies a tolerance to RGB_KEY. If TOL is a scalar
%   uint8, then it is applied to all elements of RGB_KEY. If TOL is a
%   vector uint8, then it is applied element-wise.

% Isolate layers
red = img(:,:,1);
grn = img(:,:,2);
blu = img(:,:,3);

% Create mask
if nargin==3
    tol = zeros(3,1);
elseif numel(tol)==1
    tol = tol*ones(3,1);
end
mask = red>rgb_key(1)-tol(1) & red<rgb_key(1)+tol(1) & ...
       grn>rgb_key(2)-tol(2) & grn<rgb_key(2)+tol(2) & ...
       blu>rgb_key(3)-tol(3) & blu<rgb_key(3)+tol(3);
   
% Mask Image
red(mask) = rgb_rep(1);
grn(mask) = rgb_rep(2);
blu(mask) = rgb_rep(3);
img = cat(3,red,grn,blu);
end