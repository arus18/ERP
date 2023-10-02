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
            $(".ajax-submit-btn").each(function() {
                // For each button with class "ajax-submit-btn"
                $(this).before('<input type="hidden" name="csrf" value="'+data+'">');
            });
        },
        error: function () {
            // Handle error
        }
    });
}
