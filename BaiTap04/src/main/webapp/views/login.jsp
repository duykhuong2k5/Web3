<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>

    <!-- Bootstrap CDN for responsive design -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ6h4Wv1fG6eP5dO8lW5nuaGrn9O8n6sbzYfXkFh5fRJ5X4y5h5G54JkW1so" crossorigin="anonymous">

    <style>
        body {
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: 'Segoe UI', Arial, sans-serif;
        }

        .container {
            max-width: 400px;
            padding: 40px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        h2 {
            font-weight: 600;
            color: #333;
            margin-bottom: 25px;
        }

        .form-label {
            font-weight: 500;
            color: #555;
        }

        .form-control {
            border-radius: 8px;
            padding: 10px;
            font-size: 1rem;
            transition: border-color 0.3s;
        }

        .form-control:focus {
            box-shadow: 0 0 0 0.25rem rgba(0, 123, 255, 0.25);
        }

        .btn-login {
            background-color: #007bff;
            color: white;
            border: none;
            width: 100%;
            padding: 12px;
            border-radius: 8px;
            font-size: 1.1rem;
            font-weight: 600;
            transition: background-color 0.3s, transform 0.1s;
        }

        .btn-login:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
            border-color: #f5c6cb;
            font-size: 0.9rem;
            text-align: left;
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
            font-size: 0.9rem;
        }

        .register-link a {
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s;
        }

        .register-link a:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        .forgot-password-link {
            display: block;
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2 class="text-center">Đăng nhập</h2>

        <c:if test="${not empty alert}">
            <div class="alert alert-danger">${alert}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Tên đăng nhập:</label> 
                <input type="text" id="username" name="username" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu:</label> 
                <input type="password" id="password" name="password" class="form-control" required>
            </div>

            <div class="mb-3 form-check">
                <input type="checkbox" name="remember" class="form-check-input">
                <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
            </div>

            <button type="submit" class="btn btn-login">Đăng nhập</button>
        </form>





        <!-- Liên kết quên mật khẩu -->
        <div class="forgot-password-link">
            <a href="${pageContext.request.contextPath}/views/forgot_pas.jsp">Quên mật khẩu?</a>
        </div>
        
        
        

        <div class="register-link">
            <p>
                Chưa có tài khoản? <a href="${pageContext.request.contextPath}/views/register.jsp">Đăng ký ngay</a>
            </p>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-w5kFwnT2RlgmcTgFzRU6MYTTzZ1ksw9rTe5xu9i+OIHpsZ2oVwJpqkI0Afo9w6aH" crossorigin="anonymous"></script>

</body>
</html>
