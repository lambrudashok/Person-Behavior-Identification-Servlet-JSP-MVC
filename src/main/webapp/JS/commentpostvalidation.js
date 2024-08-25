/**
 * 
 */



 function followUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("prodiv3").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","follow?followid="+value,true);
	xhttp.send();
 }
 
 function unfollowUser(value){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("prodiv3").innerHTML=this.responseText;
		}
	};
	xhttp.open("POST","unfollow?unfollowid="+value,true);
	xhttp.send();
 }

 
  function unfollowShow(button){
	button.style.color="red";
	button.style.fontWeight="bold";
	button.style.border="red solid 1px";
	button.style.backgroundColor="#1a0000";
	button.innerHTML="Unfollow";
 }
 
 
 function followingShow(button){
	button.style.color="white";
	button.style.fontWeight="normal";
	button.style.backgroundColor="#BDBDBD";
	button.innerHTML="Following";
	button.style.fontWeight="none";
	button.style.border="none";
	}
 
 



 function commentfun(){
	 var commentname = document.getElementById("comment").value;
        
        if(commentname.trim() == ''){
            alert("comment not empty");
            return false;    
        } else {
           return true;
        }
 }
 
 function likefun(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likecommentGrid"+postid).innerHTML=this.responseText;
			
		}
	};
	xhttp.open("POST","likecontroller?postid="+postid,true);
	xhttp.send();
 }
 
 function unlikefun(postid){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			document.getElementById("likecommentGrid"+postid).innerHTML=this.responseText;
			
		}
	};
	xhttp.open("POST","unlikecontroller?postid="+postid,true);
	xhttp.send();
 }
 