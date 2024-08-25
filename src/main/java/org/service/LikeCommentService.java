package org.service;

import org.model.PostModel;
import org.repository.LikeCommentRepository;

public class LikeCommentService {

	
	LikeCommentRepository likecommentRepo = new LikeCommentRepository();
	
	/*comment logic*/
	public boolean isAddComment(PostModel model) {
		return likecommentRepo.isAddComment(model);
	}
	
	// add like in database
	public boolean isAddLike(int postid,int registerid) {
		return likecommentRepo.isAddLike(postid, registerid);
	}
	
	// check user like or not post
	public int checkLike(int postid,int userID) {
		return likecommentRepo.checkLike(postid,userID);
	}
	
	// fetch like count of post
	public int fetchLikeCount(int postid) {
		return likecommentRepo.fetchLikeCount(postid);
	}
		
	// unlike post logic
	public int unLikePost(int postid,int userID) {	
		return likecommentRepo.unLikePost(postid, userID);
	}
}
