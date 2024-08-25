package org.repository;

import org.model.PostModel;

public class LikeCommentRepository extends DBHelper{
	
	
	
	/*get last added comment id*/
	public int getCommentId() {
		try {
			ps =con.prepareStatement("select max(commentid) from commentmaster");
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return -1;
		}
	}
	
	/*comment logic*/
	public boolean isAddComment(PostModel model) {
		try {
			ps = con.prepareStatement("insert into commentmaster values('0',?,(select curdate()),?)");
			ps.setString(1, model.getComment());
			ps.setInt(2, model.getRegisterid());
			int v=ps.executeUpdate();
			
			int commentID=this.getCommentId();
			ps=con.prepareStatement("insert into postcommentjoin values (?,?)");
			ps.setInt(1, model.getPostid());
			ps.setInt(2, commentID);
			int value=ps.executeUpdate();
			
			return (value>0)?true:false;
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return false;
		}
	}
	
	
	
	// fetch last like
	public int getLikeId() {
		try {
			ps =con.prepareStatement("select max(likeid) from likemaster");
			rs=ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("like repo error :"+e);
			return -1;
		}
	}
	
	
	// add like in database
	public boolean isAddLike(int postid,int registerid) {
		try {
			ps= con.prepareStatement("insert into likemaster value('0',?)");
			ps.setInt(1, registerid);
			int v=ps.executeUpdate();
			
			int likeid=this.getLikeId();
			ps=con.prepareStatement("insert into likepostjoin values(?,?)");
			ps.setInt(1, likeid);
			ps.setInt(2, postid);
			int v1=ps.executeUpdate();
			return v1>0?true:false;
		}catch(Exception e) {
			System.out.println("error :"+e);
			return false;
		}
	}
	
	// fetch like count of post
	public int fetchLikeCount(int postid) {
		try {
			ps =con.prepareStatement("select count(lm.likeid) from likemaster lm "
					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
					+ "inner join postmaster pm on pm.postid=lpj.postid "
					+ "where pm.postid=?");
			ps.setInt(1, postid);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);   // return post like count 
			}else {
				return 0;
			}
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	
	// check person like or not
	public int checkLike(int postid,int userID) {
		try {
			
			ps=con.prepareStatement("select lm.registerid from likemaster lm "
					+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
					+ "where lpj.postid=? and lm.registerid=?");
			ps.setInt(1, postid);
			ps.setInt(2, userID);
			rs=ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
			
		}catch(Exception e) {
			System.out.println("error :"+e);
			return 0;
		}
	}
	
	// unlike post logic
		public int unLikePost(int postid,int userID) {
			try {
				int like=0;
				ps=con.prepareStatement("select lm.likeid from likemaster lm "
						+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
						+ "where lpj.postid=? and lm.registerid=?");
				ps.setInt(1, postid);
				ps.setInt(2, userID);
				rs=ps.executeQuery();
				if(rs.next()) {
					like=rs.getInt(1);
				}
				
				ps=con.prepareStatement("delete from likemaster where likeid=?");
				ps.setInt(1, like);
				int v=ps.executeUpdate();
				
				return (v>0) ? 1 : 0;
				
			}catch(Exception e) {
				System.out.println("error :"+e);
				return 0;
			}
		}
	
	

}
