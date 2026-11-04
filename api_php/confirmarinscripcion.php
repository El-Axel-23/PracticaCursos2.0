<?php
header("Content-Type: application/json; charset=UTF-8");

require_once "conexion.php";

// Recibir JSON desde Android
$datos = json_decode(file_get_contents("php://input"), true);

$matricula = trim($datos["matricula"] ?? "");
$curso_id = filter_var(
    $datos["curso_id"] ?? null,
    FILTER_VALIDATE_INT
);
$fecha_registro = $datos["fecha_registro"] ?? "";

// Validar datos
if ($matricula === "" || $curso_id === false ||
    $curso_id === null || $curso_id <= 0 ||
    $fecha_registro === "") {

    echo json_encode([
        "exito" => false,
        "mensaje" => "Completa todos los datos correctamente"
    ]);

    exit;
}

// Validar formato de fecha
$fecha = DateTime::createFromFormat("!Y-m-d", $fecha_registro);

if (!$fecha || $fecha->format("Y-m-d") !== $fecha_registro) {

    echo json_encode([
        "exito" => false,
        "mensaje" => "Formato de fecha incorrecto"
    ]);

    exit;
}

try {

    $conexion->beginTransaction();

    // Buscar alumno
    $sql = "SELECT id FROM alumnos
            WHERE matricula = :matricula";

    $consulta = $conexion->prepare($sql);

    $consulta->execute([
        ":matricula" => $matricula
    ]);

    $alumno = $consulta->fetch(PDO::FETCH_ASSOC);

    if (!$alumno) {

        $conexion->rollBack();

        echo json_encode([
            "exito" => false,
            "mensaje" => "Matrícula no encontrada"
        ]);

        exit;
    }

    $alumno_id = $alumno["id"];

    // Buscar curso y bloquearlo durante la inscripción
    $sql = "SELECT id, nombre, cupo_maximo
            FROM cursos
            WHERE id = :curso_id
            FOR UPDATE";

    $consulta = $conexion->prepare($sql);

    $consulta->execute([
        ":curso_id" => $curso_id
    ]);

    $curso = $consulta->fetch(PDO::FETCH_ASSOC);

    if (!$curso) {

        $conexion->rollBack();

        echo json_encode([
            "exito" => false,
            "mensaje" => "Curso no encontrado"
        ]);

        exit;
    }

    // Verificar inscripción duplicada
    $sql = "SELECT id FROM inscripciones
            WHERE curso_id = :curso_id
            AND alumno_id = :alumno_id";

    $consulta = $conexion->prepare($sql);

    $consulta->execute([
        ":curso_id" => $curso_id,
        ":alumno_id" => $alumno_id
    ]);

    if ($consulta->fetch(PDO::FETCH_ASSOC)) {

        $conexion->rollBack();

        echo json_encode([
            "exito" => false,
            "mensaje" => "El alumno ya está inscrito en este curso"
        ]);

        exit;
    }

    // Contar inscritos
    $sql = "SELECT COUNT(*) FROM inscripciones
            WHERE curso_id = :curso_id";

    $consulta = $conexion->prepare($sql);

    $consulta->execute([
        ":curso_id" => $curso_id
    ]);

    $total = (int)$consulta->fetchColumn();

    // Verificar cupo
    if ($total >= $curso["cupo_maximo"]) {

        $conexion->rollBack();

        echo json_encode([
            "exito" => false,
            "mensaje" => "El curso no tiene cupo disponible"
        ]);

        exit;
    }

    // Guardar inscripción
    $sql = "INSERT INTO inscripciones
            (curso_id, alumno_id, fecha_registro)
            VALUES
            (:curso_id, :alumno_id, :fecha_registro)";

    $consulta = $conexion->prepare($sql);

    $consulta->execute([
        ":curso_id" => $curso_id,
        ":alumno_id" => $alumno_id,
        ":fecha_registro" => $fecha_registro
    ]);

    $inscripcion_id = $conexion->lastInsertId();

    $conexion->commit();

    echo json_encode([
        "exito" => true,
        "mensaje" => "Inscripción realizada correctamente",
        "inscripcion_id" => (int)$inscripcion_id
    ]);

} catch (PDOException $e) {

    if ($conexion->inTransaction()) {
        $conexion->rollBack();
    }

    echo json_encode([
        "exito" => false,
        "mensaje" => "Error al confirmar la inscripción"
    ]);

}
?>