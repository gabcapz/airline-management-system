# SkyManage — Airline Management System

## Setup

1. Create MySQL database: run `database/schema.sql` then `database/seed.sql` (or create db `airline_management` manually).
2. Set env vars `DB_USERNAME`, `DB_PASSWORD`.
3. Backend: `cd backend && mvnw.cmd spring-boot:run` (port 8080).
4. Frontend: `cd frontend && npm install && npm run dev` (port 5173).

## Notes

MySQL root password was not available in this environment; configure `DB_PASSWORD` accordingly. Backend compiles and frontend builds successfully.
