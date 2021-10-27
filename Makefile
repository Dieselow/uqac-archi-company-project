

create-db:
	docker exec -i archi-company-project_db_1 mysql -u root -ppassword archi < create.sql

fill-db:
	docker exec -i archi-company-project_db_1 mysql -u root -ppassword archi < insert.sql

start-app-setup:
    start "C:\Program Files\Docker\Docker\Docker Desktop.exe"
    timeout 200
    docker-compose up -d
