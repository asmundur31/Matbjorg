let imageInput = document.getElementById("picture");
let submitButton = document.getElementById("submit-button");
// Hámark 3MB
const imageMaxSize = 3000000;

imageInput.addEventListener('change', () => {
    checkImage();
});

function checkImage() {
    let selectedFile = imageInput.files[0];
    // Athugum hvort myndin er með extension .jpg, .jpeg, .gif eða .png
    let ex = ["image/jpg", "image/jpeg", "image/gif", "image/png"];
    if(selectedFile != null && ex.includes(selectedFile.type) && selectedFile.size <= imageMaxSize) {
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