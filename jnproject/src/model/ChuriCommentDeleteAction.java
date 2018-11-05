package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;

public class ChuriCommentDeleteAction implements Action {
	private String path;
	
	
	public ChuriCommentDeleteAction(String path) {
		super();
		this.path = path;
	}


	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//PwdCheck.do에서 넘어오는 값 가져오기
		int cmt_number=Integer.parseInt(req.getParameter("cmt_number"));
		int article_number=Integer.parseInt(req.getParameter("article_number"));
		//삭제한 후 리스트로 경로 이동
		BoardDAO dao=new BoardDAO();
		System.out.println(cmt_number);
		int result=dao.deleteRow(cmt_number);
		System.out.println(result);
		if (result>0) {
			path+="?bno="+article_number;
		}
		
		return new ActionForward(path, true);
	}

}
