function hex = binaryVectorToHex(bin)
%BINARYVECTORTOHEX Converts binary vector to hexadecimal string
%   BINARYVECTORTOHEX(BIN) returns a character array of the hexadecimal
%   representation of the binary value contained in the vector BIN
%
%   See also DEC2HEX, BIN2DEC, NUM2STR
%
%   Written by Nick Selby Fall 2016

hex=dec2hex(bin2dec(num2str(bin)));
end