<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
 
<% User user = (User) session.getAttribute("user"); %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <title>Dashboard | User Auth App</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/theme.css" />
  </head>
  <body>
    <nav class="navbar navbar-expand-lg glass-nav sticky-top">
      <div class="container py-1">
        <a class="navbar-brand brand-mark" href="<%= request.getContextPath() %>/">UserAuth</a>
        <div class="navbar-nav ms-auto">
          <a class="nav-link" href="<%= request.getContextPath() %>/">Home</a>
          <a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
        </div>
      </div>
    </nav>

    <main class="container py-5">
      <section class="hero-shell p-4 p-md-5 mb-4 animated-rise">
        <div class="row align-items-center g-3">
          <div class="col-md-8">
            <p class="text-uppercase small fw-semibold mb-1">Signed-in space</p>
            <h1 class="display-6 fw-bold mb-2">
              <%= "Welcome, " + user.getFirstName() + "!" %>
            </h1>
            <p class="mb-0 opacity-75">Your profile snapshot is shown below using data from the active session.</p>
          </div>
        </div>
      </section>

      <section class="section-card p-4 p-md-5 animated-rise-delay">
        <h2 class="h4 mb-4">User Information</h2>

        <div class="row g-3">
          <div class="col-md-6 col-lg-4">
            <div class="feature-card p-3 h-100">
              <div class="info-label mb-1">ID</div>
              <div class="metric-badge"><%= user.getId() %></div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="feature-card p-3 h-100">
              <div class="info-label mb-1">Username</div>
              <div class="fw-semibold"><%= user.getUsername() %></div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="feature-card p-3 h-100">
              <div class="info-label mb-1">First Name</div>
              <div class="fw-semibold"><%= user.getFirstName() %></div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="feature-card p-3 h-100">
              <div class="info-label mb-1">Last Name</div>
              <div class="fw-semibold"><%= user.getLastName() %></div>
            </div>
          </div>
          <div class="col-md-6 col-lg-4">
            <div class="feature-card p-3 h-100">
              <div class="info-label mb-1">Email</div>
              <div class="fw-semibold"><%= user.getEmail() %></div>
            </div>
          </div>
        </div>
      </section>
    </main>

    <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
  </body>
</html>
