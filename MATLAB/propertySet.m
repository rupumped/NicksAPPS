function properties = propertySet(properties,in)
%PROPERTYSET sets function property structure
%   PROPERTYSET(DEFAULT,IN) returns a custom property structure for a
%   function given a default property structure DEFAULT and a cell array of
%   properties IN with format {'prop1',val1,'prop2',val2,...}.
%
%   Written by Nick Selby January 2017

if mod(numel(in),2)~=0
    error('Argument error: Each property must have a corresponding value.');
end
if length(in)~=numel(in)
    error('IN must be a cell vector');
end
if ~isstruct(properties) || numel(properties)~=1
    error('DEFAULT must be a scalar struct')
end
if ~all(cellfun(@(s) ischar(s), in(1:2:end)))
    error('%s is not a valid property name. Property names need to be strings.',in{find(cellfun(@(s) ischar(s), in(1:2:end)),1)});
end

propNames = fieldnames(properties);
for i=1:2:numel(in)
    propMask = strcmpi(fieldnames(properties),in{i});
    if ~any(propMask)
        propNameList = char(propNames);
        propNameList = [propNameList,sprintf('\n')*ones(numel(propNames),1)];
        propNameList(end) = ' ';
        error('''%s'' is not a property name. Known property names:\n%s',in{i},propNameList');
    end
    properties.(propNames{propMask}) = in{i+1};
end
end