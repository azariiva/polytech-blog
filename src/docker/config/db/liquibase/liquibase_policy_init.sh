/liquibase/liquibase \
    --url=jdbc:mysql://db:3306/${MYSQL_DB} \
    --changeLogFile=changelog/dbchangelog-master.yaml \
    --username=${MYSQL_USER} \
    --password=${MYSQL_PASSWORD} \
    update