function patikrintiSlaptazodius(form) {
    const slaptazodis = form.slaptazodis.value;
    const slaptazodis1 = form.slaptazodis1.value;

    if (slaptazodis != slaptazodis1) {
        alert("Slaptažodžiai nesutampa");
        return false;
    } else {
        return true;
    }
}