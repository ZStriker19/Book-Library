$(document).ready(function(){
	let authorCounter = 2;
	let characterCounter = 2;
	let genreCounter = 2;
	$('#add-another-author').on('click', (event) => {
		$('#author' + authorCounter.toString()).show('slow');
		authorCounter++;
	})
	
	$('#add-another-character').on('click', (event) => {
		$('#character' + characterCounter.toString()).show('slow');
		characterCounter++;
	})
	
	$('#add-another-genre').on('click', (event) => {
		$('#genre' + genreCounter.toString()).show('slow');
		genreCounter++;
	});
});
