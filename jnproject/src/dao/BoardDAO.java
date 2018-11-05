package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.BoardVO;
import vo.CommentVO;

public class BoardDAO {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	//댓글을 위한 변수
	CommentVO comment;
	
	public Connection getConnection() {
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
			con=ds.getConnection();
		}catch (Exception e) {			
			System.out.println("DB 커넥션 실패"+e);	
		}	
		return con;
	}
	//close()
	private void close(Connection con,PreparedStatement pstmt,
			ResultSet rs) {
		try {
				if(rs!=null)	rs.close();
				if(pstmt!=null)	pstmt.close();			
				if(con!=null)	con.close();			
				}catch(SQLException e) {
					e.printStackTrace();
				}
		}
	private void close(Connection con,PreparedStatement pstmt) {
		try {				
				if(pstmt!=null)		pstmt.close();			
				if(con!=null)		con.close();			
			}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	//게시판 전체 목록 가져오기
	public ArrayList<BoardVO> selectAll(){
		//번호, 제목, 작성자, 날짜, 조회수가 보여질 것임
		ArrayList<BoardVO> list=new ArrayList<>();
		
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="select bno,rank,writer,name,content,readcount,score ";
			//sql+="from board order by bno desc";
			sql+="from churilist order by rank asc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO vo=new BoardVO();
				vo.setBno(rs.getInt(1));
				vo.setRank(rs.getInt(2));
				vo.setWriter(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setContent(rs.getString(5).substring(0,70)+"...");
				vo.setReadcount(rs.getInt(6));
				vo.setScore(rs.getInt(7));
				list.add(vo);
			}
			con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}			
		return list;
	}
	//bno에 해당하는 글 하나 가져오기
	public BoardVO getRow(int bno) {
		BoardVO vo=null;
		
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="select bno,rank,writer,name,content,readcount,score,info,price ";
			//sql+="from board order by bno desc";
			sql+="from churilist where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new BoardVO();
				vo.setBno(rs.getInt(1));
				vo.setRank(rs.getInt(2));
				vo.setWriter(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setContent(rs.getString(5));
				vo.setReadcount(rs.getInt(6));
				vo.setScore(rs.getInt(7));
				vo.setInfo(rs.getString(8));
				vo.setPrice(rs.getInt(9));
			}
			//댓글 카운팅
			sql="select count(*) from churicomments where article_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo.setCommentCount(rs.getLong(1));
			}
			
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return vo;
		
	}
	//조회수 올리기
	public int setReadCountUpdate(int bno) {
		int result=0;
		BoardVO vo=null;
		try {
			con=getConnection();//커넥션 받아오고
			con.setAutoCommit(false);//오토커밋으로 만들고
			String sql="update churilist set readcount=readcount+1 where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result=pstmt.executeUpdate();
			if(result>0) 
				con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return result;
	}
	
	public ArrayList<CommentVO> selectComments(int bno){
		ArrayList<CommentVO> comments=new ArrayList<>();
		
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="select * from churicomments where article_number=? order by re_ref asc,re_seq asc";
			//sql+="from board order by bno desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentVO cmtvo=new CommentVO();
				cmtvo.setCmt_number(rs.getInt(1));
				cmtvo.setCmt_name(rs.getString(2));
				cmtvo.setCmt_content(rs.getString(3));
				cmtvo.setCmt_date(rs.getDate(4));
				cmtvo.setArticle_number(rs.getInt(5));
				cmtvo.setCmt_password(rs.getString(6));
				cmtvo.setRe_ref(rs.getInt(7));
				cmtvo.setRe_seq(rs.getInt(8));
				comments.add(cmtvo);
			}
			con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}			
		return comments;
		
	}
		
	public synchronized HashMap<String, Object> insertComment(String name, String cmt_content, int article_number, String password){
		HashMap<String, Object> hm=new HashMap<>();

		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="insert into churicomments(cmt_name,cmt_content,cmt_date,article_number) ";
					sql+="values(?, ?, sysdate(), ?, ?)";
			//sql+="from board order by bno desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);;
			pstmt.setString(2, cmt_content);
			pstmt.setInt(3, article_number);//아티클넘버는 bno임
			pstmt.setString(4, password);
			rs=pstmt.executeQuery();
			ArrayList<CommentVO> comments=null;
			comments=selectComments(article_number);
			hm.put("result", rs);
			hm.put("comments", comments);
			con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		return hm;
	}
	
	public int insertCmt(CommentVO comment) {
		int result=0;
		int re_ref=0;
		try {
			con=getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("select max(cmt_number) from churicomments"); 
			rs=pstmt.executeQuery();
			if(rs.next())
				re_ref=rs.getInt(1)+1;
			else
				re_ref=1;
			String sql="insert into churicomments(cmt_name,cmt_content,cmt_date,article_number,cmt_password,re_ref) ";
					sql+="values(?, ?, now(), ?, ?,?);";
			//sql+="from board order by bno desc";
			pstmt=con.prepareStatement(sql);
			System.out.println(comment.getCmt_name());
			System.out.println(comment.getCmt_content());
			System.out.println(comment.getArticle_number());
			System.out.println(comment.getCmt_password());
			pstmt.setString(1, comment.getCmt_name());;
			pstmt.setString(2, comment.getCmt_content());
			pstmt.setInt(3, comment.getArticle_number());//아티클넘버는 bno임
			pstmt.setString(4, comment.getCmt_password());
			pstmt.setInt(5, re_ref);
			result=pstmt.executeUpdate();
			if(result>0) 
				con.commit();
				
			}catch(Exception e) {
				try {
					con.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				close(con,pstmt,rs);
			}
			
		return result;
		}
	//댓글답글삽입
	public int insertCmtReply(CommentVO comment) {
		int result=0;
		int re_seq=0;
		try {
			con=getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("select max(re_seq) from churicomments where cmt_number=?"); 
			pstmt.setInt(1, comment.getRe_ref());
			rs=pstmt.executeQuery();
			if(rs.next())
				if(rs.getInt(1)!=0)
					re_seq=rs.getInt(1)+1;
				else
					re_seq=1;
			String sql="insert into churicomments(cmt_name,cmt_content,cmt_date,article_number,cmt_password,re_ref,re_seq) ";
			sql+="values(?, ?, now(), ?, ?,?,?);";
			//sql+="from board order by bno desc";
			pstmt=con.prepareStatement(sql);
			System.out.println(comment.getCmt_name());
			System.out.println(comment.getCmt_content());
			System.out.println(comment.getArticle_number());
			System.out.println(comment.getCmt_password());
			pstmt.setString(1, comment.getCmt_name());;
			pstmt.setString(2, comment.getCmt_content());
			pstmt.setInt(3, comment.getArticle_number());//아티클넘버는 bno임
			pstmt.setString(4, comment.getCmt_password());
			pstmt.setInt(5, comment.getRe_ref());
			pstmt.setInt(6, re_seq);
			result=pstmt.executeUpdate();
			if(result>0) 
				con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return result;
	}
	//비밀번호 확인
		public boolean pwdCheck(int cmt_number,String cmt_password,int article_number) {
			boolean flag=false;
			try {
				con=getConnection();
				con.setAutoCommit(false);
				String sql="select cmt_number from churicomments where cmt_number=? and cmt_password=? and article_number=?";
				pstmt=con.prepareStatement(sql);
				System.out.println(cmt_number);
				System.out.println(cmt_password);
				System.out.println(article_number);
				pstmt.setInt(1, cmt_number);
				pstmt.setString(2, cmt_password);
				pstmt.setInt(3, article_number);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					flag=true;
				}
				con.commit();
				
			} catch (Exception e) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				close(con,pstmt,rs);
			}
			return flag;
		}
		public int deleteRow(int cmt_number) {
			int result=0;
			try {
				con=getConnection();//커넥션 받아오고
				con.setAutoCommit(false);//오토커밋으로 만들고
				pstmt=con.prepareStatement("select re_seq from churicomments where cmt_number=?"); 
				pstmt.setInt(1, cmt_number);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getInt(1)!=0){
						String sql="delete from churicomments where cmt_number=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, cmt_number);
						result=pstmt.executeUpdate();
					}
					else {
						String sql="delete from churicomments where re_ref=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, cmt_number);
						result=pstmt.executeUpdate();
					}
				}
				if(result>0) 
					con.commit();
			}catch(Exception e) {
				try {
					con.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				close(con,pstmt,rs);
			}
				
			return result;
		}	
}













