<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <title>Register | User Auth App</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/theme.css" />
  </head>
  <body>
    <nav class="navbar navbar-expand-lg glass-nav sticky-top">
      <div class="container py-1">
        <a class="navbar-brand brand-mark" href="<%= request.getContextPath() %>/">UserAuth</a>
        <div class="navbar-nav ms-auto">
          <a class="nav-link" href="<%= request.getContextPath() %>/">Home</a>
          <a class="nav-link" href="<%= request.getContextPath() %>/login">Login</a>
        </div>
      </div>
    </nav>

    <main class="container py-5">
      <div class="row justify-content-center">
        <div class="col-12 col-lg-8">
          <section class="section-card p-4 p-md-5 animated-rise">
            <div class="d-flex justify-content-between align-items-start flex-wrap gap-2 mb-4">
              <div>
                <h1 class="h3 mb-1">Create your account</h1>
                <p class="text-secondary mb-0">
                  Set up profile details for a smoother first login.
                </p>
              </div>
            </div>

            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger" role="alert">
              <%= request.getAttribute("error") %>
            </div>
            <% } %>

            <form action="<%= request.getContextPath() %>/register" method="post" class="row g-3">
              <div class="col-md-6">
                <label for="firstName" class="form-label">First Name</label>
                <input
                  type="text"
                  class="form-control"
                  id="firstName"
                  name="firstName"
                  required
                />
              </div>
              <div class="col-md-6">
                <label for="lastName" class="form-label">Last Name</label>
                <input
                  type="text"
                  class="form-control"
                  id="lastName"
                  name="lastName"
                  required
                />
              </div>

              <div class="col-md-6">
                <label for="username" class="form-label">Username</label>
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  name="username"
                  required
                />
              </div>
              <div class="col-md-6">
                <label for="email" class="form-label">Email</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  name="email"
                  required
                />
              </div>

              <div class="col-12">
                <label for="password" class="form-label">Password</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  name="password"
                  required
                />
              </div>

              <div class="col-12 d-grid d-sm-flex gap-2 pt-2">
                <button type="submit" class="btn btn-brand px-4">
                  Register
                </button>
                <a href="<%= request.getContextPath() %>/login" class="btn btn-outline-secondary">
                  Already have an account?
                </a>
              </div>
            </form>
          </section>
        </div>
      </div>
    </main>

    <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
  </body>
</html>
