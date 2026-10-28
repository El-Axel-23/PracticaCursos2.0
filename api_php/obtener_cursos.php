<?php
// obtener_cursos.php
header("Content-Type: application/json; charset=UTF-8");
require_once "config/conexion.php";
$categoria_id = isset($_GET['carrera_id']) ? intval($_GET['carrera_id']) : 0;

try { 
    if ($carrera_id > 0) {
        $stmt = $pdo->prepare("SELECT id, carrera_id, nombre, salon, url_imagen FROM cursos WHERE carrera_id = ?");
        $stmt->execute([$carrera_id]);
    } else {
        $stmt = $pdo->query("SELECT id, carrera_id, nombre, salon, url_imagen FROM cursos");
    }
    $productos = $stmt->fetchAll();
    // 2. Casteamos los tipos de datos para que coincidan con la data class dataCursos de Kotlin
    foreach ($cursos as &$c) {
        $c['id'] = (int)$c['id'];
        $c['carrera_id'] = (int)$c['carrera_id'];
    }

    echo json_encode($cursos);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener catálogo de cursos: " . $e->getMessage()]);
}
?>