<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "conexion.php";

// Recibir JSON desde Android
$datos = json_decode(file_get_contents("php://input"), true);
$matricula = trim($datos["matricula"] ?? "");
if (empty($matricula)) {
    echo json_encode([
        "exito" => false,
        "mensaje" => "Ingrese la matrícula"
    ]);

    exit;
}
try {

    $sql = "SELECT matricula, nombre, correo_institucional
            FROM alumnos
            WHERE matricula = :matricula";

    $consulta = $conexion->prepare($sql);

    $consulta->bindParam(":matricula", $matricula);

    $consulta->execute();

    $alumno = $consulta->fetch(PDO::FETCH_ASSOC);

    if ($alumno) {
        echo json_encode([
            "exito" => true,
            "mensaje" => "Alumno encontrado",
            "matricula" => $alumno["matricula"],
            "nombrealumno" => $alumno["nombre"],
            "correo_institucional" => $alumno["correo_institucional"]
        ]);

    } else {

        echo json_encode([
            "exito" => false,
            "mensaje" => "Matrícula no encontrada",
            "matricula" => "",
            "nombrealumno" => "",
            "correo_institucional" => ""
        ]);

    }

} catch (PDOException $e) {

    echo json_encode([
        "exito" => false,
        "mensaje" => "Error al buscar el alumno"
    ]);

}
?>