/**
 * Subclass of Array that enables basic numeric operations on arrays of numbers.
 */
class NumArray extends Array {
	/**
	 * The standard deviation of the array.
	 * Source: https://stackoverflow.com/questions/7343890/standard-deviation-javascript
	 * @return {number} The standard deviation of the numbers in the array.
	 */
	std() {
		var i,j,total = 0, mean = 0, diffSqredArr = [];
		for(i=0;i<this.length;i+=1){
			 total+=this[i];
		}
		mean = total/this.length;
		for(j=0;j<this.length;j+=1){
			 diffSqredArr.push(Math.pow((this[j]-mean),2));
		}
		return (Math.sqrt(diffSqredArr.reduce(function(firstEl, nextEl){
					return firstEl + nextEl;
				 })/this.length));
	}

	/**
	 * The sum of the numbers in the array.
	 * @return {number} The numeric sum.
	 */
	sum() {
		return this.reduce((a,b) => a+b, 0);
	}

	/**
	 * Applies a function to every value in the array.
	 * This function does not change the original array. Instead, it returns a new array with changed values.
	 * @param  {function} The function to be applied element-wise.
	 * @return {NumArray} The new array.
	 */
	arrayfun(f) {
		return this.map(x => f(x));
	}
}