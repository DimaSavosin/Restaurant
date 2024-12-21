$(document).ready(function () {
    $.ajax({
        url: '/ORIS_war_exploded/menuData',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            const menuContainer = $('#menu-container');
            data.forEach(item => {
                const menuItem = `
                    <div class="menu-item">
                        <img src="${item.imagePath}" alt="${item.name}">
                        <h3>${item.name}</h3>
                        <p>${item.description}</p>
                        <p>Цена: ${item.price} ₽</p>
                    </div>`;
                menuContainer.append(menuItem);
            });
        },
        error: function () {
            alert('Ошибка при загрузке меню.');
        }
    });
});
