<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>
    <title></title>
    <style>
        * {
            margin: 0px;
            padding: 0px;
            font-family: "微软雅黑"
        }

        .container {
            width: 96%;
            margin: 1rem auto 3rem;
        }

        .title {
            font-size: 24px;
            color: #242424;
            font-weight: bold;
            display: block;
            width: 100%
        }

        .content {
            margin-top: 1rem;
            text-indent: 2em;
            font-size: 12px;
            color: #343434;
            line-height: 20px
        }

        .content p {
            width: 100%;
            line-height: 150%;
        }

        .content img {
            width: 100%;
            margin: .5rem auto;
            display: block;
            box-sizing: border-box;
            border: 1px solid #ccc;
        }

        .time {
            font-size: 15px;
            color: #565656;
            margin: 5px 0px 10px 0px;
            width: 100%;
        }
    </style>

</head>
<body>
<div class="container">
    <p class="title">${notice.notice_title}</p>

    <p class="time">${um:format(notice.dateline, "yyyy-MM-dd HH:mm:ss")}</p>

    <div class="content">${notice.notice_content}
        <br>
    </div>
</div>
</body>
</html>
