Base de datos egg_cell — Setup
Orden de ejecución
```bash
# 1. Autenticarse e importar la estructura del esquema (DDL)
mysql -u root -p < schema.sql

# 2. Importar los datos de prueba iniciales (DML)
mysql -u root -p < data.sql
```
Verificación de claves foráneas
Ejecutar `verify_fk.sql` para confirmar que las relaciones (FK) entre
`usuario`, `lote` y `registro_postura` quedaron creadas correctamente.
```bash
mysql -u root -p egg_cell < verify_fk.sql
```