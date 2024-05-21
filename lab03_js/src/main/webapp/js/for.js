const $result = document.querySelector("#result");

let html = "";

for (let i = 1; i < 10; i++) {
  html += `2 x ${i} = ${2 * i}<br/>`;
}
$result.innerHTML += html;
$result.innerHTML += "<hr/>";
html = "";

for (let i = 3; i < 10; i++) {
  for (let j = 1; j < 10; j++) {
    html += `${i} x ${j} = ${j * i}<br/>`;
  }
  html += "<hr/>";
}
$result.innerHTML += html;

html = "";
for (let i = 2; i < 10; i++) {
  for (let j = 1; j < 10; j++) {
    html += `${i} x ${j} = ${i * j} <br/>`;
    if (i == j) {
      break;
    }
  }
  html += "<hr/>";
}
$result.innerHTML += html;
