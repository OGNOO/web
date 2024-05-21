const $output = document.querySelector("#output");
const $list = document.querySelector("#list");
const arr = [1, 2, 30, 40, -5];

let html = "";
for (let i = 0; i < arr.length; i++) {
  if (i === arr.length - 1) {
    html += `${arr[i]} `;
  } else {
    html += `${arr[i]}, `;
  }
}
$output.innerHTML += html + "<br/>";

html = "";
for (let val of arr) {
  html += `${val}, `;
}
$output.innerHTML += html + "<br/>";

html = "";
for (let idx in arr) {
  html += `index:${idx}, val:${arr[idx]}, `;
}
$output.innerHTML += html + "<br/>";

let sum = 0;
for (let i = 0; i < arr.length; i++) {
  sum += arr[i];
}
let avg = sum / arr.length;
$output.innerHTML += "arr 합계: " + sum + "<br/>";
$output.innerHTML += "arr 평균: " + avg + "<br/>";

html = "";

const drama = ["삼식이 삼촌", "동조자", "삼체"];
// for (let i = 0; i < drama.length; i++) {
//   const $li = document.createElement("li");
//   $li.textContent = drama[i];
//   $list.appendChild($li);
// }

drama.forEach((d) => {
  const $li = document.createElement("li");
  $li.textContent = d;
  $list.appendChild($li);
});
