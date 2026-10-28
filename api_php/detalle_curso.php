<?php

header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";

$curso_id = isset($_GET['curso_id']) ? intval($_GET['curso_id']) : 0;

if ($curso_id <= 0) {
    header("HTTP/1.1 400 Bad Request");
    echo json_encode(["error" => "El ID del curso proporcionado no es valido"]);
    exit;
}

try {
    $stmt = $pdo->prepare("SELECT id, carrera_id, codigo, nombre, descripcion, url_imagen, salon, edificio, total_horas, dias_atencion, horario, cupo_maximo FROM cursos WHERE id = ?");
    $stmt->execute([$curso_id]);
    $curso = $stmt->fetch();

    if ($curso) {
        $curso['id'] = (int)$curso['id'];
        $curso['carrera_id'] = (int)$curso['carrera_id'];
        $curso['total_horas'] = (int)$curso['total_horas'];
        $curso['cupo_maximo'] = (int)$curso['cupo_maximo'];
        $curso['descripcion'] = $curso['descripcion'] ?? "";        

        echo json_encode($curso);
    } else {
    header("HTTP/1.1 404 Not Found");
    echo json_encode(["error" => "No se encontro el curso solicitado"]);
    }
    
} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener el detalle del curso: " . $e->getMessage()]);
}
?>