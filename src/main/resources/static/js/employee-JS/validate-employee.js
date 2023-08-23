function checkId(value) {
  let idInvalid = document.querySelector("#idInvalid");

  if ((value.length < 5) || (value.length > 12)) {
    idInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    idInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkName(value) {
  let nameInvalid = document.querySelector("#nameInvalid");

  if (value.length < 5 || value.length > 100) {
    nameInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    idInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkPhoneNumber(value) {
  let phoneValidate = /^0\d/;
  let phoneInvalid = document.querySelector("#phoneInvalid");
  if (!value.match(phoneValidate)) {
    phoneInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    phoneInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkAddress(value) {
  let addressInvalid = document.querySelector("#addressInvalid");

  if (value.length < 5 || value.length > 100) {
    addressInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    addressInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkEmail(value) {
  let emailInvalid = document.querySelector("#emailInvalid");

  if (value.length > 100) {
    emailInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    emailInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkWorkingPlace(value) {
  let workingPlaceInvalid = document.querySelector("#workingPlaceInvalid");

  if (value.length > 100) {
    workingPlaceInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    workingPlaceInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkPosition(value) {
  let positionInvalid = document.querySelector("#positionInvalid");

  if (value.length > 100) {
    positionInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    positionInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkUsername(value) {
  let usernameInvalid = document.querySelector("#usernameInvalid");

  if ((value.length > 30) || (value.length < 5)) {
    usernameInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    usernameInvalid.style.setProperty("opacity", 0);
    return true;
  }
}

function checkPassword(value) {
  let passwordInvalid = document.querySelector("#passwordInvalid");

  if ((value.length > 30) || (value.length < 10)) {
    passwordInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    passwordInvalid.style.setProperty("opacity", 0);
    return true;
  }
}


function validateEmployee() {
  let employeeInvalid = document.querySelector("#employeeInvalid");

  let id = document.querySelector("#idInput").value;
  let name = document.querySelector("#nameInput").value;
  let phoneNumber = document.querySelector("#phoneInput").value;
  let address = document.querySelector("#addressInput").value;
  let email = document.querySelector("#emailInput").value;
  let workingPlace = document.querySelector("#workPlaceInput").value;
  let position = document.querySelector("#positionInput").value;
  let username = document.querySelector("#usernameInput").value;
  let password = document.querySelector("#passwordInput").value;

  if (
    checkId(id) &&
    checkName(name) &&
    checkPhoneNumber(phoneNumber) &&
    checkAddress(address) &&
    checkEmail(email) &&
    checkWorkingPlace(workingPlace) &&
    checkPosition(position) &&
    checkUsername(username) &&
    checkPassword(password)
  ) {
    employeeInvalid.style.setProperty("opacity", 0);
    return true;
  } else {
    employeeInvalid.style.setProperty("opacity", 1);
    return false;
  }
}
