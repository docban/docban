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

- `DOMAIN` - **Nuevos Entidad `Password`**: _Se ha añadido la entidad `Password` en la capa de dominio._
  - _Se han creado los `ValueObjects` necesarios para la entidad `Password` (`PasswordId` y `PasswordSalt`)._
  - _Se ha creado el evento de dominio para la creación de la entidad `Password` (`CreateNewPasswordEvent`)._

---

- `DOMAIN` - **Nuevos Entidad `User`**: _Se ha añadido la entidad `User` en la capa de dominio._
  - _Se han creado los `ValueObjects` necesarios para la entidad `User` (`UserId` y `UserName`)._
  - _Se ha creado el evento de dominio para la creación de la entidad `User` (`CreateNewUserEvent`)._

---

- `DOMAIN` - **Nuevos `ValueObjects`**: _Se ha añadido nuevos ValueObjects en la capa de dominio. `Email`, `Name`, `LastName`, `UUID`, `HashSha256`._

---

- `DOMAIN` - **Clase de Utilidades EventHandler**: _Se ha añadido una clase de utilidades en la capa de dominio que nos permite lanzar eventos, y crear eventos y listeners de dominio._

---

- `DOMAIN` - **Clase de Utilidades TimeStamp**: _Se ha añadido una clase de utilidades en la capa de dominio para construir los Timestamps._

---

- `DOMAIN` - **Nuevas interfaces base**: _Se han añadido las interfaces para representar ValueObjects y Entities en la capa de dominio._

---