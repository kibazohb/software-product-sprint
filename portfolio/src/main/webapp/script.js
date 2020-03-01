
//this is the script js file to get sample data from server

function getMyName() {
  fetch('/data').then(response => response.text()).then((name) => {
    document.getElementById('name-container').innerText = name;
  });
}

/** Fetches all facts */

function getMyFacts() {
    fetch("/data").then(response => response.json()).then((facts) => {
        console.log(facts);
        const factsListElement = document.getElementById('liElement-container');
        factsListElement.innerHTML = '';
        for (let i = 0; i < facts.length; i++){
            factsListElement.appendChild(createListElement(facts[i]))
        }
    });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

