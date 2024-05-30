/**
 *  /post/modify.jsp 에 포함.
 */

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
// document.addEventListener("DOMContentLoaded", () => {
const $modifyForm = document.querySelector("#modifyForm");
const $id = document.querySelector("#id");
const $title = document.querySelector("#title");
const $content = document.querySelector("#content");
const $btnDelete = document.querySelector("#btnDelete");
const $btnUpdate = document.querySelector("#btnUpdate");
const regex = /^\s+$/;

$btnDelete.addEventListener("click", () => {
  const result = confirm("정말 삭제할까요?");
  if (result) {
    location.href = `delete?id=${$id.value}`;
  }
});

$btnUpdate.addEventListener("click", () => {
  if (regex.test($title.value) || regex.test($content.value)) {
    alert("제목과 내용은 반드시 입력해야 합니다.");
    return;
  }

  const result = confirm("정말 업데이트할까요?");
  if (result) {
    $modifyForm.action = "update";
    $modifyForm.method = "post";
    $modifyForm.submit();
  }
});
// });
