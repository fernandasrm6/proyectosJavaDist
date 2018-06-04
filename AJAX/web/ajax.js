/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function loadNewContent(id, target) { var ajaxRequest;
        if (window.XMLHttpRequest){
            ajaxRequest=new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
} else {
ajaxRequest=new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
}
ajaxRequest.onreadystatechange = function(){
if (ajaxRequest.readyState==4 && ajaxRequest.status==200){
document.getElementById(id).innerHTML=ajaxRequest.responseText; }
}
ajaxRequest.open("GET", target, true /*async*/);
ajaxRequest. send(); }