document.addEventListener("DOMContentLoaded", function () {
  const bsCollapse = new bootstrap.Collapse("div#collapseComments", {
    toggle: false,
  });

  const $btnToggle = document.querySelector("#btnToggle");
  $btnToggle.addEventListener("click", () => {
    bsCollapse.toggle();

    const toggle = $btnToggle.getAttribute("data-toggle");
    if (toggle === "collapse") {
      const $divComments = document.querySelector("#divComments");
      if ($divComments.childNodes.length === 0) {
        getAllComments(0);
      }
      $btnToggle.textContent = "댓글 감추기";
      $btnToggle.setAttribute("data-toggle", "unfold");
    } else {
      $btnToggle.textContent = "댓글 보기";
      $btnToggle.setAttribute("data-toggle", "collapse");
    }
  });
});

const $btnRegisterComment = document.querySelector("#btnRegisterComment");
$btnRegisterComment.addEventListener("click", () => {
  console.log("댓글 등록 클릭");
  registerComment();
  $btnMoreComments.setAttribute("data-currentPageNo", 0);
});

const $btnMoreComments = document.querySelector("#btnMoreComments");
$btnMoreComments.addEventListener("click", () => {
  console.log("더보기 클릭");
  let currentPageNo = $btnMoreComments.getAttribute("data-currentPageNo");
  currentPageNo++;
  $btnMoreComments.setAttribute("data-currentPageNo", currentPageNo);
  getAllComments(currentPageNo);
});

function registerComment() {
  const postId = document.querySelector("#postId").textContent;
  const ctext = document.querySelector("#commentText").value;
  const writer = document.querySelector("#commentWriter").textContent;

  if (ctext.trim() === "") {
    alert("댓글 내용을 입력하세요");
    return;
  }

  const data = {
    postId,
    ctext,
    writer,
  };

  axios
    .post("/api/comment", data)
    .then((response) => {
      alert("댓글이 등록 되었습니다.");
      document.querySelector("#commentText").value = "";
      getAllComments(0);
    })
    .catch((error) => {
      console.log(error);
    });
}
function getAllComments(pageNo) {
  // 댓글이 달린 포스트 아이디
  const postId = document.querySelector("#postId").textContent;
  const uri = `/api/comment/all/${postId}?p=${pageNo}`;

  axios
    .get(uri)
    .then((response) => {
      if (!response.data.empty) {
        currentPageNo = response.number;
        makeCommentElements(response.data.content, response.data.number);
      } else {
        document.querySelector("#divComments").innerHTML = "";
      }
      if (response.data.last) {
        document.querySelector("#btnMoreComments").style.display = "none";
      } else {
        document.querySelector("#btnMoreComments").style.display = "";
      }
    })
    .catch((error) => {
      console.log(error);
    });
}
function makeCommentElements(content, pageNo) {
  const $divComments = document.querySelector("#divComments");
  let htmlStr = "";
  content.forEach((e) => {
    console.log(e);
    htmlStr += `
    <div class="card text-white bg-dark mb-2" style="max-width: 100%">
      <div class="card-header d-flex justify-content-between">
        <span class="fw-bold">${
          e.writer
        }</span><span style="color: #cbcbcb;">${formatDateTime(
      e.modifiedTime
    )}</span>
      </div>
      <div class="card-body">
        <div class="card-text">${e.ctext}</div>
      </div>
      <div class="card-footer px-3 pt-0 border-top-0 d-flex justify-content-end">
        ${checkCommentWriter(e.writer, e.id)}
      </div>
    </div>
    `;
  });
  if (pageNo === 0) {
    $divComments.innerHTML = htmlStr;
  } else {
    $divComments.innerHTML += htmlStr;
  }
}

function formatDateTime(dateTimeString) {
  const now = new Date();
  const data = new Date(dateTimeString);
  const diff = now - data; // 밀리초 단위 차이 계산
  console.log(diff);
  const seconds = Math.floor(diff / 1000);
  const minutes = Math.floor(seconds / 60);
  const hours = Math.floor(minutes / 60);
  const days = Math.floor(hours / 24);
  const months = Math.floor(days / 30);
  const years = Math.floor(days / 365);

  if (years > 0) {
    return years + "년 전";
  } else if (months > 0) {
    return months + "달 전";
  } else if (days > 0) {
    return days + "일 전";
  } else if (hours > 0) {
    return hours + "시간 전";
  } else if (minutes > 0) {
    return minutes + "분 전";
  } else {
    return seconds + "초 전";
  }
}

function clickCommentDelBtn(e) {
  const uri = e.target.getAttribute("data-uri");
  axios
    .delete(uri)
    .then((response) => {
      if (response.data === 1) {
        getAllComments(0);
        $btnMoreComments.setAttribute("data-currentPageNo", 0);
      }
    })
    .catch((error) => {
      console.log(error);
    });
}

function clickCommentUpdateBtn(e, cid) {
  if (e.target.textContent === "수정") {
    e.target.parentNode.parentNode.querySelector(
      ".card-text"
    ).innerHTML = `<div class="row">
      <div class="col-10 pe-0">
        <textarea class="px-2" oninput="asd(event)" row="5" id="targetText" style="width:100%; height:100%; background: #494949;
        color: white; border-radius: 3px;">${
          e.target.parentNode.parentNode.querySelector(".card-text").textContent
        }</textarea>
      </div>
      <div class="col-2">
        <button id="updateCommentBtn" class="btn btn-success" onclick="realUpdateBtn(event,${cid})" style="width:100%; height:95%;">수정</button>
      </div>
    </div>`;
    e.target.textContent = "취소";
  } else {
    e.target.parentNode.parentNode.querySelector(".card-text").innerHTML = `${
      e.target.parentNode.parentNode.querySelector("#targetText").textContent
    }`;
    e.target.textContent = "수정";
  }
}

function realUpdateBtn(e, cid) {
  const update = confirm("댓 진짜 수정할거임?");
  if (update == 1) {
    const targetTextValue =
      e.target.parentNode.parentNode.querySelector("#targetText").value;
    const uri = `/api/comment/update/${cid}?ctext=${targetTextValue}`;
    axios
      .put(uri)
      .then((response) => {
        if (response.data === 1) {
          getAllComments(0);
          $btnMoreComments.setAttribute("data-currentPageNo", 0);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }
}

function asd(e) {
  const commentTextMaxLen = 500;
  if (e.target.value.length > commentTextMaxLen) {
    alert(`댓글은 ${commentTextMaxLen}자 까지만 입력가능합니다.`);
    e.target.value = e.target.value.substring(0, commentTextMaxLen);
  }
}
function checkCommentWriter(writer, cid) {
  const loginUser = loginUserId;
  if (writer === loginUser) {
    return `<button id="commentDelBtn" class="btn py-0 px-2" style="color: gray" data-uri="/api/comment/delete/${cid}" onclick="clickCommentDelBtn(event)">삭제</button>
    <button class="btn py-0 px-2" style="color: gray" onclick="clickCommentUpdateBtn(event,${cid})">수정</button>`;
  } else {
    return "";
  }
}
