# user-auth-app

A small learning project to practice **Jakarta Servlets + JSP**.

This repository is **not production ready** and is intended for experimentation and understanding:

- form-based auth flow (register, login, logout)
- session-based access control
- database-backed users (PostgreSQL)
- protected content under `/app/*`
- secret image serving from a servlet
- dashboard view showing user/session-related data

## Tech Stack

- Java 17
- Maven
- Jakarta Servlet API 6 / JSP API 3.1
- PostgreSQL
- BCrypt (`org.mindrot:jbcrypt`) for password hashing
- Deployed as a WAR to a Jakarta-compatible servlet container (for example Tomcat 10.1+)

## Project Layout

- `src/main/java/controller` - servlets (`/login`, `/register`, `/logout`, `/app/dashboard`, `/app/secret`)
- `src/main/java/filter` - auth filter for protected routes
- `src/main/java/dao` - data access layer
- `src/main/java/util` - JNDI helper
- `src/main/webapp/WEB-INF/views` - JSP pages
- `src/main/webapp/META-INF/context.xml` - JNDI datasource configuration
- `UserDB.session.sql` - SQL schema for users table

## Prerequisites

1. JDK 17+
2. Maven 3.8+
3. PostgreSQL 14+ (or equivalent)
4. A servlet container compatible with Jakarta namespaces (Tomcat 10.1+ recommended)

## 1) Database Setup

Create DB/user (or reuse your own), then apply the schema.

Example (defaults from `context.xml`):

```sql
CREATE DATABASE userdb;
CREATE USER wai WITH PASSWORD 'wai';
GRANT ALL PRIVILEGES ON DATABASE userdb TO wai;
```

Then run schema:

```bash
psql -U wai -d userdb -f UserDB.session.sql
```

## 2) Configure Datasource (JNDI)

This app expects JNDI resources defined in `src/main/webapp/META-INF/context.xml`:

- `jdbc/local`
- `jdbc/production`
- env flag `release` (Boolean)

By default, `release=false`, so the app uses `jdbc/local`.

Adjust these values in `context.xml` to match your local PostgreSQL config:

- URL (default: `jdbc:postgresql://localhost:5432/userdb`)
- username/password

If you use Tomcat, make sure the deployed app sees this context configuration.

## 3) Build

From repository root:

```bash
mvn clean package
```

This generates:

- `target/user-auth-app.war`

## 4) Deploy and Run

### Option A: Manual deploy to Tomcat

1. Copy `target/user-auth-app.war` to your Tomcat `webapps/` folder.
2. Start Tomcat.
3. Open:
   - `http://localhost:8080/user-auth-app/`

### Option B: Exploded app (if preferred)

Deploy the exploded artifact from `target/user-auth-app/` to your container.

## 5) How to Use the App

1. Open index page (`/`).
2. Register a new account (`/register`).
3. Login (`/login`).
4. After successful login, you are redirected to:
   - `/app/dashboard`
5. Access protected secret image via:
   - `/app/secret`
6. Logout via:
   - `/logout`

Routes under `/app/*` are protected by `AuthFilter`. Unauthenticated access redirects to `/login`.

## Auth/Data Flow (High Level)

1. Register stores a new user in PostgreSQL (password hashed with BCrypt).
2. Login verifies password hash and loads user data.
3. On successful login, the user object is stored in HTTP session (`session.user`).
4. `AuthFilter` checks session user for all `/app/*` requests.
5. Dashboard renders user/timestamps/locale values from stored model data.

## Notes and Limitations

This is a learning/demo project:

- no production hardening
- no CSRF protection
- minimal validation and error handling
- no account lockout/rate limiting
- no secure secret management for credentials
- no advanced logging/monitoring

Do not use this as-is in production.

## Useful Commands

```bash
# compile + package WAR
mvn clean package

# run tests (if/when present)
mvn test
```

## Troubleshooting

- `No Datasource` / JNDI lookup errors:
  - verify `context.xml` is loaded by your container
  - verify JNDI names (`jdbc/local`, `jdbc/production`) match exactly
- DB connection failures:
  - verify PostgreSQL is running
  - verify DB/user/password and JDBC URL
- 404 on app routes:
  - confirm WAR deployed as `user-auth-app`
  - use correct base URL: `/user-auth-app`
- Redirect loops to `/login`:
  - check session creation and whether cookies are enabled
