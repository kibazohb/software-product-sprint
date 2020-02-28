//this is the script js file to generate the name.

function getMyName() {
  fetch('/data').then(response => response.text()).then((name) => {
    document.getElementById('name-container').innerText = name;
  });
}