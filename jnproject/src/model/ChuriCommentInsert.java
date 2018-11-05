package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.CommentVO;

public class ChuriCommentInsert implements Action{
	private String path;
	public ChuriCommentInsert(String path) {
		this.path=path;
	}
	
	/*@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		int bno=Integer.parseInt(req.getParameter("bno"));
		BoardDAO dao=new BoardDAO();
		int result=dao.insertCmt(name, cmt_content, article_number, password)
		if(result>0) {
			path+="?bno="+bno;
		}
		BoardVO vo=dao.getRow(bno);
		if(vo!=null) {
			req.setAttribute("vo", vo);
		}
		//댓글 담기
		ArrayList<CommentVO> comments=dao.selectComments(bno);
		req.setAttribute("comments", comments);
		
		return new ActionForward(path, false);
	}*/
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		CommentVO comment=new CommentVO();
		comment.setArticle_number(Integer.parseInt(req.getParameter("article_number")));
		comment.setCmt_name(req.getParameter("cmt_name"));
		comment.setCmt_password(req.getParameter("cmt_password"));
		comment.setCmt_content(req.getParameter("cmt_content"));
		System.out.println(Integer.parseInt(req.getParameter("article_number")));
		System.out.println(req.getParameter("cmt_name"));
		System.out.println(req.getParameter("cmt_password"));
		System.out.println(req.getParameter("cmt_content"));
		//db삽입
		BoardDAO dao=new BoardDAO();
		int result=dao.insertCmt(comment);
		
		if (result<0) {
			path="web/single-page.jsp";
		}else {
			path+="?bno="+comment.getArticle_number();
		}
		return new ActionForward(path, true);
	}
}
