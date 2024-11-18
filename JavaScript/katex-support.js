// To prevent linebreaks immediately after an inline KaTeX block (https://github.com/KaTeX/KaTeX/issues/1233)
window.addEventListener('DOMContentLoaded', () => {
	document.querySelectorAll('span:has(span.katex)').forEach(katexSpan => {
		if (katexSpan.nextSibling && katexSpan.nextSibling.nodeType === Node.TEXT_NODE && !/\s/.test(katexSpan.nextSibling.textContent.charAt(0))) {
			katexSpan.lastChild.lastChild.lastChild.innerHTML+= katexSpan.nextSibling.textContent.charAt(0)
			katexSpan.nextSibling.textContent = katexSpan.nextSibling.textContent.slice(1)
		}
	})
})

// To prevent overlap between equation tags and equation bases
function adjustKatexTags() {
	const equations = document.querySelectorAll('.katex-html:has(> .base):has(> .tag)');
	equations.forEach(eq => {
		const base = eq.querySelector('.base');
		const tag = eq.querySelector('.tag');
				
		// Get bounding rectangles
		const baseRect = base.getBoundingClientRect();
		const tagRect = tag.getBoundingClientRect();
		
		// Check for overlap
		const hasOverlap = !(
			baseRect.right < tagRect.left ||
			baseRect.left > tagRect.right ||
			baseRect.bottom < tagRect.top ||
			baseRect.top > tagRect.bottom
		);
		
		if (hasOverlap) {
			// Switch to flex layout only if there's overlap
			eq.style.display = 'flex';
			eq.style.justifyContent = 'space-between';
			eq.style.alignItems = 'center';
			tag.style.position = 'static';
			console.log('here')
		}
	});
}
window.addEventListener('load', adjustKatexTags);
window.addEventListener('resize', adjustKatexTags);