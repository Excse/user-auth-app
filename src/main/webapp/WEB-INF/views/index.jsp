<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>

<% User user = (User) session.getAttribute("user"); %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <title>User Auth App</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/theme.css" />
  </head>
  <body>
    <nav class="navbar navbar-expand-lg glass-nav sticky-top">
      <div class="container py-1">
        <a class="navbar-brand brand-mark" href="<%= request.getContextPath() %>/">UserAuth</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#topNav"
          aria-controls="topNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="topNav">
          <div class="navbar-nav ms-auto">
            <% if (user != null) { %>
            <a class="nav-link" href="<%= request.getContextPath() %>/app/dashboard">Dashboard</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
            <% } else { %>
            <a class="nav-link" href="<%= request.getContextPath() %>/login">Login</a>
            <a class="nav-link" href="<%= request.getContextPath() %>/register">Register</a>
            <% } %>
          </div>
        </div>
      </div>
    </nav>

    <main class="container py-5">
      <section class="hero-shell p-4 p-md-5 animated-rise">
        <div class="row align-items-center g-4">
          <div class="col-lg-7">
            <p class="text-uppercase fw-semibold small mb-2">
              Lightweight servlet starter
            </p>
            <h1 class="display-5 fw-bold mb-3">
              A cleaner front door for your auth app
            </h1>
            <p class="mb-4 opacity-75">
              This Bootstrap-powered experience gives your login flow a strong
              visual identity while keeping your existing Java servlet
              architecture untouched.
            </p>
          </div>
        </div>
      </section>

      <section class="row g-4 mt-1 animated-rise-delay">
        <div class="col-md-4">
          <article class="feature-card p-4 h-100">
            <h5 class="mb-2">Reliable Routing</h5>
            <p class="mb-0 text-secondary">
              Context-path-safe links ensure this UI works in local and deployed
              environments.
            </p>
          </article>
        </div>
        <div class="col-md-4">
          <article class="feature-card p-4 h-100">
            <h5 class="mb-2">Mobile Friendly</h5>
            <p class="mb-0 text-secondary">
              Fluid grid and spacing scale naturally from phones to desktop
              without special templates.
            </p>
          </article>
        </div>
        <div class="col-md-4">
          <article class="feature-card p-4 h-100">
            <h5 class="mb-2">Ready to Extend</h5>
            <p class="mb-0 text-secondary">
              Drop in validation, flash messages, and user profile screens using
              the same style system.
            </p>
          </article>
        </div>
      </section>
    </main>

    <script src="<%= request.getContextPath() %>/js/bootstrap.js"></script>
  </body>
</html>
