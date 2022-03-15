/**
 * Is the array sorted?
 *
 * Checks to see if the input array is sorted in ascending order.
 *
 * @param  {object}	 arr  Input array to check.
 * @return {boolean}      true iff the array is sorted.
 */
function issorted(arr) {
    return arr.reduce((out, el) => out !== false && el && el >= out);
}