package com.example.propple.entities.cliente

class Transaccion(
    var publicacion : Publicacion?= Publicacion(),
    var valoracion : Valoracion?= Valoracion(5,"comentarioEjemplo"),
    var Fecha : String?="99/99/9999",
    var presupuesto  : Double?=9999.99,
    var Ubicacion : String?=""
) {

}