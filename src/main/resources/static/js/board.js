function checkALL(object) {
    const checkboxes = objAll(".checklist-delete-radio");

    checkboxes.forEach((c) => {
        c.checked = object.checked; 
    });
}

function checkOnce(object) {
    const cbox = objAll(".checklist-delete-radio");
    const cboxcheck = objAll(".checklist-delete-radio:checked");

    const checkAll = obj(object);

    if(cbox.length === cboxcheck.length) {
        checkAll.checked = true;
    } else {
        checkAll.checked = false;
    }
}

function confirmDel(objid) {
    const checkCount = objAll(".checklist-delete-radio:checked").length;
    const form = obj(objid);

    if(checkCount !== 0 && confirm("정말로 삭제하시겠습니까?")) {
        form.method = "post";
        form.action = path+"/delete";
        form.submit();
        return true;
    } else {
        alert("삭제할 게시글을 최소 1개 이상 선택해주세요.");
        return false;
    }
}

function openSearch(vis) {
    var box = document.querySelector(".search-pop");
    box.style.display = vis;
}

function jumpPage() {
    var page = obj("#page").value;
    if (page === "" | page === null) {
        alert("넘어갈 페이지 번호를 입력하세요!");
        return false;
    }

    obj('#board-management').submit();
}