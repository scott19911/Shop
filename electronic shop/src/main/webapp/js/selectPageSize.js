function setPageSize(selectObject) {
    var pageSize = selectObject.value;
    var _url = location.href;
    /*Check if the url already contains ?, if yes append the parameter, else add the parameter*/
    if (_url.indexOf('pageSize=') > 0) {
        _url = _url.replace(/pageSize=(\w|((?=[^&])\W))*/, 'pageSize=' + pageSize);
        return window.location.href = _url;
    }
    _url = (_url.indexOf('?') !== -1) ? _url + '&pageSize=' + pageSize : _url + '?pageSize=' + pageSize;
    /*reload the page */
    window.location.href = _url;
}

function setOrderBy(selectObject) {
    var orderBy = selectObject.value;
    var _url = location.href;
    /*Check if the url already contains ?, if yes append the parameter, else add the parameter*/
    if (_url.indexOf('orderBy=') > 0) {
        _url = _url.replace(/orderBy=(\w|((?=[^&])\W))*/, 'orderBy=' + orderBy);
        return window.location.href = _url;
    }
    _url = (_url.indexOf('?') !== -1) ? _url + '&orderBy=' + orderBy : _url + '?orderBy=' + orderBy;
    /*reload the page */
    window.location.href = _url;
}