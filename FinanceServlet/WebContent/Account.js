$(document).ready(function(){
	
	$('#depositTab').hide();
	$('#withdrawTab').hide();
	$('#historyTab').hide();
	$('#logoutTab').hide();
	
	
	$('#deposit').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#deposit').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#withdraw').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#withdraw').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#history').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#history').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	$('#logout').mouseover(function(){
		$(this).css('cursor','pointer');
	});
	$('#logout').mouseleave(function(){
		$(this).css('cursor','pointer');
	});
	
	
	
	$('#deposit').click(function(){
		$('#depositTab').toggle();
	});
	
	$('#withdraw').click(function(){
		$('#withdrawTab').toggle();
	});
	
	$('#history').click(function(){
		$('#historyTab').toggle();
		document.getElementById('fromYear').value="";
		document.getElementById('toYear').value="";
		var radio = document.getElementsByName('act');
		for(var i=0;i<radio.length;i++){
			radio[i].checked=false;
		}
		
	});
	
	$('#logout').click(function(){
		$('#logoutTab').toggle();
	});
	
});