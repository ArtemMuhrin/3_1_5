
window.addEventListener('load', function () {
    fillCurrentUserInfo();
})

async function fillCurrentUserInfo() {
    let response = await fetch("/user/currentUser");

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
        console.log(response.status);
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



