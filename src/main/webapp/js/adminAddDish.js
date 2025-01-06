$(document).ready(function () {
    $('#addDishForm').on('submit', function (e) {
        e.preventDefault();

        const formData = new FormData(this);

        $.ajax({
            url: '/ORIS_war_exploded/admin/addDish',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {

                if (response.status === 'success') {
                    $('#addDishStatus').text(response.message).css('color', 'green');
                    $('#addDishForm')[0].reset();
                } else {
                    $('#addDishStatus').text(response.message).css('color', 'red');
                }
            },
            error: function () {
                alert('Ошибка при добавлении блюда.');
            }
        });
    });
});
