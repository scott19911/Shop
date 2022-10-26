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

    x.setAttribute("value", ur);
    return false;
}

function langAddress(selectObject) {
    var _url = location.href;
    var lang = selectObject.value;
    /*Check if the url already contains ?, if yes append the parameter, else add the parameter*/
    if (_url.indexOf('lang=') > 0) {
        _url = _url.replace(/lang=(\w|((?=[^&])\W))*/, 'lang=' + lang);
        return window.location.href = _url;
    }
    _url = (_url.indexOf('?') !== -1) ? _url + '&lang=' + lang : _url + '?lang=' + lang;
    window.location.href = _url;
}

