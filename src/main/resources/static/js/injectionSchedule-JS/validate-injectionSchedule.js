function checkInjectionScheduleId(value) {
    let vaccineId = document.querySelector("#InjectionScheduleIdInvalid");
    let z1 = /^[0-9]*$/;
    if (!value.match(z1) || value.length < 5) {
        vaccineId.style.setProperty("opacity", 1);
        return false;
    } else {
        vaccineId.style.setProperty("opacity", 0);
        return true;
    }
}