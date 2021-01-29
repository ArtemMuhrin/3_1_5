window.addEventListener('load', function () {
    fillUsersTable();
    fillCurrentUserInfo();
    fillRolesSelect($("#newUserRolesSelect"))
})

$('#editModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let modal = $(this);

    fetch("/admin/users/" + button.data('user-id'))
        .then(response => response.json())
        .then(user => {
            modal.find("#edit-id").val(user.id);
            modal.find("#edit-name").val(user.name);
            modal.find("#edit-email").val(user.email)
        });
    fillRolesSelect($("#editUserRolesSelect"));
})

$('#deleteModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let modal = $(this);

    fetch("/admin/users/" + button.data('user-id'))
        .then(response => response.json())
        .then(user => {
            modal.find("#delete-id").val(user.id);
            modal.find("#delete-name").val(user.name);
            modal.find("#delete-email").val(user.email);
            modal.find("#delete-password").val(user.password);
            modal.find("#delete-roles").val(rolesToString(user.roles))
        });
})

function fillUsersTable() {
    $("#usersTable tbody").empty();
    fetch("/admin/users/")
        .then(response => response.json())
        .then(users => {
            for (let i = 0; i < users.length; i += 1) {
                $("#usersTable tbody").append("<tr>"
                    + wrap(users[i].id)
                    + wrap(users[i].name)
                    + wrap(users[i].email)
                    + wrap(users[i].password)
                    + wrap(rolesToString(users[i].roles))
                    + wrap("<button data-user-id=" + users[i].id + " class='btn btn-md btn-info' data-toggle='modal' data-target='#editModal'>Edit</button>")
                    + wrap("<button data-user-id=" + users[i].id + " class='btn btn-md btn-danger' data-toggle='modal' data-target='#deleteModal'>Del</button>")
                    + "</tr>")
            }
        });
}

function fillCurrentUserInfo() {
    fetch("/admin/currentUser/")
        .then(response => response.json())
        .then(currentUser => {
            $("#currentUserTable tbody").append("<tr>"
                + wrap(currentUser.id)
                + wrap(currentUser.name)
                + wrap(currentUser.email)
                + wrap(currentUser.password)
                + wrap(rolesToString(currentUser.roles))
                + "</tr>")
            $("#userInfo").append(currentUser.name + " with roles " + rolesToString(currentUser.roles));
        });
}

function fillRolesSelect(select) {
    select.empty();
    fetch("/admin/roles/")
        .then(response => response.json())
        .then(roles => {
            for (let i = 0; i < roles.length; i += 1) {
                select.append("<option data-id=" + roles[i].id + ">" + roles[i].role + "</option>")
            }
        });
}

function saveUser() {
    let user = {
        name: $("#new-name").val(),
        password: $("#new-password").val(),
        email: $("#new-email").val(),
        roles: getSelectValues($("#newUserRolesSelect")[0])
    };

    fetch('/admin/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.status)
        .then(result => {
            fillUsersTable()
            $("#newUserForm")[0].reset();
            $("#usersMenuLink").click();
        })
}

function editUser() {
    let user = {
        id: $("#edit-id").val(),
        name: $("#edit-name").val(),
        password: $("#edit-password").val(),
        email: $("#edit-email").val(),
        roles: getSelectValues($("#editUserRolesSelect")[0])
    };

    fetch('/admin/users', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.status)
        .then(result => {
            fillUsersTable()
        })
}

function deleteUser() {
    let id = $("#delete-id").val();

    fetch('/admin/users/' + id, {
        method: 'DELETE',
    })
        .then(response => response.status)
        .then(result => {
            fillUsersTable()
        })
}

function wrap(value) {
    return "<td>" + value + "</td>";
}

function rolesToString(roles) {
    let rolesString = "";
    for (let i = 0; i < roles.length; i += 1) {
        rolesString = rolesString + roles[i].role + " ";
    }
    return rolesString;
}

function getSelectValues(select) {
    let result = [];
    let options = select && select.options;
    let opt;

    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];
        if (opt.selected) {
            result.push({'id': opt.dataset.id, 'role': opt.value});
        }
    }
    return result;
}
