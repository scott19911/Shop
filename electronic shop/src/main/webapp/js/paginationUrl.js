function codeAddress() {
    var _url = location.href;
    /*Check if the url already contains ?, if yes append the parameter, else add the parameter*/
    if (_url.indexOf('&pageNumber=') > 0) {
        return _url.replace(/&pageNumber=(\w|((?=[^&])\W))*/, '&pageNumber=');
    }
    if (_url.indexOf('?pageNumber=') > 0) {
        return _url.replace(/\?pageNumber=(\w|((?=[^&])\W))*/, '?pageNumber=');
    }
    if (_url.endsWith('shop')){
        return  _url + '?pageNumber=';
    }
    return _url + '&pageNumber=';
}


function setAddress(selectObject, page) {
    selectObject.setAttribute("href", codeAddress() + page);
    return false;
}
function setInput(number) {
    var x = document.getElementById("cameFrom" + number);
    var _url = location.href;
    var ur = _url.slice(_url.lastIndexOf('/'))

    x.setAttribute("value",ur);
    return false;
}