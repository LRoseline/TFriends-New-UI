const navimenu = getComputedStyle(obj(".navigation"));
const navitext = getComputedStyle(obj(".navbar"));
const darkbg = obj(".navigation-background");

const menuwidth = "270px";

function menuOpen() {
  if (navimenu.getPropertyValue("width") === menuwidth) {
    onMenu("0", "none", 20);
    darkbg.style.width = "0";
  } else {
    onMenu(menuwidth, "block", 480);
    darkbg.style.width = "100%";
  }
}

function onMenu(w, s, delay) {
  obj(".navigation").style.setProperty("width", w);
  setTimeout(() => {
    obj("#login").style.setProperty("display", s);
    obj(".navbar").style.setProperty("display", s);
  }, delay);
}

var dropdown = document.getElementsByClassName("navi-child");
var i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function () {
    this.classList.toggle("navi-select");
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  });
}
