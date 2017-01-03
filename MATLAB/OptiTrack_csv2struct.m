function opti = OptiTrack_csv2struct(fn)
%OPTITRACK_CSV2STRUCT extract data from OptiTrack output
%   OPTITRACK_CSV2STRUCT(FN) returns a structure containing the following
%   fields:
%    - t: the time of the reading
%    - x: the x position of the trackable
%    - y: the y position of the trackable
%    - z: the z position of the trackable
%   and a few others I'll write about later.

opti_temp = struct('t',[] ...
             ,'x',[],'y',[],'z',[] ...
             ,'qx',[],'qy',[],'qz',[],'qw',[] ...
             ,'yaw',[],'pitch',[],'roll',[]);
opti = opti_temp;
fh = fopen(fn);
line = fgetl(fh);
while ischar(line)
    if strncmp(line,'frame',5)
        line = regexp(line,number_pattern,'tokens');
        line = arrayfun(@(c) str2double(c{:}), line);
        trackables = line(3);
        if trackables == 1
            for t=0:trackables-1
    %             opti(end+1,:) = line([2,5:14]);
                trackable = line(4+t*21);
                if trackable ~= round(trackable) || trackable < 1
                    0;
                end
                if trackable>numel(opti)
                    opti(trackable) = opti_temp;
                end
                opti(trackable).t    (end+1) = line( 2);
                opti(trackable).x    (end+1) = line( 5+t*21);
                opti(trackable).y    (end+1) = line( 6+t*21);
                opti(trackable).z    (end+1) = line( 7+t*21);
                opti(trackable).qx   (end+1) = line( 8+t*21);
                opti(trackable).qy   (end+1) = line( 9+t*21);
                opti(trackable).qz   (end+1) = line(10+t*21);
                opti(trackable).qw   (end+1) = line(11+t*21);
                opti(trackable).yaw  (end+1) = line(12+t*21);
                opti(trackable).pitch(end+1) = line(13+t*21);
                opti(trackable).roll (end+1) = line(14+t*21);
            end
        end
    end
    line = fgetl(fh);
end
fclose(fh);
% opti=struct('t' ,num2cell(opti(:,1)) ...
%            ,'x' ,num2cell(opti(:,2)),'y' ,num2cell(opti(:,3)),'z' ,num2cell(opti(:,4)) ...
%            ,'qx',num2cell(opti(:,5)),'qy',num2cell(opti(:,6)),'qz',num2cell(opti(:,7)),'qw',num2cell(opti(:,8)) ...
%            ,'yaw',num2cell(opti(:,9)),'pitch',num2cell(opti(:,10)),'roll',num2cell(opti(:,11)));
end