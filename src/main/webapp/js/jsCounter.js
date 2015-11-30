var count = 20;
function inc(){
    var divCounter = document.body.firstElementChild.firstElementChild.firstElementChild.firstElementChild.lastElementChild;
    divCounter.innerHTML = count;
    count++;
};
setInterval(inc,3000)