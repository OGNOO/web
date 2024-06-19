/**
 * /user/signup.jsp 파일에 포함.
 */

// document.addEventListener("DOMContentLoaded", () => {
let userIdChecked = false; // 사용자 아이디 중복 체크 결과. true: 사용할 수 있는 아이디.
let passwordChecked = false; // 비밀번호 필드 작성 여부 체크.
let emailChecked = false; // 이메일 필드 작성 여부 체크.

const inputUserId = document.querySelector("#userId");
inputUserId.addEventListener("input", checkUserId);

const inputPassword = document.querySelector("#pw");
inputPassword.addEventListener("change", checkPassword);

const inputEmail = document.querySelector("#email");
inputEmail.addEventListener("change", checkEmail);

/* -------------------- 함수 선언 -------------------- */

// 회원 가입 버튼 활성화/비활성화
function changeButtonState() {
  const btnSignUp = document.querySelector("#btnSubmit");

  if (userIdChecked && passwordChecked && emailChecked) {
    // 버튼의 class 속성 값들 중 'disabled'를 제거.
    btnSignUp.classList.remove("disabled");
  } else {
    // 버튼의 class 속성에 'disabled'를 추가.
    btnSignUp.classList.add("disabled");
  }
}

// userId 입력 필드의 change 이벤트 리스너
function checkUserId(event) {
  // 중복 아이디 체크 Ajax 요청을 보내고, 응답을 받았을 때 처리.
  const userId = event.target.value;
  console.log(userId);

  const uri = `./checkId?userId=${userId}`;
  axios
    .get(uri)
    .then((response) => {
      const $checkUserIdResult = document.querySelector("#checkUserIdResult");
      if (response.data === "Y") {
        userIdChecked = true;
        $checkUserIdResult.classList.remove("d-none");
        $checkUserIdResult.innerHTML = "멋진 아이디입니다.";
        $checkUserIdResult.classList.add("text-success");
        $checkUserIdResult.classList.remove("text-danger");
      } else {
        userIdChecked = false;
        $checkUserIdResult.innerHTML = "사용할 수 없는 아이디입니다.";
        $checkUserIdResult.classList.remove("d-none");
        $checkUserIdResult.classList.add("text-danger");
        $checkUserIdResult.classList.remove("text-success");
      }
      changeButtonState();
    })
    .catch((error) => {
      console.log(error);
    });
}

// 비밀번호 입력 필드의 change 이벤트 리스너
function checkPassword(event) {
  // input#password 비어 있는 지를 체크
  if (event.target.value === "") {
    passwordChecked = false;
  } else {
    passwordChecked = true;
  }
  changeButtonState();
}

// 이메일 입력 필드의 change 이벤트 리스너
function checkEmail(event) {
  // input#email 비어 있는 지를 체크
  if (event.target.value === "") {
    emailChecked = false;
  } else {
    emailChecked = true;
  }
  changeButtonState();
}
// });
