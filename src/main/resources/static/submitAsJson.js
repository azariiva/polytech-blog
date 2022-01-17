/* Submit HTML form as function
    Based off idea by Keith Hackbarth
    https://github.com/keithhackbarth/submitAsJSON
    Example usage:
    $("form").submit(function(event) {
        event.preventDefault();
        submitAsJSON(this);
    });
*/


/* Function takes a jquery form
and converts it to a JSON dictionary */
function convertFormToJSON(form){
    var array = $(form).serializeArray();
    var json = {};

    $.each(array, function() {
        json[this.name] = this.value || '';
    });

    return json;
}

/* Adds a new form to the body of a page with the JSON data encoded into it */
function submitAsJSON($form) {
    var url = $form.attr('action'),
        JSONString = JSON.stringify(convertFormToJSON($form));
    $.ajax({
        type: "post",
        url: url,
        data: JSONString,
        contentType: "application/json",
        error: function (param) {
            console.log(param)
        }
    });
}