$('#edit-modal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    var user = button.data('user')

    modal.find("#edit-id").val(button.data('user-id'));
    modal.find("#edit-name").val(button.data('user-name'));
    modal.find("#edit-email").val(button.data('user-email'));
    modal.find("#edit-password").val(button.data('user-password'));
    modal.find("#edit-roles").val(button.data('user-roles'));
})
$('#delete-modal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var modal = $(this)
    var user = button.data('user')

    modal.find("#delete-id").val(button.data('user-id'));
    modal.find("#delete-name").val(button.data('user-name'));
    modal.find("#delete-email").val(button.data('user-email'));
    modal.find("#delete-password").val(button.data('user-password'));
    modal.find("#delete-roles").val(button.data('user-roles'));
})