package org.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.model.PostLayoutModel;
import org.model.PostModel;

public class CreatePostRepository extends DBHelper{
	
	
	
	
	//generate postid
	public int autoIncrementPostID() {
		try {
			ps= con.prepareStatement("select max(postid) from postmaster");
			rs =ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
			
		}catch(Exception e) {
			System.out.println("autoIncrementPostID error :"+e);
			return -1;
		}
	}
	
	// store post and post image name in database
	public boolean isaddUserNewPost(PostModel model) {
		try {
			int postID=this.autoIncrementPostID();
			postID=postID+1;
			
			ps = con.prepareStatement("insert into postmaster values(?,?,(select curdate()),?)");
			
			ps.setInt(1, postID);
			ps.setString(2, model.getPost());
			ps.setString(3, model.getImgname());
			int v=ps.executeUpdate();
			
			if(v>0) {
				ps =con.prepareStatement("insert into postregistrationjoin values (?,?)");
				ps.setInt(1, postID);
				ps.setInt(2, model.getRegisterid());
				v=ps.executeUpdate();
			}
			return (v>0) ? true : false;
		}catch(Exception e) {
			System.out.println("post master repository error :"+e);
			return false;
		}
	}
	
	
	
	/*view All posts or like,comment date wise decreasing order particular user*/
	public List<PostLayoutModel> ViewAllPosts(int userID){
		List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store particular user post id ,post,like,comment count
		
		ArrayList<Integer> alPost =new ArrayList<Integer>();   // store post id particular user
		
		try {
			// we fetch post id and store in ArrayList
			ps =con.prepareStatement("select pm.postid from postmaster pm "
					+ "inner join postregistrationjoin prj on pm.postid=prj.postid "
					+ "inner join registrationmaster rm on rm.registerid=prj.registerid where rm.registerid=? "
					+ "order by pm.postdate desc");
			ps.setInt(1, userID);
			rs=ps.executeQuery();
			while(rs.next()) {
				alPost.add(rs.getInt(1));
			}
			
			// we fetch post id ,post , count like ,comment count for particular post
			if(alPost.size()>0) {               // we check user posts not found
				for(Integer lc:alPost) {
					
					PostLayoutModel pmodel = new PostLayoutModel();
										
					ps=con.prepareStatement("select username,profileimg from registrationmaster where registerid=?");
					ps.setInt(1, userID);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setUsername(rs.getString(1)); // set username
						pmodel.setProfileimage(rs.getString("profileimg"));
					}
				
					pmodel.setPostid(lc);      // set post id
					
					ps=con.prepareStatement("select post,imgname from postmaster where postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setPost(rs.getString(1));   // set post
						pmodel.setImgname(rs.getString(2)); // set imgname
					}
					
					// we fetch like count of post
					ps =con.prepareStatement("select count(lm.likeid) from likemaster lm "
							+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
							+ "inner join postmaster pm on pm.postid=lpj.postid "
							+ "where pm.postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setLikeCount(rs.getInt(1));   // set count like post 
					}
					
					// we fetch comment count of post
					ps=con.prepareStatement("select count(cm.commentid) from commentmaster cm "
							+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
							+ "inner join postmaster pm on pm.postid=pcj.postid "
							+ "where pm.postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setCommentCount(rs.getInt(1)); //  set comment count of post
					}
				  list.add(pmodel);
				}
			}
			
			return (list.size()>0) ? list:null;
		}catch(Exception e) {
			System.out.println("post master repository error :"+e);
			return null;
		}
	}
	
	
	
	/*view All posts or like,comment all application users*/
	public List<PostLayoutModel> ViewAllPosts(){
		List<PostLayoutModel> list= new LinkedList<PostLayoutModel>(); // store particular user post id ,post,like,comment count
		
		ArrayList<Integer> alPost =new ArrayList<Integer>();   // store post id users
		
		try {
			// we fetch post id and store in ArrayList
			ps =con.prepareStatement("select pm.postid from postmaster pm "
					+ "inner join postregistrationjoin prj on pm.postid=prj.postid "
					+ "inner join registrationmaster rm on rm.registerid=prj.registerid "
					+ "order by pm.postdate desc");
			rs=ps.executeQuery();
			while(rs.next()) {
				alPost.add(rs.getInt(1));
			}
			
			// we fetch post id ,post , count like ,comment count for particular post
			if(alPost.size()>0) {               // we check  posts not found
				for(Integer lc:alPost) {
					
					PostLayoutModel pmodel = new PostLayoutModel();
										
					ps=con.prepareStatement("select rm.username,rm.profileimg from registrationmaster rm "
							+ "inner join postregistrationjoin prj on prj.registerid=rm.registerid "
							+ "inner join postmaster pm on pm.postid=prj.postid "
							+ "where pm.postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setUsername(rs.getString(1)); // set username
						pmodel.setProfileimage(rs.getString("profileimg")); // set profile photo name
					}				
					
					pmodel.setPostid(lc);      // set post id
					
					ps=con.prepareStatement("select post,imgname from postmaster where postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setPost(rs.getString(1));   // set post
						pmodel.setImgname(rs.getString(2)); // set post image name
					}
					
					// we fetch like count of post
					ps =con.prepareStatement("select count(lm.likeid) from likemaster lm "
							+ "inner join likepostjoin lpj on lpj.likeid=lm.likeid "
							+ "inner join postmaster pm on pm.postid=lpj.postid "
							+ "where pm.postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setLikeCount(rs.getInt(1));   // set count like post 
					}
					
					// we fetch comment count of post
					ps=con.prepareStatement("select count(cm.commentid) from commentmaster cm "
							+ "inner join postcommentjoin pcj on pcj.commentid=cm.commentid "
							+ "inner join postmaster pm on pm.postid=pcj.postid "
							+ "where pm.postid=?");
					ps.setInt(1, lc);
					rs=ps.executeQuery();
					if(rs.next()) {
						pmodel.setCommentCount(rs.getInt(1)); //  set comment count of post
					}
				  list.add(pmodel);
				}
			}
			
			return (list.size()>0) ? list:null;
		}catch(Exception e) {
			System.out.println("post master repository error :"+e);
			return null;
		}
	}
	
	
	
	
	/*Delete post from database*/
	public int deletePost(int postID) {
		try {
			ps=con.prepareStatement("delete from postmaster where postid=?");
			ps.setInt(1, postID);
			int value=ps.executeUpdate();
			return (value>0)?1:0;
		}catch(Exception e) {
			System.out.println("post repo error :"+e);
			return -1;
		}
	}
	
}
