/**
 * Given a sorted array and a value, return the index at which the value
 * should be inserted to maintain the sorted property of the array.
 *
 * @param {array} sortedArray A sorted array of numbers.
 * @param {number} value The number to find the insertion index for.
 * @return {number} The index to insert the value.
 */
function findInsertionIndex(sortedArray, value) {
	let low = 0;
	let high = sortedArray.length;

	while (low < high) {
		const mid = Math.floor((low + high) / 2);
		if (sortedArray[mid] < value) {
			low = mid + 1;
		} else {
			high = mid;
		}
	}

	return low;
}