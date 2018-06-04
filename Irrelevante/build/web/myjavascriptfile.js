/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function changeImage() {
element=document.getElementById('myimage') 
if(element.src.match("down")) {
element. src="IMAGES/FIDEO.jpeg"; } 
else {
element. src="IMAGES/TEAM.jpeg";
} 
}