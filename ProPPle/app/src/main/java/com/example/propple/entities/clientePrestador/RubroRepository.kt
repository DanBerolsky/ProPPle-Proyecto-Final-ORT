package com.ort.casodeusotest.entities

class RubroRepository {

    var rubroList : MutableList<Rubro> = mutableListOf()

    init {
        rubroList.add(Rubro("Plom", "P"))
        rubroList.add(Rubro("Elec", "E"))
        rubroList.add(Rubro("Gas", "G"))
        rubroList.add(Rubro("Domés", "D"))
        rubroList.add(Rubro("Aire AC", "A"))
    }

}