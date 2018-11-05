package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class ChuriCommentPwdCheckAction implements Action{
	private String path;
	
	public ChuriCommentPwdCheckAction(String path) {
		super();
		this.path = path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

		int cmt_number=Integer.parseInt(req.getParameter("cmt_number"));
		String cmt_password=req.getParameter("cmt_password");
		int article_number=Integer.parseInt(req.getParameter("article_number"));
		
		BoardDAO dao=new BoardDAO();
		boolean flag=dao.pwdCheck(cmt_number, cmt_password, article_number);
		System.out.println(flag);
		System.out.println(path);
		if(flag) {//qDelete.do?bno=1;
			path+="?cmt_number="+cmt_number+"&article_number="+article_number;
		}else {
			path="web/pwdCheckError.jsp";
			System.out.println(path);
		}
		return new ActionForward(path, true);
	}

}
