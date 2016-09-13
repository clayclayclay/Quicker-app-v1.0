document.getElementById('add').onclick=function(){
	var name=document.getElementById('fname').value;
	var num=document.getElementById('lname').value;
	if (name=="" ||num=="") {
		alert("输入不能为空");
	}else{
		var table=document.getElementById('table');
		var newstu=document.createElement("tr");
		newstu.className="student";
		newstu.innerHTML="<td class='td1'>"+name+"</td><td class='td2'>"+num+"</td><td class='td3'><button type='button' autofocus='autofocus'  id='delet'>删除</button></td>";
		table.appendChild(newstu);
		document.getElementById('fname').value="";
		var num=document.getElementById('lname').value="";
	}
}



var stu=document.getElementsByClassName("delet");
for (var i = 0; i < stu.length; i++) {
	stu[i].onclick=function(){
		this.parentNode.parentNode.remove();
	}
}


