/**
 * MATLAB-style sorting to also return an index vector.
 *
 * See https://www.mathworks.com/help/matlab/ref/sort.html#d124e1456163.
 *
 * @param  {object}	arr          Array to sort.
 * @param  {function} compareFn  Comparator function. If left undefined, arr will be sorted in ascending order.
 * @return {object} Object with two fields:
 *                   - sortedArray: the sorted version of arr
 *                   - sortedIndices: the same size as sortedArray and describes the arrangement of the elements of arr into sortedArray
 */
function sortWithIndices(arr, compareFn=undefined) {
	// Create new array with all the elements of arr labeled with their indices
	const indexedArray = arr.map((value, index) => [value, index]);

	// Sort the new array according to the comparator. If no comparator was given, sort in ascending order.
	indexedArray.sort(compareFn ? (a, b) => compareFn(a[0], b[0]) : (a, b) => a[0] - b[0]);

	// Map the values and labels to two outputs.
	const sortedArray = indexedArray.map(item => item[0]);
	const sortedIndices = indexedArray.map(item => item[1]);

	return { sortedArray, sortedIndices };
}

/**
 * MATLAB-style indexing of an array. Matches A(I) for same-size vectors A and I.
 *
 * @param  {object}	arr  Array to rearrange, like A.
 * @param  {object} ind  Indices describing the new arrangement of the elements of arr, like I.
 * @return {object} Rearranged array, like A(I).
 */
function orderArray(arr, ind) {
	return ind.map(index => arr[index])
}