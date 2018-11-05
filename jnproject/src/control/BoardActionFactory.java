package control;

import model.Action;
import model.ChuriCommentDeleteAction;
import model.ChuriCommentInsert;
import model.ChuriCommentPwdCheckAction;
import model.ChuriHitUpdateAction;
import model.ChuriReplyReplyAction;
/*import model.BoardDeleteAction;
import model.BoardHitUpdateAction;
import model.BoardInsertAction;
import model.BoardListAction;
import model.BoardModifyAction;
import model.BoardPwdCheckAction;
import model.BoardReplyAction;
import model.BoardReplyViewAction;
import model.BoardSearchAction;
import model.BoardUpdateAction;
import model.BoardViewAction;*/
import model.JnListAction;

public class BoardActionFactory {
	
	private static BoardActionFactory baf=null;
	
	private BoardActionFactory() {}
	public static BoardActionFactory getInstance() {
		if(baf==null)
			baf=new BoardActionFactory();
		return baf;
	}
	
	Action action=null;
	public Action action(String cmd) {
		
		if(cmd.equals("/churiList.do")) {
			//action=new BoardListAction("view/qna_board_list.jsp");
			action=new JnListAction("web/listindex.jsp");
		}
		else if(cmd.equals("/churiHitUpdate.do")) {
			//action=new BoardListAction("view/qna_board_list.jsp");
			action=new ChuriHitUpdateAction("web/single-page.jsp");
		}else if(cmd.equals("/cmtInsert.do")) {
			//action=new BoardListAction("view/qna_board_list.jsp");
			action=new ChuriCommentInsert("churiHitUpdate.do");
		}else if(cmd.equals("/qPwdCheck.do")) {
			action=new ChuriCommentPwdCheckAction("qDelete.do");
		}else if(cmd.equals("/qDelete.do")) {
			action=new ChuriCommentDeleteAction("churiHitUpdate.do");
		}else if(cmd.equals("/reply_reply.do")) {
			action=new ChuriReplyReplyAction("churiHitUpdate.do");
		}
		
		/*else if(cmd.equals("/churiView.do")) {
			//action=new BoardListAction("view/qna_board_list.jsp");
			action=new ChuriHitUpdateAction("/web/single-page.jsp");
		}*/
		
		
		return action;
	}
}



