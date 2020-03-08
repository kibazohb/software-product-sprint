
//this is the script js file to get sample data from server

/** Fetches all comments */

function getMyComments() {
    fetch("/data").then(response => response.json()).then((comments) => {
        const commentsListElement = document.getElementById('liElement-container');
        commentsListElement.innerHTML = '';
        for (const element of comments) {
            commentsListElement.appendChild(createListElement(element.content));
        }
    });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

