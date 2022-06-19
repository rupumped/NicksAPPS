#!/usr/bin/python
import sys, re

FILENAME = sys.argv[-1]
CITATION = r'\[\d+\]'

# Parse Input
with open(FILENAME) as txt:
    lines = txt.readlines()

# Separate Works Cited
for i, line in enumerate(lines):
    if re.match(CITATION, line):
        wc = lines[i:]
        lines = lines[:i-1]
        break

# Replace in-text citations with anchors
output = '\t\t<section>\n'
for line in lines:
    line = '<p>' + line[:-1] + '</p>'
    ref = re.search(CITATION, line)
    while ref:
        num = int(ref.group()[1:-1])
        line = line.replace(ref.group(), '<sup class="in-text-citation">[<a id="it{num}" href="#wc{num}">{num}</a>]</sup>'.format(num=num))
        ref = re.search(CITATION, line)
    output += '\t\t\t' + line + '\n'

output+= '\t\t</section>\n\n\t\t<section id="Works-Cited">\n\t\t\t<h2>Works Cited</h2>\n\t\t\t<ol>\n'
# Format Works Cited
for i,work in enumerate(wc):
    if re.match(CITATION, work) is None:
        raise Exception('Error in work: {}'.format(work))
    work = re.sub(CITATION, '', work[:-1])
    output+= '\t\t\t\t<li id="wc{num}"><a href="#it{num}">^</a>{work}</li>\n'.format(num=i+1, work=work)
output+= '\t\t\t</ol>\n\t\t</section>'

# Combine adjacent in-text citations
output = output.replace(']</sup>,<sup class="in-text-citation">[',',')

# Clean up quotes
output = output.replace('“','"')
output = output.replace('”','"')
output = output.replace("’","'")
output = output.replace("`","'")

# Write output file
with open('blogpost.html', 'w') as f:
    f.write(output)