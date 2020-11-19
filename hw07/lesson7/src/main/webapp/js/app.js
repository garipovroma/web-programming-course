window.notify = function (message, className) {
    if (className === undefined) {
        className = "success";
    }
    $.notify(message, {
        position: "right bottom",
        className: className
    });
}

function ajax(data, context, successFunction) {
    if (successFunction === undefined) {
        successFunction = function (response) {
            if (response["error"]) {
                $(context).find(".error").text(response["error"]);
            } else {
                if (response["redirect"]) {
                    location.href = response["redirect"];
                }
            }
        };
    }
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data,
        success: successFunction
    });
    return false;
}