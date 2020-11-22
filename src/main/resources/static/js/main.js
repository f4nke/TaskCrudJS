$(document).ready(function () {


    function fillTheTable() {
        fetch("/rest/all").then(response => response.json()).then(result => {
            for (let user of result) {
                let newTr = `<tr>
                    <td id="userID"> ${user.id} </td>
                    <td  id="userUsername"> ${user.name} </td>                           
                    <td> 
                    <a  href="/rest/get?id=${user.id}" id="eBtn" class="btn btn-primary eBtn" style="color: white">
                    Edit
                    </a>
                    </td>
                    <td> 
                    <a  href="/rest/get?id=${user.id}\" id= "dBtn" class="btn btn-danger dBtn">
                    Delete
                    </a> 
                    </td>"
                    </tr>`;
                $("#AllUsers > tbody").append(newTr);
            }
        })
    }

    fillTheTable();

    //================================================================
    //Edit form
    //================================================================
    $("#AllUsers > tbody").on("click", "#eBtn", function (event) {
        event.preventDefault();
        let href = $(this).attr("href");
        $.get(href, function (user, status) {
            $("#editId").val(user.id)
            $("#editName").val(user.username)
        });

        $("#Edit").on("click", "#saveChanges", async function (event) {
            event.preventDefault();
            let id = $("#editId").val();
            let username = $("#editName").val();

            let role = {
                role: $("#editRole").val()
            }

            let user = {
                "id": id,
                "username": username,
                "roles": [role]
            };

            await fetch('/rest/save', {
                method: 'post',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify(user)
            }).then(r => {
                if (r.status >= 400) {
                    alert("Error. Please try again")
                } else {
                    $("#AllUsers > tbody").html("");
                    fillTheTable();
                }

            });
            $(document.getElementById("Edit")).modal("hide");
            $("#Edit").off("click", "#saveChanges");

        });


        $(document.getElementById("Edit")).modal();

    });

    //================================================================
    //Delete form
    //================================================================
    $("#AllUsers > tbody").on("click", "#dBtn", function(event) {
        event.preventDefault();
        let href = $(this).attr("href");
        $.get(href, function (user, status) {
            $("#delId").val(user.id);
            $("#delName").val(user.username);
            $("#rolesString").val(user.rolesToString);
        });

        $("#Delete").on("click", "#delButton", async function(event) {
            event.preventDefault();
            let id = $("#delId").val();
            console.log(id);
            await fetch("/rest/delete?id=" + id, {
            }).then(r =>{
                if(r.status >= 400){
                    alert("Error. Please try again")
                } else {
                    $("#AllUsers > tbody").html("");
                    fillTheTable();
                }
            })
            $(document.getElementById("Delete")).modal("hide");
            $("#Delete").off("click", "#delButton");
        });


        $(document.getElementById("Delete")).modal();
    });


    $("#addNewUser").on("click", async function (event) {
        event.preventDefault();
        let name = $("#newName").val();
        let username = $("#newUsername").val();
        let password = $("#newPassword").val();
        let role = {
            role: $("#newRole").val()
        };
        let user = {
            "name": name,
            "username": username,
            "password": password,
            "roles": [role]
        };


        await fetch("/rest/save", {
            method: 'post',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user)
        }).then(r => {
            if (r.status >= 400) {
                alert("Error. Please try again")
            } else {
                $("#AllUsers > tbody").html("");
                fillTheTable();
            }
        });
    })
});