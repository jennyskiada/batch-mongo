/**
 * Show A Table Row Containing Execution's Error Message (If Any)
 */
$(document).on("click", ".show-error", function(event) {
    var _message = $(this).closest("tr").find(".hidden-error").text();
    var _colspan = $(this).closest("table").find("tbody > tr:first > td").length;
    // Change The glyphicon Arrow
    $(this).toggleClass("glyphicon glyphicon-chevron-down");
    $(this).toggleClass("glyphicon glyphicon-chevron-up");
    // Show or Hide The Error Row
    if($(this).hasClass("error-shown")) {
        $(this).removeClass("error-shown");
        var _previous = $(this).closest("tr").next();
        if(_previous.hasClass('hidden-row')) { //Sanity Check
            _previous.remove();
        }
    } else {
        $(this).addClass("error-shown");
        $(this).closest("tr").after("<tr class='hidden-row'><td colspan='" + _colspan + "'>" + _message + "</td></tr>");
        $(".hidden-row").show(400);
    }
});