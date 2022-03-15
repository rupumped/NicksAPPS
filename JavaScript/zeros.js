/**
 * 2D array of zeros
 *
 * Constructs an array of arrays where each element is zero.
 *
 * @param  {number}	x  Number of rows in output array.
 * @param  {number} y  Number of columns in output array.
 * @return {number}    2D zeros array filled of size (x,y).
 */
function zeros(x,y) {
    arr = new Array(x);
    for (let i=0; i<x; i++) {
        arr[i] = new Array(y).fill(0);
    }
    return arr;
}