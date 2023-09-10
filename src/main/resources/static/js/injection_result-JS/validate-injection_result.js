document.querySelector("#injectionDateInput").min = new Date().toISOString().split("T")[0];
document.querySelector("#nextInjectionDateInput").min = new Date().toISOString().split("T")[0];

function setMinDateNextInjection(value) {
    let nextInjectionDate =  document.querySelector("#nextInjectionDateInput");
    let minDateNextInjection = new Date();
    let dateToArr = value.split("-");
    minDateNextInjection.setDate(dateToArr[dateToArr.length - 1]);
    minDateNextInjection.setMonth(dateToArr[1]);
    minDateNextInjection.setFullYear(dateToArr[0]);
    minDateNextInjection.setDate(minDateNextInjection.getDate() + 7);
    nextInjectionDate.min = minDateNextInjection.toISOString().split("T")[0];
}
