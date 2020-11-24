let imageInput = document.querySelector('input[type="file"]');
let submitButton = document.querySelector('input[type="submit"]');
// Hámark 3MB
const imageMaxSize = 3000000;

imageInput.addEventListener('change', () => {
    checkImageSize();
    checkImageExtension();
});

function checkImageSize() {
    let selectedFile = imageInput.files[0];
    // Athugum hvort myndin er stærri en hámrkið
    if(selectedFile != null && selectedFile.size > imageMaxSize) {
        // Látum vita að mynd er invalid
        imageInput.classList.add("is-invalid");
        // Gerum takkan óvirkan
        submitButton.disabled = true;
    } else {
        // Fjarlægjum tilkynningu um að mynd er invalid
        imageInput.classList.remove("is-invalid");
        // Gerum takkan aftur virkan
        submitButton.disabled = false;
    }
}

function checkImageExtension() {
    let selectedFile = imageInput.files[0];
    // Athugum hvort myndin er með extension .jpg, .jpeg, .gif eða .png
    let ex = ["image/jpg", "image/jpeg", "image/gif", "image/png"];
    if(selectedFile == null || ex.includes(selectedFile.type)) {
        // Fjarlægjum tilkynningu um að mynd er invalid
        imageInput.classList.remove("is-invalid");
        // Gerum takkan aftur virkan
        submitButton.disabled = false;
    } else {
        // Látum vita að mynd er invalid
        imageInput.classList.add("is-invalid");
        // Gerum takkan óvirkan
        submitButton.disabled = true;
    }
}