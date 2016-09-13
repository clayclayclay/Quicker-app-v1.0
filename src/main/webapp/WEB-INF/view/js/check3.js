var chooseall = document.getElementById('chooseall');
		var choose = document.getElementsByClassName('chooseit');
		chooseall.onclick = function(){
			for(var i=0;i<choose.length;i++){
				choose.item(i).checked = this.checked;
				}
			
		}