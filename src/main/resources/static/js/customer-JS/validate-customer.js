document.querySelector("#codeCaptcha").defaultValue = randomCaptcha() ;
function randomCaptcha() {
  let captcha = Math.floor(100000 + Math.random() * 900000);
  return captcha;
}

function reloadCaptcha() {
  document.querySelector("#codeCaptcha").value = randomCaptcha();
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

function checkIdCard(value) {
  let idCardInvalid = document.querySelector("#idCardInvalid");

  if (value.length < 5 || value.length > 20) {
    idCardInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    idCardInvalid.style.setProperty("opacity", 0);
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

function checkRePassword(value) {
  let rePasswordInvalid = document.querySelector("#rePasswordInvalid");
  let passwordValue = document.querySelector("#passwordInput").value;
  if (passwordValue != value) {
    rePasswordInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    rePasswordInvalid.style.setProperty("opacity", 0);
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

function checkCaptcha(value) {
  let captchaInvalid = document.querySelector("#captchaInvalid");
  let codeCaptcha = document.querySelector("#codeCaptcha").value;
  if (codeCaptcha != value) {
    captchaInvalid.style.setProperty("opacity", 1);
    return false;
  } else {
    captchaInvalid.style.setProperty("opacity", 0);
    return true;
  }
}


function validateCustomer() {
  let name = document.querySelector("#nameInput").value;
  let address = document.querySelector("#addressInput").value;
  let idCard = document.querySelector("#idCardInput").value;

  let username = document.querySelector("#usernameInput").value;
  let password = document.querySelector("#passwordInput").value;
  let rePassword = document.querySelector("#rePasswordInput").value;
  let email = document.querySelector("#emailInput").value;
  let phoneNumber = document.querySelector("#phoneInput").value;
  let captcha = document.querySelector("#captchaInput").value;


  if (
    checkName(name) &&  
    checkAddress(address) &&
    checkIdCard(idCard) &&
    checkUsername(username) &&
    checkPassword(password) &&
    checkRePassword(rePassword) &&
    checkPhoneNumber(phoneNumber) &&
    checkEmail(email) &&
    checkCaptcha(captcha)
   
  ) {
    return true;
  } else {
    alert("You must input all required fields in correct format !!")
    return false;
  }
}


