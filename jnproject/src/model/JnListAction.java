package model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

public class JnListAction implements Action {
	private String path;
	public JnListAction(String path) {
		this.path=path;
	}

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		BoardDAO dao=new BoardDAO();
		ArrayList<BoardVO> list=dao.selectAll();
		
		if (list.isEmpty()) {
			path="index.html";
		}else {
			req.setAttribute("list", list);
		}
		return new ActionForward(path, false);
	}

}
