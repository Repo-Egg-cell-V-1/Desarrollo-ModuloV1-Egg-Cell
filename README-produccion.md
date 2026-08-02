# Módulo Producción — Egg-Cell (Jhoan Sebastián Jiménez)

## ⚠️ Dependencias externas necesarias
Este código usa clases que **no vienen en esta carpeta** porque las crea otro compañero (Juan Andrés Felipe):
- `com.eggcell.model.Lote`
- `com.eggcell.model.RegistroPostura`
- `com.eggcell.repository.LoteRepository`
- `com.eggcell.repository.RegistroPosturaRepository` (necesita los métodos `sumHuevosByLoteId` y `sumAlimentoByLoteId`)

Sin esas clases el proyecto **no compila**. Asegúrate de integrarlas antes de correr `./gradlew build`.

## Contratos JSON

### POST /api/v1/lotes
Request:
```json
{
  "codigo": "GALPON-01",
  "fechaIngreso": "2026-07-01",
  "cantidadAvesIniciales": 500,
  "raza": "Hy-Line Brown"
}
```
Response (201):
```json
{
  "id": 1,
  "codigo": "GALPON-01",
  "fechaIngreso": "2026-07-01",
  "cantidadAvesIniciales": 500,
  "cantidadAvesActivas": 500,
  "raza": "Hy-Line Brown",
  "estado": "ACTIVO"
}
```

### POST /api/v1/produccion
Request:
```json
{
  "loteId": 1,
  "fecha": "2026-07-31",
  "huevosComerciales": 450,
  "huevosRotos": 10,
  "bajasMortalidad": 0,
  "alimentoConsumidoKg": 55.5
}
```
Response (201):
```json
{
  "id": 101,
  "loteId": 1,
  "fecha": "2026-07-31",
  "huevosComerciales": 450,
  "huevosRotos": 10,
  "bajasMortalidad": 0,
  "alimentoConsumidoKg": 55.5
}
```

### GET /api/v1/produccion/resumen?loteId=1
Response (200):
```json
{
  "loteId": 1,
  "codigoLote": "GALPON-01",
  "totalAvesActivas": 500,
  "totalHuevosRecolectados": 460,
  "porcentajePostura": 92.0,
  "totalAlimentoKg": 55.5
}
```

## Riesgos y dependencias
- **Concurrencia**: múltiples operarios registrando producción para el mismo lote al mismo tiempo. Mitigación: `@Transactional` + índice único en `(lote_id, fecha)`.
- **Dependencia**: `RegistroPostura` depende de que `Lote` ya exista (llave foránea) y del modelo JPA de Juan Andrés Felipe.

## Pull Requests

**PR 1** — `feature/HU-2-HU-3-servicios-produccion-resumen`
- Capa de servicios `ProduccionService` con validaciones (huevos excedentes, insumos negativos).
- Controladores `/api/v1/produccion` y `/api/v1/produccion/resumen`.
- Pruebas unitarias con JUnit 5 + Mockito (`ProduccionServiceTest`).
- Reviewer: @JuanAndresFelipe

**PR 2** — `fix/ajustes-demo-mortalidad-validaciones`
- Campo opcional `bajasMortalidad` en DTO y controlador, según feedback de la demo.
- Suite de pruebas actualizada cubriendo mortalidad.
- Reviewer: @JuanAndresFelipe
