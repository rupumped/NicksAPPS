// This script is adapted from Murray Bourne's "KaTeX tag placement with no wrap"
// https://bourne2learn.com/math/katex/tag-placement_nowrap.html

function adjustTag() {
	var browserWidth = document.querySelector('body').clientWidth;
	var eqnWidth = 0;
	var tagWidth = 0;

	var tags = document.querySelectorAll('.katex-html .tag');	
	
	for(i=0;i<tags.length;i++) {
		eqnWidth = tags[i].parentNode.querySelector('.base').offsetWidth;
		tagWidth = tags[i].clientWidth;
		
		tags[i].style.border = "";
		
		if(1.1*eqnWidth + 2*tagWidth > browserWidth) {
			tags[i].style.display = "inline-block";
			tags[i].style.marginLeft = "8px";
			tags[i].style.position = "static";
		} else {
			tags[i].style.background = "";
			tags[i].style.position = "absolute";
			tags[i].style.right = 0;
		}
	}
}

function waitKaTeX() {
	if(document.querySelectorAll('.katex-html').length == 0 ) {
		requestAnimationFrame(waitKaTeX);
	} else {
		cancelAnimationFrame(raf);
		adjustTag();
	}
}
var raf = window.requestAnimationFrame(waitKaTeX);

window.addEventListener("resize", adjustTag);
