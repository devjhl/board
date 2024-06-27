    $(document).ready(function() {
    $('#content').summernote({
        height: 300,
        minHeight: null,
        focus: true
    });
});

    function updateBoard() {
    var id = $('#boardId').val();
    var title = $('#title').val();
    var content = $('#content').summernote('code'); // summernote의 내용을 가져오기 위해 .code() 사용

    boardUpdate(id, title, content);
}
