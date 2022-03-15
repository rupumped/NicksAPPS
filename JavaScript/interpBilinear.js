/**
 * Bilinear interpolation over a rectilinear grid.
 *
 * See https://en.wikipedia.org/wiki/Bilinear_interpolation.
 *
 * @param  {object}	x   Array of row headers for lookup table, length xl.
 * @param  {object} y   Array of column headers for lookup table, length yl.
 * @param  {object} z   Array of arrays representing lookup table, size (xl,yl)
 * @param  {number} xq  x value of query.
 * @param  {number} yq  y value of query.
 * @return {number}     Interpolated z value for (xq,yq).
 */
function interpBilinear(x,y,z,xq,yq) {
    // Check dimensions
    if (z.length !== x.length) {
        throw `Number of rows in z (${z.length}) must be equal to length of x (${x.length}).`;
    }
    for (let r=0; r<z.length; r++) {
        if (z[r].length !== y.length) {
            throw `Number of columns in each row of z (${z[r].length} at row ${r}) must be equal to the length of y (${y.length})`;
        }
    }

    // Check to make sure not extrapolating
    if (!issorted(x) || !issorted(y)) {
        throw `Input arrays x and y must both be in ascending order with no repeated values.`;
    }
    if (xq<x[0] || xq>x[x.length-1] || yq<y[0] || yq>y[y.length-1]) {
        throw `Extrapolation not allowed. (${xq},${yq}) must be in the domain of (x,y)`;
    }

    // Interpolate along x axis (rows)
    var x1,x2,xi;
    for (xi=0; xi<x.length; xi++) {
        if (x[xi+1] >= xq) {
            x1 = x[xi  ];
            x2 = x[xi+1];
            break;
        }
    }

    // Interpolate along y axis (columns)
    var y1,y2,yi;
    for (yi=0; yi<y.length; yi++) {
        if (y[yi+1] >= yq) {
            y1 = y[yi  ];
            y2 = y[yi+1];
            break;
        }
    }

    // Implement alternative matrix form from Wikipedia
    var fQ11 = z[xi  ][yi  ];
    var fQ12 = z[xi  ][yi+1];
    var fQ21 = z[xi+1][yi  ];
    var fQ22 = z[xi+1][yi+1];
    
    var alt1 = [[fQ11,fQ12,fQ21,fQ22]];
    var alt2 = [[ x2*y2, -y2, -x2,  1],
                [-x2*y1,  y1,  x2, -1],
                [-x1*y2,  y2,  x1, -1],
                [ x1*y1, -y1, -x1,  1]];
    var alt3 = [[1],[xq],[yq],[xq*yq]];

    return mmultiply(mmultiply(alt1,alt2), alt3)/((x2-x1)*(y2-y1));
}

// Source: https://gist.github.com/jremmen/9454479?permalink_comment_id=2674718#gistcomment-2674718
mmultiply = (a, b) => a.map(x => transpose(b).map(y => dotproduct(x, y)));
dotproduct = (a, b) => a.map((x, i) => a[i] * b[i]).reduce((m, n) => m + n);
transpose = a => a[0].map((x, i) => a.map(y => y[i]));

// Modified from other function in repo to disallow repeated elements
function issorted(arr) {
    return arr.reduce((out, el) => out !== false && el && el > out);
}