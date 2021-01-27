window.addEventListener('load', function () {
    fillUsersTable();
    fillCurrentUserInfo();
})

async function fillUsersTable() {
    let response = await fetch("/admin/users");

    if (response.ok) {
        let users = await response.json();
        $("#usersTable tbody").empty();
        for (let i = 0; i < users.length; i += 1) {
            $("#usersTable tbody").append("<tr>"
                + wrap(users[i].id)
                + wrap(users[i].name)
                + wrap(users[i].email)
                + wrap(users[i].password)
                + wrap(rolesToString(users[i].roles))
                + wrap("<button onclick='getUserForEdit(" + users[i].id + ")' class='btn btn-md btn-info' data-toggle='modal' data-target='#editModal'>Edit</button>")
                + wrap("<button onclick='deleteUser(" + users[i].id + ")' class='btn btn-md btn-danger' data-toggle='modal' data-target='#deleteModal'>Del</button>")
                + "</tr>")
        }
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
}

async function fillCurrentUserInfo() {
    let response = await fetch("/admin/currentUser");

    if (response.ok) {
        let currentUser = await response.json();

        $("#currentUserTable tbody").append("<tr>"
            + wrap(currentUser.id)
            + wrap(currentUser.name)
            + wrap(currentUser.email)
            + wrap(currentUser.password)
            + wrap(rolesToString(currentUser.roles))
            + "</tr>")
        $("#userInfo").append(currentUser.name + " with roles " + rolesToString(currentUser.roles));
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
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

function getUserForEdit(id) {
alert(id);
}

function deleteUser(id) {
alert(id)
}

function saveUser() {
    alert("good")
}


