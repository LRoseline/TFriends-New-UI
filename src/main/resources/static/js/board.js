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

async function confirmDel() {
    const checkCount = objAll(".checklist-delete-radio:checked");

    if(checkCount.length !== 0 && confirm("정말로 삭제하시겠습니까?")) {
        let ok = 0;
        let deny = 0;

        for (var i = 0; i < checkCount.length; i++) {
            await $.ajax({
                url: "/rss/v2/"+path.split("/")[2]+"/delete/"+checkCount[i].value,
                method: "DELETE",
                beforeSend: function(xhr) {
                    xhr.setRequestHeader('X-XSRF-TOKEN', document.querySelector("#csrf").value);
                },
                success: function(data, textStatus, xhr) {
                    ok++;
                },
                error: function(data, textStatus, e) {
                    deny++;
                }
            });
        }
        alert("삭제 작업을 완료했습니다.\n("+ok+"개 성공, "+deny+"개 실패)");
        location.reload();
    } else {
        alert("삭제할 게시글을 최소 1개 이상 선택해주세요.");
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