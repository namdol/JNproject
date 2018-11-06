안녕하세요?
신입 개발자 남도영입니다.
잘 하겠습니다!
***
**프로젝트명** : jn-100

**수행기간** : 2018.10.1 ~ 2018.10.22

**개발목표** : 장르별로 1~20위까지 총 100개의 소설을 추천해주고 댓글을 달 수 있도록 개발한 웹프로젝트입니다.

**개발환경** : 이클립스, JAVA/jsp, mySQL

**구현기능** : 장르별로 소설 게시판 구분(추리 소설만 구현함)/조회수/댓글 작성/댓글에 대한 댓글 작성/댓글 삭제
          
혼자서 진행한 개인 프로젝트이며 디자인은 무료 부트스트랩 템플릿을 프로젝트에 맞게 재구축하여 진행했습니다

---

<img src="https://user-images.githubusercontent.com/43259813/47989435-596c2080-e128-11e8-8fb6-86069d66d658.jpg">

프로젝트 구조

---

<img src="https://user-images.githubusercontent.com/43259813/47988210-fdec6380-e124-11e8-8173-e98d79dfa166.jpg">

추리소설의 랭킹보기 버튼을 클릭하면 추리소설 페이지로 이동
```{.java}
//index.html

<h2 class="mx-auto mb-5">
<em>추리소설</em>
</h2>
<a class="btn btn-primary btn-xl" href="churiList.do">랭킹 보기</a>
```
---

<img src="https://user-images.githubusercontent.com/43259813/47988212-fdec6380-e124-11e8-976c-57e0ee10600c.jpg">

데이터베이스에 저장해 놓은 책 정보(순위, 이미지, 제목, 작가, 소개글을 subString 한것, 조회수)를 순위 순으로 정렬하여 화면에 출력
```{.java}
//BoardActionFactory.java

if(cmd.equals("/churiList.do")) {
action=new JnListAction("web/listindex.jsp");
}


//JnListAction.java

BoardDAO dao=new BoardDAO();
ArrayList<BoardVO> list=dao.selectAll()


//BoardDAO.java.selectAll()

String sql="select bno,rank,writer,name,content,readcount,score from churilist order by rank asc";
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
return list;


//listindex.jsp

<c:forEach var="vo" items="${list}">
          <li onclick="location.href='churiHitUpdate.do?bno=${vo.bno}';">
          <h4 class="style2"><a href="#">${vo.rank}<b style="font-size: 13px">위</b></a></h4>
          <img src="web/images/book/${vo.name}.jpg" width=70% height=70%><!-- width="282" height="118" -->
          <div class="post-info">
          <div class="post-basic-info">
                    <h3><a href="#">${vo.name}</a></h3>
                    <span><a href="#">-&nbsp;${vo.writer}&nbsp;-</a></span>
                    <p>${vo.content}</p>
          </div>
          <div class="post-info-rate-share">
                    <div class="rateit">
                              <span> </span>
                    </div>
                    <div class="post-share">
                              <span>조회수 : ${vo.readcount} </span>
                    </div>
                    <div class="clear"> </div>
          </div>
          </div>
          </li>
</c:forEach>
```
---

<img src="https://user-images.githubusercontent.com/43259813/47988213-fdec6380-e124-11e8-8d05-0c18fc0ca160.jpg">

책을 선택하면 DB에 저장되어 있는 해당 책의 조회수를 증가시키고 자세한 정보를 보여주는 페이지로 이동, 책의 고유번호를 참조하는 참조키를 가진 댓글 테이블에서 댓글을 출력

네이버 버튼은 해당 책을 네이버 책에 검색하는 새창
(a href="https://book.naver.com/search/search.nhn?query=${vo.name}")
```{.java}
//BoardActionFactory.java

else if(cmd.equals("/churiHitUpdate.do")) {
          action=new ChuriHitUpdateAction("web/single-page.jsp");
}


//ChuriHitUpdateAction.java

int bno=Integer.parseInt(req.getParameter("bno"));
          BoardDAO dao=new BoardDAO();
          //조회수 증가
	int result=dao.setReadCountUpdate(bno);
          if(result>0) {
                    path+="?bno="+bno;
          }
          //책정보 담기
          BoardVO vo=dao.getRow(bno);
          if(vo!=null) {
                    req.setAttribute("vo", vo);
          }
          //댓글 담기
          ArrayList<CommentVO> comments=dao.selectComments(bno);
          req.setAttribute("comments", comments);

          return new ActionForward(path, false);


//BoardDAO.java.setReadCountUpdate()
          
          String sql="update churilist set readcount=readcount+1 where bno=?";
	pstmt=con.prepareStatement(sql);
	pstmt.setInt(1, bno);
	result=pstmt.executeUpdate();
          
          
//BoardDAO.java.selectComments()
          
          String sql="select * from churicomments where article_number=? order by re_ref asc,re_seq asc";
          pstmt=con.prepareStatement(sql);
          pstmt.setInt(1, bno);
          rs=pstmt.executeQuery();


//single-page.jsp
          
          //책 정보 부분
          <p align="right">조회수 : ${vo.readcount}</p>
          <h4 class="style3"><a>${vo.rank}<b style="font-size: 20px">위</b></a></h4>
          <img src="web/images/book/${vo.name}.jpg" title="banner1">
          <p>&nbsp;</p>
          <h3><a href="#">${vo.name}</a></h3>
          <p>&nbsp;</p>
          <p>${vo.writer}</p> 
          <p>${vo.info}</p> 
          <p>${vo.price}원</p> 
          <p>&nbsp;</p>
          <p><a>${vo.content}</a></p> 
          
          //댓글 부분
          <h2>Comments</h2>
          <c:forEach var="cmt" items="${comments}">
          <c:if test="${cmt.re_seq==0}">//그냥 댓글이라면
          <div class="grid1_of_2">
                    <div class="grid_img">
                              <img src="web/images/pic10.jpg" alt="">
                    </div>
                    <div class="grid_text">
                              <h4 class="style2 list">${cmt.cmt_name}</h4>
                              <h3 class="style2">${cmt.cmt_date}</h3>
                              <p class="para top"> ${cmt.cmt_content}</p>
                              <a class="btn1" name="reply_reply" reply_id="${cmt.cmt_number}" bno="${vo.bno}"                                                             style="cursor:pointer">Reply</a>
                              <a style="cursor:pointer" onclick="openDelForm(${cmt.cmt_number},${vo.bno})" class="btn2">Delete</a>
                    </div>
                    <div class="clear"></div>
          </div>
          </c:if>
          <c:if test="${cmt.re_seq!=0}">//댓글에 대한 댓글이라면
          <div class="grid1_of_2 left">
                    <div class="grid_img">
                              <img src="web/images/pic12.jpg" alt="">
                    </div>
                    <div class="grid_text">
                              <h4 class="style2 list">${cmt.cmt_name}</h4>
                              <h3 class="style2">${cmt.cmt_date}</h3>
                              <p class="para top"> ${cmt.cmt_content}</p>
                              <a style="cursor:pointer" onclick="openDelForm(${cmt.cmt_number},${vo.bno})" class="btn2">Delete</a>
                    </div>
                    <div class="clear"></div>
          </div>
          </c:if>
          </c:forEach>
```


---

<img src="https://user-images.githubusercontent.com/43259813/47988214-fe84fa00-e124-11e8-9a52-6c9b38804998.jpg">

페이지 하단에 댓글 입력창 위치

댓글의 Reply를 누르면 댓글 바로 하단에 댓글 입력창 생기고 댓글에 대한 댓글 입력 가능.

댓글에 대한 댓글은 다른 레벨의 속성값을 가지고 DB에 저장되며 delete만 가진 채 들여쓰기로 출력.

Delete를 누르면 비밀번호 확인창이 뜨고 비밀번호가 맞으면 삭제되고 아니면 틀렸다는 문구와 함께 돌아감. 

댓글 삭제시 댓글에 대한 댓글이 함께 삭제되고 댓글에 대한 댓글 개별 삭제시 댓글에 대한 댓글만 삭제
```{.java}
//single-page.jsp

//하단의 댓글 입력 부분
<h2>Leave a Comment</h2>
<div class="table-form">
	<form action="cmtInsert.do" method="post" role="form">
		<div>
			<label>Name<span>*</span></label>
			<input type="text" name="cmt_name">
		</div>
		<div>
			<label>Password<span>*</span></label>
			<input type="password" name="cmt_password">
		</div>
		<div>
			<label>Your Comment<span>*</span></label>
			<textarea name="cmt_content"> </textarea>	
		</div>
		<input type=hidden name="article_number" value="${vo.bno}"> 
	<input type="submit" value="submit">
	</form>
</div>


//BoardActionFactory.java

else if(cmd.equals("/cmtInsert.do")) {
	action=new ChuriCommentInsert("churiHitUpdate.do");
}


//ChuriCommentInsert.jvaa

CommentVO comment=new CommentVO();
comment.setArticle_number(Integer.parseInt(req.getParameter("article_number")));
comment.setCmt_name(req.getParameter("cmt_name"));
comment.setCmt_password(req.getParameter("cmt_password"));
comment.setCmt_content(req.getParameter("cmt_content"));
BoardDAO dao=new BoardDAO();
int result=dao.insertCmt(comment);

if (result<0) {
	path="web/single-page.jsp";
}else {
	path+="?bno="+comment.getArticle_number();
}
return new ActionForward(path, true);


//BoardDAO.java.insertCmt()

```
