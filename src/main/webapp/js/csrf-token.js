window.addEventListener('load', function() {
    console.log('All assets are loaded');
    retrieveToken();
});

function retrieveToken() {
    $.ajax({
        type: "GET",
        url: "token",
        contentType: "text/json",
        success: function(data) {
            console.log(data.csrfToken);
            $("form").append("<input type='hidden'name='csrf' value='"+data.csrfToken+"'>")
        },
        error: function () {
            // Handle error
        }
    });
}
