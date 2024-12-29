$(document).ready(function () {
    // Получение данных меню
    $.ajax({
        url: '/ORIS_war_exploded/menuData',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            const menuContainer = $('#menu-container');
            menuContainer.empty(); // Очищаем контейнер перед добавлением новых элементов

            data.forEach(item => {
                const menuItem = `
                    <div class="menu-item" data-id="${item.id}">
                        <img src="${item.imagePath}" alt="${item.name}">
                        <h3>${item.name}</h3>
                        <p>${item.description}</p>
                        <p>Цена: ${item.price} ₽</p>
                        <button class="delete-btn" data-id="${item.id}">Удалить</button>
                    </div>`;
                menuContainer.append(menuItem);
            });
        },
        error: function () {
            alert('Ошибка при загрузке меню.');
        }
    });

    // Обработчик удаления блюда
    $(document).on('click', '.delete-btn', function () {
        const menuId = $(this).data('id'); // $(this) это текущий HTML-элемент, на который пользователь кликнул.
        //data('id') возвращает значение у аттрибута data-id
            $.ajax({
                url: '/ORIS_war_exploded/admin/deleteDish',
                type: 'POST',
                data: { menuId: menuId },
                success: function () {
                    $(`.menu-item[data-id="${menuId}"]`).remove();
                },
                error: function () {
                    alert('Ошибка при удалении блюда.');
                }
            });

    });
});
