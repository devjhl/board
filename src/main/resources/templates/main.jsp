<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>게시판</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .board-table th, .board-table td {
            text-align: center;
        }
        .board-table th {
            background-color: #f8f9fa;
        }
        .pagination {
            justify-content: center;
        }
        .search-box {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">게시판</h2>
    <div class="search-box">
        <input type="text" class="form-control w-25" placeholder="검색어를 입력하세요">
        <button class="btn btn-primary ml-2">검색</button>
    </div>
    <table class="table table-bordered board-table">
        <thead>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.id}</td>
                <td><a href="#">${board.title}</a></td>
                <td>${board.user.username}</td>
                <td>${board.createDate}</td>
                <td>${board.count}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-right mb-3">
        <button class="btn btn-success">글쓰기</button>
    </div>
    <nav>
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">이전</a></li>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item"><a class="page-link" href="#">${i}</a></li>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="#">다음</a></li>
        </ul>
    </nav>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>