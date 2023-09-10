
document.querySelector("#injectionDateInput").min = new Date().toISOString().split("T")[0];
document.querySelector("#nextInjectionDateInput").min = new Date().toISOString().split("T")[0];


function checkCustomerId(value) {
    let customerIDInvalid = document.querySelector("#customerIDInvalid");
    let z1 = /^[0-9]*$/;
    if (!value.match(z1) || value.length < 1) {
        customerIDInvalid.style.setProperty("opacity", 1);
        return false;
    } else {
        customerIDInvalid.style.setProperty("opacity", 0);
        return true;
    }
}

function checkInjectionResultId(value) {
    let injectionResultIdInvalid = document.querySelector("#injectionResultIdInvalid");
    let z1 = /^[0-9]*$/;
    if (!value.match(z1) || value.length < 5) {
        injectionResultIdInvalid.style.setProperty("opacity", 1);
        return false;
    } else {
        injectionResultIdInvalid.style.setProperty("opacity", 0);
        return true;
    }
}

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

function validateInjectionResult() {
    let customerID =            document.querySelector("#idCusInput").value;
    let injectionResultID =     document.querySelector("#injectionResultIdInput").value;

    if(checkCustomerId(customerID) && checkInjectionResultId(injectionResultID)) {
        return true;
    } else {
        alert("You must input all required fields in correct format !!")
        return false;
    }
}
