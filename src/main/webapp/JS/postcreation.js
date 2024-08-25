/**
 * 
 */

 function postfun(){
	 var postname = document.getElementById("postname").value;
        
        if(postname.trim() === ''){
            alert("post content required");
            return false;    
        } else {
           return true;
        }
 }
 
 function changeImg(value){
	let img = document.getElementById("twitterpic");
	img.src = URL.createObjectURL(value.files[0]);
 }