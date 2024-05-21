$weekday = document.querySelector("#weekday");
$btn = document.querySelector("#btn");
$result = document.querySelector("#result");

function selectListener() {
  const value = $weekday.value;
  switch (value) {
    case "mon":
    case "tue":
    case "wed":
    case "thu":
    case "fri":
      $result.textContent = "학원 출석";
      break;
    case "sat":
    case "sun":
      $result.textContent = "놀러감";
      break;
    default:
      $result.textContent = "몰라요";
  }
}
$btn.addEventListener("click", selectListener);
