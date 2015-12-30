var xmlHttp=false;

function createXMLHttpRequest() {
 if(window.XMLHttpRequest) {
  xmlHttp = new XMLHttpRequest();
 } else if(window.ActiveXObject) {
  try{
	  xmlHttp=new ActiveXObject("Msxm12.XMLHTTP");
  }catch(e){
	  try{
		  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }catch(e){}  
  }
 }
}
function changeSelect_fir(){
	createXMLHttpRequest();
	var district=document.getElementById("select_district").value;
	var url="weixin_selectFirServlet?district="+district;
	xmlHttp.onreadystatechange=callback_fir;
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
}
function callback_fir(){
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			document.getElementById("select").innerHTML="";
			document.getElementById("select").innerHTML=xmlHttp.responseText;
		}
	}
} 
function changeSelect_sec(){
	createXMLHttpRequest();
	var district=document.getElementById("select_district").value;
	var street=document.getElementById("select_street").value;
	var url="weixin_selectSecServlet?district="+district+"&street="+street;
	xmlHttp.onreadystatechange=callback_sec;
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
}
function callback_sec(){
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			document.getElementById("select").innerHTML="";
			document.getElementById("select").innerHTML=xmlHttp.responseText;
		}
	}
} 