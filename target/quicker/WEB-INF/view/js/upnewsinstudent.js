

function fileSelected() {
	var file = document.getElementById('fileToUpload').files[0];
	if (file) {
		var fileSize = 0;
		if (file.size > 1024 * 1024)
			fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
		else
			fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

		document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
		document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
		document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
	};
};

//function uploadFile() {
//	var fd = new FormData();
//	fd.append("file", document.getElementById('fileToUpload').files[0]);
//
//	var xhr = null;
//	if (window.ActiveXObject) {//如果是IE
//
//		xhr = new ActiveXObject("Microsoft.XMLHTTP");
//
//	} else if (window.XMLHttpRequest) {
//
//		xhr = new XMLHttpRequest(); //实例化一个xhr
//	}
//
//	xhr.upload.addEventListener("progress", uploadProgress, false);
//	xhr.addEventListener("load", uploadComplete, false);
//	xhr.addEventListener("error", uploadFailed, false);
//	xhr.addEventListener("abort", uploadCanceled, false);
//	xhr.open("POST", "/quicker/web/upload");
//	xhr.send(fd);
//};

function uploadProgress(evt) {
	if (evt.lengthComputable) {
		var percentComplete = Math.round(evt.loaded * 100 / evt.total);
		document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
	}else {
		document.getElementById('progressNumber').innerHTML = 'unable to compute';
	};
};

function uploadComplete(evt) {
	$(".suup").slideDown('slow', function() {
		$(this).delay(1000).slideUp('slow');
	});
};

function uploadFailed(evt) {
	$(".errup").text("文件上传失败");
	$(".errup").slideDown('slow', function() {
		$(this).delay(1000).slideUp('slow');
	});
};

function uploadCanceled(evt) {
	$(".errup").text("文件上传被取消");
	$(".errup").slideDown('slow', function() {
		$(this).delay(1000).slideUp('slow');
	});
};

$().ready(function(){
	var heigh = $(window).height();
	var f = false;
	if (heigh > 470) {
		$(".container").css({
			minHeight:heigh-320
		})
	};

	$(".stitle").click(function(event) {
		if(f == false){
			$(this).nextAll("form").slideDown('400', function() {
			});
			f = true;
		}else{
			$(this).nextAll("form").slideUp('400', function() {
			});
			f = false;
		};
	});




	$("#upviews").click(function(event) {
		var viewname = $(".intitle").val();
		var viewmore = $("#contents").val();

		$.ajax({
			type:"post",
			url:"/quicker/web/departSendActivity",
			data:{
				"title":viewname,
				"contents":viewmore
			},
			dataType:"json",
			success:function(data){
				$(".suup").slideDown('slow', function() {
					$(this).delay(1000).slideUp('slow');
				});
			},
			error:function(data){
				alert('1');
				$(".errup").text(data.errorMsg.description);
				$(".errup").slideDown('slow', function() {
					$(this).delay(1000).slideUp('slow');
				});
			}

		});
		return false;
	});

	$(".hybutton").click(function(event) {
		$('#fileToUpload').trigger('click');
	});


});


