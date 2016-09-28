$().ready(function(){
	var f = false;
	$('.content tr').hover(function(){

		$(this).children().css({"background-color":'#85B3F2',"color":"#ffffff"});

		$(this).children().children('a').css({"background-color":'#85B3F2',"color":"#ffffff"});

	}).mouseleave(function(){
		$(this).children().css({"background-color":'',"color":''});
		$(this).children().children('a').css({"background-color":'',"color":""});
	})
	$('.bookView:even').addClass('eve');
	$('.bookView:odd').addClass('odd');

	$(".onews a").on('click',clickone=function(event) {
		if(f == false){
			$(this).nextAll(".minuview").slideDown('400', function() {
			});
			f = true;
		}else{
			$(this).nextAll(".minuview").slideUp('400', function() {
			});
			f = false;
		};
	});




	$("#upview").click(function(event) {
		var ssvalue = $(".is-check:checked");
		alert(ssvalue.length);
		var svalue = new Array();
		for (var i = 0; i < ssvalue.length; i++) {
			svalue[i] = ssvalue.eq(i).attr("value");
			alert(svalue);
		};
		$.ajax({
			type:"post",
			url:"/quicker/web/download",
			dataType:"json",
			data:{
				"tableList":svalue.join(";")
			},
			success:function(data){
				if(data.status == true){
					$(".suup").slideDown('slow', function() {
						$(this).delay(1000).slideUp('slow');
					});
				}else{
					$(".errup").text(data.errorMsg.description);
					$(".errup").slideDown('slow', function() {
						$(this).delay(1000).slideUp('slow');
					});
				}
			},
			error:function(data){
				$(".errup").text("服务器异常");
				$(".errup").slideDown('slow', function() {
					$(this).delay(1000).slideUp('slow');
				});
			}
		});
	});

});
	