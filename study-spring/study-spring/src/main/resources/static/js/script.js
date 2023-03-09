const clock = document.querySelector('.clock');
function getTime() {
    let today = new Date();
    let hours = today.getHours();
    let minutes = today.getMinutes();
    let seconds = today.getSeconds();
    clock.innerHTML = `${hours<10 ? `0${hours}`:hours}:${minutes<10 ? `0${minutes}`:minutes}:${seconds<10 ? `0${seconds}`:seconds}`
}
function init(){
    setInterval(getTime, 1000);
}
init();
function cnt() {
    var s = parseInt($("#cnt").val())
    s += 1
    $("#cnt").val(s)
}

//if(data != null) {
//   alert(data);
//}


