<?php

// Le indicamos al cliente (Android / Retrofit) que la respuesta es un JSON estructurado
header("Content-Type: application/json");

// Importamos el archivo de conexión que creamos en el paso anterior
require_once "config/conexion.php";

try {
    // Preparamos y ejecutamos la consulta ordenada alfabéticamente
    $stmt = $pdo->query("SELECT id, carrera FROM carreras ORDER BY carrera ASC");
    $carreras = $stmt->fetchAll();

    // Convertimos el array de PHP en un formato JSON legible para Android
    echo json_encode($carreras);

} catch (Exception $e) {
    header("HTTP/1.1 500 Internal Server Error");
    echo json_encode(["error" => "Error al obtener carreras: " . $e->getMessage()]);
}
?>