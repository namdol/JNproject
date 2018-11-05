package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.CommentVO;

public class ChuriReplyReplyAction implements Action {
	private String path;
	
	public ChuriReplyReplyAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println(req.getParameter("reply_writer"));//cmt_name
		System.out.println(req.getParameter("reply_password"));//cmt_password
		System.out.println(req.getParameter("reply_content"));//cmt_content
		System.out.println(req.getParameter("reply_id"));//cmt_number
		System.out.println(req.getParameter("bno"));//article_number
		//cmt_date:now()  re_ref:cmt_number  re_seq:기본글0, 답글1씩 증가.
		CommentVO comment=new CommentVO();
		comment.setArticle_number(Integer.parseInt(req.getParameter("bno")));
		comment.setCmt_name(req.getParameter("reply_writer"));
		comment.setCmt_password(req.getParameter("reply_password"));
		comment.setCmt_content(req.getParameter("reply_content"));
		comment.setRe_ref(Integer.parseInt(req.getParameter("reply_id")));
		
		//db삽입
		BoardDAO dao=new BoardDAO();
		int result=dao.insertCmtReply(comment);
		
		if (result<0) {
			path="web/single-page.jsp";
		}else {
			path+="?bno="+comment.getArticle_number();
		}
		return new ActionForward(path, true);
		
		
	}

}
