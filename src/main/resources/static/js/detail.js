function editContent() {
    $('#title').attr('contenteditable', 'true').addClass('border p-2');
    $('#content').summernote({
        height: 300,
        minHeight: null,
        focus: true
    });
    $('.btn-edit').addClass('d-none');
    $('.btn-save').removeClass('d-none');
}

function saveContent() {
    var id = $('#boardId').val();
    var title = $('#title').text();
    var content = $('#content').summernote('code');
    $('#content').summernote('destroy');
    $('#content').html(content);
    $('#title').attr('contenteditable', 'false').removeClass('border p-2');
    $('.btn-edit').removeClass('d-none');
    $('.btn-save').addClass('d-none');

    boardUpdate(id, title, content);
}

function boardUpdate(id, title, content) {
    var boardData = {
        id: id,
        title: title,
        content: content
    };

    $.ajax({
        url: "/boards/" + id,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(boardData),
        success: function(response) {
            console.log("수정 성공", response);
            window.location.href = '/boards';  // 게시판 목록 페이지로 이동
        },
        error: function(xhr, status, error) {
            console.error("수정 실패:", status, error);
            console.log("응답 메시지:", xhr.responseText);
        }
    });
}

function deleteBoard() {
    var id = $('#boardId').val();

    if (!id) {
        console.error("ID가 정의되지 않았습니다.");
        return;
    }

    $.ajax({
        url: "/boards/" + id,
        type: "DELETE",
        data: { "id": id },
        success: function(response) {
            console.log("삭제 성공:", response);
            window.location.href = '/boards';
        },
        error: function(xhr, status, error) {
            console.error("삭제 실패:", status, error);
            console.log("응답 메시지:", xhr.responseText);
        }
    });
}

$(document).ready(function(){
    $('.btn-back').on('click', function() {
        window.history.back();
    });

    $('#btn-comment-save').on('click', function() {
        commentSave();
    });

    document.querySelectorAll('#btn-comment-update').forEach(function(item) {
        item.addEventListener('click', function() {
            const form = this.closest('form');
            commentUpdate(form);
        });
    });

    function commentSave() {
        var data = {
            boardIdd: $('#boardIdd').val(),
            comment: $('#comment').val()
        };

        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/api/posts/' + data.boardIdd + '/comments',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
                success: function() {
                    alert('댓글이 등록되었습니다.');
                    window.location.reload();
                },
                error: function(error) {
                    alert('오류가 발생했습니다: ' + JSON.stringify(error));
                }
            });
        }
    }

    function commentUpdate(form) {
        const data = {
            id: form.querySelector('#id').value,
            postsId: form.querySelector('#postsId').value,
            comment: form.querySelector('#comment-content').value.trim(),
        };

        if (!data.comment) {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }

        if (confirm("수정하시겠습니까?")) {
            $.ajax({
                type: 'PUT',
                url: `/api/posts/${data.postsId}/comments/${data.id}`,
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data),
            }).done(() => {
                window.location.reload();
            }).fail((error) => {
                alert(JSON.stringify(error));
            });
        }
    }

    function commentDelete(postsId, commentId) {
        if (confirm("삭제하시겠습니까?")) {
            $.ajax({
                type: 'DELETE',
                url: `/api/posts/${postsId}/comments/${commentId}`,
                dataType: 'json',
            }).done(() => {
                alert('댓글이 삭제되었습니다.');
                window.location.reload();
            }).fail((error) => {
                alert(JSON.stringify(error));
            });
        }
    }

    window.main = {
        commentDelete: commentDelete
    };
});