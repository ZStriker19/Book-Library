$(document).ready(function(){
	let authorCounter = 1;
	let characterCounter = 1;
	let genreCounter = 1
	$('#add-another-author').on('click', (event) => {
		console.log("clicked add author button");
		$('#author' + authorCounter.toString()).show('slow');
	})
	
	$('#add-another-character').on('click', (event) => {
		$('#character' + characterCounter.toString()).show('slow');
	})
	
	$('#add-another-genre').on('click', (event) => {
		$('#genre' + genreCounter.toString()).show('slow');
	})
})
