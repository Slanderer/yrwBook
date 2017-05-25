
var elements = document.getElementsByClassName("fc-time");
for (var i = 0, len = elements.length; i < len; i++) {
    var html = elements[i].innerHTML;
    html = html.replace('p','');
    elements[i].innerHTML =html;
}
