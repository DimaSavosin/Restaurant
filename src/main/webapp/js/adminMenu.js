$(document).ready(function () {

    $.ajax({
        url: '/ORIS_war_exploded/menuData',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            const menuContainer = $('#menu-container');
            menuContainer.empty();

            data.forEach(item => {
                const menuItem = `
                    <div class="menu-card" data-id="${item.id}">
                        <img src="${item.imagePath}" alt="${item.name}" class="menu-card__image">
                        <h3 class="menu-card__name">${item.name}</h3>
                        <p class="menu-card__description">${item.description}</p>
                        <p class="menu-card__price">Цена: ${item.price} ₽</p>
                        <button class="menu-card__delete-btn" data-id="${item.id}">Удалить</button>
                    </div>`;
                menuContainer.append(menuItem);
            });
        },
        error: function () {
            alert('Ошибка при загрузке меню.');
        }
    });


    $(document).on('click', '.menu-card__delete-btn', function () {
        const menuId = $(this).data('id');
        $.ajax({
            url: '/ORIS_war_exploded/admin/menuDelete',
            type: 'POST',
            data: { menuId: menuId },
            success: function () {

                $(`.menu-card[data-id="${menuId}"]`).remove();
            },
            error: function () {
                alert('Ошибка при удалении блюда.');
            }
        });
    });
});
