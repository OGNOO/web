const $list = document.querySelector("#list");
const $tableBody = document.querySelector("#tableBody");
let n = 1;

// let html = "";
// while (n <= 5) {
//   html += `<li>Item ${n}</li>`;
//   n++;
// }
// $list.innerHTML = html;

while (n <= 5) {
  const $li = document.createElement("li");
  $li.textContent = `Item ${n}`;
  $list.append($li);
  console.log(n);
  n++;
}

const title = ["a", "b", "c", "d", "e"];

let startNumber = 1;
let columIndex = 1;
let maxRow = 5;
let maxColum = 2;

while (startNumber <= maxRow) {
  const $tr = document.createElement("tr");

  while (columIndex <= maxColum) {
    const $td = document.createElement("td");
    if (columIndex == 1) {
      $td.textContent = startNumber;
    } else if (columIndex == 2) {
      $td.textContent = title[startNumber - 1];
    }
    $tr.appendChild($td);
    columIndex++;
  }

  columIndex = 1;
  $tableBody.appendChild($tr);
  startNumber++;
}
