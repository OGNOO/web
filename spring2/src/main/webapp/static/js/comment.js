/**
 * /post/details.jsp 에 포함.
 */

// document.addEventListener("DOMContentLoaded", () => {
const regex = /^\s+$/;
const $btnToggleComment = document.querySelector("#btnToggleComment");
const $collapseComments = document.querySelector("#collapseComments");
const $btnRegisterComment = document.querySelector("#btnRegisterComment");
const commentModal = new bootstrap.Modal("#commentModal", {
  backdrop: true,
});
const $btnUpdateComment = document.querySelector("#btnUpdateComment");

$btnUpdateComment.addEventListener("click", updateComment);
function updateComment() {
  const id = document.querySelector("#modalCommentId").value;
  const ctext = document.querySelector("#modalCommentText").value;
  if (regex.test(ctext)) {
    console.log("first");
    alert("업데이트할 댓글 내용을 입력하세요.");
    return;
  }
  console.log(ctext);
  const uri = `../api/comment/${id}`;
  axios
    .put(uri, { ctext })
    .then((response) => {
      console.log(response);
      // 댓글 목록 갱신
      getAllComments();

      commentModal.hide();
    })
    .catch((error) => {
      console.log(error);
    });
}

$btnToggleComment.textContent = "댓글 보기";
$btnToggleComment.addEventListener("click", () => {
  if ($btnToggleComment.textContent === "댓글 보기") {
    $btnToggleComment.textContent = "댓글 감추기";
    // 포스트에 달려 있는 모든 댓글 목록 보여줌.
    getAllComments();
  } else {
    $btnToggleComment.textContent = "댓글 보기";
  }
});

// 댓글 등록 이벤트 리스너 콜백(함수):
function registerComment() {
  // 댓글이 달릴 포스트 번호를 찾음.
  // 댓글 내용을 찾으.
  // 댓글 작성자 아이디를 찾음.
  const postId = document.querySelector("#id").value;
  const ctext = document.querySelector("#ctext").value;
  const userName = document.querySelector("#userName").value;

  if (regex.test(ctext) || regex.test(userName)) {
    alert("댓글 내용과 작성자는 반드시 입력하세요.");
    return;
  }

  //   const data = {
  //     postId: postId,
  //     ctext: ctext,
  //     userName: userName,
  //   };
  const data = { postId, ctext, userName };

  // Post 방식의 Ajax 요청을 보냄. 응답 성공/실패 콜백을 등록.
  axios
    .post("../api/comment", data)
    .then((response) => {
      console.log(data);
      console.log(response.data); // RestController 에 보낸 데이터
      if (response.data === 1) {
        alert("댓글 1개 등록 성공");
        document.querySelector("#ctext").value = "";
        document.querySelector("#userName").value = "";
        getAllComments();
      }
    })
    .catch((error) => {
      console.log(error);
    });
}
$btnRegisterComment.addEventListener("click", registerComment);

function makeCommentElements(data) {
  const $comments = document.querySelector("#comments");

  let htmlStr = "";

  for (const comment of data) {
    // 댓글 최종 수정 시간
    const modifiedTime = new Date(comment.modifiedTime).toLocaleString();

    htmlStr += `
    <div class="card card-body mt-1 py-3">
      <div style="font-size:0.85rem;">
        <span style="display:none;">${comment.id}</span>
        <span class="fs-6 fw-bold">${comment.userName}</span>
        <span class="text-secondary">${modifiedTime}</span>
      </div>
      <div class="ctextDiv">
        <span class="fs-4 fw-light">${comment.ctext}</span>
      </div>
      <div class="mt-1">
        <button class="btnDeleteComment btn btn-outline-danger btn-sm" style="font-size: x-small;" data-id="${comment.id}">삭제</button>
        <button class="btnModifyComment btn btn-outline-primary btn-sm" style="font-size: x-small;" data-id="${comment.id}">수정</button>
      </div>
      </div>
    </div>
    `;
  }

  // 작성된 HTML 코드를 div 영역에 삽입.
  $comments.innerHTML = htmlStr;

  // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
  const $$btnDeleteComment = document.querySelectorAll(".btnDeleteComment");
  for (const btn of $$btnDeleteComment) {
    btn.addEventListener("click", deleteComment);
  }
  // TODO: 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
  const $$btnModifyComment = document.querySelectorAll(".btnModifyComment");
  for (const btn of $$btnModifyComment) {
    btn.addEventListener("click", showUpdateComment);
  }
}

function deleteComment(e) {
  // 이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
  const id = e.target.getAttribute("data-id");

  // 삭제 여부 확인
  const res = confirm("댓글을 정말 삭제하시겠습니까?");
  if (!res) {
    return;
  }

  // Ajax 요청을 보낼 URI
  const uri = `../api/comment/${id}`;

  axios
    .delete(uri)
    .then((response) => {
      if (response.data === 1) {
        alert(`댓글 삭제 성공`);
        getAllComments();
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

function showUpdateComment(e) {
  const id = e.target.getAttribute("data-id");
  const uri = `../api/comment/${id}`;

  axios
    .get(uri)
    .then((response) => {
      console.log(response);
      const commentId = response.data.id;
      const commentCtext = response.data.ctext;

      document.querySelector("#modalCommentId").value = commentId;
      document.querySelector("#modalCommentText").value = commentCtext;

      commentModal.show();
    })
    .catch((error) => {
      console.log(error);
    });
  // console.log(e.target.parentNode.parentNode);
  // console.log(e.target.parentNode.parentNode.classList);
  // console.log(e.target.parentNode.parentNode);
  // const a = e.target.parentNode.parentNode
  //   .querySelector(".ctextDiv")
  //   .querySelector("span");
  // console.log(a);
  // e.target.parentNode.parentNode.querySelector(".ctextDiv").removeChild(a);
}

function getAllComments() {
  const postId = document.querySelector("#id").value;
  const uri = `../api/comment/all/${postId}`;

  axios
    .get(uri)
    .then((response) => {
      console.log(response.data);
      // 댓글 목록을 HTML로 작성 -> #comments 영역에 출력.
      makeCommentElements(response.data);
    })
    .catch((error) => {
      console.log(error);
    });
}
// });
