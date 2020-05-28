const texto = document.getElementById('texto');
const checkboxMaiuscula = document.getElementById('checkbox-maiuscula');
const checkboxMinuscula = document.getElementById('checkbox-minuscula');

checkboxMaiuscula.addEventListener('click', () => {
  checkboxMinuscula.checked = false;
  texto.value = texto.value.toUpperCase();
});

checkboxMinuscula.addEventListener('click', () => {
  checkboxMaiuscula.checked = false;
  texto.value = texto.value.toLowerCase();
});
