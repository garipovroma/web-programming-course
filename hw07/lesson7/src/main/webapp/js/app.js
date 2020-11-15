window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

function ajax(data, context) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data,
        success: function (response) {
            if (response["error"]) {
                $(context).find(".error").text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    });
    return false;
}