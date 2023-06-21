const path = window.location.pathname;

function href(url) {
    location.href=url;
}

function hrefTab(url) {
    window.open(url);
}

function popup(url, width, height) {
    window.open(url,'Open Window','width='+width+',height='+height+',resizable=no');
}

function obj(obj) {
    return document.querySelector(obj);
}

function objAll(obj) {
    return document.querySelectorAll(obj);
}