
function checkVaccineId(value) {
  let vaccineId = document.querySelector("#vaccineIdInvalid");

  if (value.length < 5 || value.length > 100) {
    vaccineId.style.setProperty("opacity", 1);
    return false;
  } else {
    vaccineId.style.setProperty("opacity", 0);
    return true;
  }
}

function checkVaccineName(value) {
  let vaccineName = document.querySelector("#vaccineNameInvalid");

  if (value.length < 5 || value.length > 20) {
    vaccineName.style.setProperty("opacity", 1);
    return false;
  } else {
    vaccineName.style.setProperty("opacity", 0);
    return true;
  }
}

function checkNumberOfInject(value) {
  let numberOfInject = document.querySelector("#numberOfInjectInvalid");

  if ((value.length > 30) || (value.length < 5)) {
    numberOfInject.style.setProperty("opacity", 1);
    return false;
  } else {
    numberOfInject.style.setProperty("opacity", 0);
    return true;
  }
}

function checkUsage(value) {
  let usage = document.querySelector("#usageInvalid");

  if ((value.length > 30) || (value.length < 10)) {
    usage.style.setProperty("opacity", 1);
    return false;
  } else {
    usage.style.setProperty("opacity", 0);
    return true;
  }
}

function checkIndication(value) {
  let indication = document.querySelector("#indicationInputInvalid");
  
 if ((value.length > 30) || (value.length < 10)) {
    indication.style.setProperty("opacity", 1);
    return false;
  } else {
    indication.style.setProperty("opacity", 0);
    return true;
  }
}

function checkContraindication(value) {
  let contraindication = document.querySelector("#contraindicationInvalid");
  if ((value.length > 30) || (value.length < 10)) {
    contraindication.style.setProperty("opacity", 1);
    return false;
  } else {
    contraindication.style.setProperty("opacity", 0);
    return true;
  }
}

function checkTimeBeginnig(value) {
  let timeBegin = document.querySelector("#timeBeginningInvalid");

  if ((value.length > 30) || (value.length < 10)) {
    timeBegin.style.setProperty("opacity", 1);
    return false;
  } else {
    timeBegin.style.setProperty("opacity", 0);
    return true;
  }
}

function checkTimeEnd(value) {
  let timeEnd = document.querySelector("#timeEndInvalid");

  if ((value.length > 30) || (value.length < 10)) {
    timeEnd.style.setProperty("opacity", 1);
    return false;
  } else {
    timeEnd.style.setProperty("opacity", 0);
    return true;
  }
}

function checkOrigin(value) {
  let origin = document.querySelector("#originInvalid");

  if ((value.length > 30) || (value.length < 10)) {
    origin.style.setProperty("opacity", 1);
    return false;
  } else {
    origin.style.setProperty("opacity", 0);
    return true;
  }
}


function validateVaccine() {
  let vaccindId = document.querySelector("#vaccineIdInput").value;
  let VaccineName = document.querySelector("#vaccineNameInput").value;
  let numberOfInject = document.querySelector("#numberOfInjectInput").value;

  let usage = document.querySelector("#usageInput").value;
  let idication = document.querySelector("#indicationInput").value;
  let contraindication = document.querySelector("#contraindicationInput").value;
  let timeBegin = document.querySelector("#timeBeginningInput").value;
  let timeEnd = document.querySelector("#timeEndInput").value;
  let origin = document.querySelector("#originInput").value;


  if (
    checkVaccineId(vaccindId) &&  
    checkVaccineName(VaccineName) &&
    checkNumberOfInject(numberOfInject) &&
    checkUsage(usage) &&
    checkIndication(idication) &&
    checkContraindication(contraindication) &&
    checkTimeBeginnig(timeBegin) &&
    checkTimeEnd(timeEnd) &&
    checkOrigin(origin)
   
  ) {
    return true;
  } else {
    alert("You must input all required fields in correct format !!")
    return false;
  }
}