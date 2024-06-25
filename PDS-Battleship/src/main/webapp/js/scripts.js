const uniqueTabId = "uniqueTabIdentifier";
const uniqueTabValue = "open";

function checkIfTabAlreadyOpen() {
    if (localStorage.getItem(uniqueTabId) === uniqueTabValue) {
        alert("Another instance of this application is already open in another tab.");
        window.location.href = "error.jsp"; // or any other logic to handle this
    } else {
        localStorage.setItem(uniqueTabId, uniqueTabValue);
    }
}

function handleTabClose() {
    localStorage.removeItem(uniqueTabId);
}

window.onload = checkIfTabAlreadyOpen;
window.onbeforeunload = handleTabClose;