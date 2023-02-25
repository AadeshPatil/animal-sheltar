document.addEventListener('DOMContentLoaded', function () {
    showPets();

});

let petsList;
let pet;
function showPets() {
    fetch('http://localhost:8080/pets/getall')
        .then(response => response.json())
        .then(data => {
            const petsDiv = document.getElementById('petsdiv');
            petsList = data;
            data.forEach(pet => {
                const div = document.createElement('div');
                div.classList.add('lg:w-1/4', 'md:w-1/2', 'p-4', 'w-full');

                const a = document.createElement('a');
                a.classList.add('block', 'relative', 'h-48', 'rounded', 'overflow-hidden');

                const img = document.createElement('img');
                img.classList.add('object-cover', 'object-center', 'w-full', 'h-full', 'block');
                img.setAttribute('src', pet.images);

                a.appendChild(img);
                div.appendChild(a);

                const category = document.createElement('h3');
                category.classList.add('text-gray-500', 'text-xs', 'tracking-widest', 'title-font', 'mb-1');
                category.textContent = pet.species;
                div.appendChild(category);

                const name = document.createElement('h2');
                name.classList.add('text-gray-900', 'title-font', 'text-lg', 'font-medium');
                name.textContent = " Name: " + pet.name + ", Age: " + pet.age;
                div.appendChild(name);

                const button = document.createElement('button');
                button.setAttribute('id', 'update-pet-btn');
                button.setAttribute('onclick', 'openModel(' + pet.id + ")");

                button.classList.add('bg-blue-500', 'hover:bg-blue-700', 'text-white', 'font-bold', 'py-2', 'px-4', 'rounded');
                button.textContent = ' Update Pet ';
                div.appendChild(button);

                petsDiv.appendChild(div);


            });


        })
        .catch(error => console.error(error));
}

// Get the update pet modal and the button that opens it
function openModel(id) {
    const updatePetModal = document.getElementById('update-pet-modal');

    pet = findPetById(id); // find the pet with the matching id

    let nameInput = updatePetModal.querySelector('#name');
    let ageInput = updatePetModal.querySelector('#age');
    let cate = updatePetModal.querySelector('#categary');
    let avaliable = updatePetModal.querySelector('#available');

    // Populate the form fields with the selected pet's details
    nameInput.value = pet.name;
    ageInput.value = pet.age;
    cate.value = pet.species;
    avaliable.checked = !pet.adopted;
    updatePetModal.classList.remove('hidden');



}

function findPetById(id) {
    return petsList.find(pet => pet.id === id);
}

function dismissModal() {
    const updatePetModal = document.getElementById('update-pet-modal');
    updatePetModal.classList.add('hidden');
}
function updatePet() {
    const updatePetModal = document.getElementById('update-pet-modal');
    let nameInput = updatePetModal.querySelector('#name');
    let ageInput = updatePetModal.querySelector('#age');
    let cate = updatePetModal.querySelector('#categary');
    let avaliable = updatePetModal.querySelector('#available');

    let obj = {
        "id": pet.id,
        "name": nameInput.value,
        "age": ageInput.value,
        "species": cate.value,
        "images": pet.images,
        "adopted": !avaliable.checked
    }

    fetch(`http://localhost:8080/pets/` + pet.id, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to update pet.');
            }
            return response.json();
        })
        .then(data => {
            updatePetModal.classList.add('hidden');
            window.location.href = "admin-page.html";
            alert('Pet updated successfully');
        })
        .catch(error => {
            console.error('Error updating pet:', error);
            // Handle error case here

        });
}

function addPet(){
    let nameInput = document.getElementById('petName');
    let ageInput = document.getElementById('petAge');
    let cate = document.getElementById('petCategory');
    let avaliable = document.getElementById('petAvailability');
    let imageURL = document.getElementById('imageURL');

        let obj = {
        "id": '',
        "name": nameInput.value,
        "age": ageInput.value,
        "species": cate.value,
        "images": imageURL.value,
        "adopted": !avaliable.checked
    }

    fetch(`http://localhost:8080/pets/addpet`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(obj)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to update pet.');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById("addPetModal").classList.add('hidden');
            window.location.href = "admin-page.html";
            alert('Pet Added successfully');
        })
        .catch(error => {
            console.error('Error updating pet:', error);
            // Handle error case here

        });
}