<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <title>Login | User Auth App</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/theme.css" />
  </head>
  <body>
    <nav class="navbar navbar-expand-lg glass-nav sticky-top">
      <div class="container py-1">
        <a class="navbar-brand brand-mark" href="<%= request.getContextPath() %>/">UserAuth</a>
        <div class="navbar-nav ms-auto">
          <a class="nav-link" href="<%= request.getContextPath() %>/">Home</a>
          <a class="nav-link" href="<%= request.getContextPath() %>/register">Register</a>
        </div>
      </div>
    </nav>

    <main class="container py-5">
      <div class="row justify-content-center">
        <div class="col-12 col-md-9 col-lg-6 col-xl-5">
          <section class="section-card p-4 p-md-5 animated-rise">
            <h1 class="h3 mb-2">Welcome back</h1>
            <p class="text-secondary mb-4">Sign in to continue to your protected home page.</p>

            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
              <%= request.getAttribute("error") %>
            </div>
            <% } %>

            <form action="<%= request.getContextPath() %>/login" method="post" class="needs-validation" novalidate>
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" required />
              </div>

              <div class="mb-4">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required />
              </div>

              <div class="d-grid gap-2">
                <button type="submit" class="btn btn-brand btn-lg">Log In</button>
                <a href="<%= request.getContextPath() %>/register" class="btn btn-outline-secondary">Need an account?</a>
              </div>
            </form>
          </section>
        </div>
      </div>
    </main>

    <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
  </body>
</html>
