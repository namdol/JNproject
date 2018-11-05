package model;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;
import vo.CommentVO;

public class ChuriHitUpdateAction implements Action {
	private String path;
	public ChuriHitUpdateAction(String path) {
		this.path=path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		int bno=Integer.parseInt(req.getParameter("bno"));
		BoardDAO dao=new BoardDAO();
		int result=dao.setReadCountUpdate(bno);
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
	}

}
