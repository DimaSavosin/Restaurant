$(document).ready(function () {
    $.ajax({
        url: '/ORIS_war_exploded/menuData',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            const menuContainer = $('#menu-container');
            data.forEach(item => {
                const menuItem = `
                    <div class="menu-card">
                        <img class="menu-card__image" src="${item.imagePath}" alt="${item.name}">
                        <h3 class="menu-card__name">${item.name}</h3>
                        <p class="menu-card__description">${item.description}</p>
                        <p class="menu-card__price">Цена: ${item.price} ₽</p>
                    </div>`;
                menuContainer.append(menuItem);
            });
        },
        error: function () {
            alert('Ошибка при загрузке меню.');
        }
    });
});
