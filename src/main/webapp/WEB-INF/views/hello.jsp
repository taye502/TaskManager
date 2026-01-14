<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FrontController Test</title>
    <style>
        body { font-family: sans-serif; padding: 20px; background-color: #f0f0f0; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }
        .info-box { background: #e9ecef; padding: 15px; margin: 15px 0; border-radius: 4px; }
        .label { font-weight: bold; color: #555; }
        .links a { display: inline-block; margin-right: 10px; text-decoration: none; color: #007bff; }
        .links a:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <h1>動作確認</h1>
        
        <div class="info-box">
            <p><span class="label">Message:</span> ${message}</p>
            <p><span class="label">Time:</span> ${currentTime}</p>
        </div>

        <div class="info-box">
            <p><span class="label">Context Path:</span> ${pageContext.request.contextPath}</p>
            <p><span class="label">Path Info:</span> ${pathInfo}</p>
            <p><span class="label">Remote Addr:</span> ${remoteAddr}</p>
            <p><span class="label">User Agent:</span> ${userAgent}</p>
        </div>

        <div class="links">
            <a href="${pageContext.request.contextPath}/app/hello">Reload</a>
            <a href="${pageContext.request.contextPath}/app/hello?name=Tanaka">With Parameter</a>
            <a href="${pageContext.request.contextPath}/app/unknown">Test 404</a>
        </div>
    </div>
</body>
</html>