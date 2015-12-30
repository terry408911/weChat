function showImage(imgFile,num){
	var pattern= /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/; 
	if(!pattern.test(imgFile.value)){
		alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
		imgFile.focus();
	}else{
		var path;
		if(document.all){
			 imgFile.select(); 
		      path = document.selection.createRange().text; 
		      document.getElementById("imgPreview"+num).innerHTML=""; 
		      document.getElementById("imgPreview"+num).style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";
		}
		else{
			path = URL.createObjectURL(imgFile.files[0]);
		    document.getElementById("imgPreview"+num).innerHTML = "<img width='"+100+"'height='"+80+"' src='"+path+"'/>"; 
		}
	}
}