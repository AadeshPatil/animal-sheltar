document.addEventListener('DOMContentLoaded', function () {
    showPets();
});

function showPets() {
    fetch('http://localhost:8080/pets/getall')
        .then(response => response.json())
        .then(data => {
            const petsDiv = document.getElementById('petsdiv');

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
                name.textContent =" Name: "+ pet.name + ", Age: "+pet.age;
                div.appendChild(name);

                const button = document.createElement('a');
                button.classList.add('inline-flex', 'text-white', 'bg-blue-500', 'border-0', 'mx-1', 'px-6', 'focus:outline-none', 'hover:bg-gray-600', 'rounded', 'text-lg');
                button.setAttribute('href', 'meetingform.html');
                button.textContent = 'Adopt';
                div.appendChild(button);

                petsDiv.appendChild(div);
            });
        })
        .catch(error => console.error(error));
}
