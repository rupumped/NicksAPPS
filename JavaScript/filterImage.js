/**
 * Generates filter to change color of monochromatic images.
 *
 * Generates the string to be applied to the style.filter attribute of the img element you want to tranform.
 * The input image must be monochromatic and white on a transparent background.
 * See https://stackoverflow.com/questions/29037023/how-to-calculate-required-hue-rotate-to-generate-specific-colour
 * This doesn't work very well. It gets kind of close.
 *
 * @param  {number}	h  Hue of desired color (0<h<255);
 * @param  {number}	s  Saturation of desired color (0<s<255);
 * @param  {number}	v  Value of desired color (0<v<255);
 * @return {string}    String to be applied to style.filter.
 */
function filterImage(h, s, v) {
	const h_s = 38;
	const s_s = 24.5;
	const v_s = 60;

	s = 100*s/255;
	v = 100*v/255;

	var hue_rotate = h-h_s;
	var saturate = 100 + (s_s - s);
	var brightness = 100 + (v - v_s);

	return `brightness(50%) sepia(1) hue-rotate(${hue_rotate}deg) saturate(${saturate}%) brightness(${brightness}%)`;
}