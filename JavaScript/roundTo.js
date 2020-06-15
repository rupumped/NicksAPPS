/**
 * toFixed() that returns numbers.
 *
 * Rounds a number to a specified number of decimals.
 *
 * @param  {number}	num       Number to be rounded.
 * @param  {number} decimals  Number of decimals to which to round.
 * @return {number}           Rounded number
 */
function roundTo(num, decimals) {
	return parseFloat(num.toFixed(decimals));
}