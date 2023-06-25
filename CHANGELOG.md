# CHANGELOG

Todos los cambios notables en este proyecto se documentarán en este archivo.

El formato está basado en [Keep a Changelog](https://keepachangelog.com/en/1.1.0/), y este proyecto se adhiere a [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

<!-- ## [Unreleased] -->
<!-- ### ADDED ✔️-->
<!-- ### FIXED 🐛-->
<!-- ### CHANGED 🛠️-->
<!-- ### REMOVED 🗑️-->
<!-- ### SECURITY 🛡️-->
<!-- ### DEPRECATED 🛑-->

## UNRELEASED

### ADDED ✔️

---

- `CI/ID` - **Nuevo Workflow de GitHub de SonarCloud**: _Se ha añadido un nuevo workflow de GitHub para analizar el código con SonarCloud._

---

- `DOMAIN` - **Nuevos tests unitarios**: _Se han añadido nuevos tests unitarios`._
  - _Se ha añadido los tests unitarios para el **ValueObject** de `Email`._ 
  - _Se ha añadido los tests unitarios para el **ValueObject** de `Name`._ 

---

- `DOMAIN` - **Nueva Entidad `Password`**: _Se ha añadido la entidad Password en la capa de dominio._
  - _Se han creado los **ValueObjects** necesarios para la entidad `Password` (`PasswordId` y `PasswordSalt`)._
  - _Se ha creado el evento de dominio para la creación de la entidad `Password` (`CreateNewPasswordEvent`)._
  - _Se ha creado la factoría que nos permite crear una entidad `Password` con datos primitivos._

---

- `DOMAIN` - **Nueva Entidad `User`**: _Se ha añadido la entidad `User` en la capa de dominio._
  - _Se han creado los **ValueObjects** necesarios para la entidad `User` (`UserId` y `UserName`)._
  - _Se ha creado el evento de dominio para la creación de la entidad `User` (`CreateNewUserEvent`)._
  - _Se ha creado la factoría que nos permite crear una entidad `User` con datos primitivos._

---

- `DOMAIN` - **Nuevos ValueObjects**: _Se ha añadido nuevos **ValueObjects** para todas las entidades de dominio._
  - _Se ha creado el **ValueObject** para el `Email`._
  - _Se ha creado el **ValueObject** para el `Name`._
  - _Se ha creado el **ValueObject** para el `LastName`_
  - _Se ha creado el **ValueObject** para el `HashSha256`._
  - _Se ha creado el **ValueObject** para el `Timestamp`._

---

- `DOMAIN` - **Clase de Utilidades `EventHandler`**: _Se ha añadido una clase de utilidades en la capa de dominio que nos permite lanzar eventos, y crear eventos y listeners de dominio._

---

- `DOMAIN` - **Nuevas clases base**: _Se han añadido algunas clases para representar varios elementos en la capa de dominio._
  - _Se ha creado la **interfaz** para representar un **ValueObject**._
  - _Se ha creado la **interfaz** para representar una **Entity**._

---