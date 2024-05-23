public class polimorfismo {

    public static void main(String[] args) {
        Animal miMascota = new Perro();
        miMascota.hacerSonido();  // Salida: Guau guau

        miMascota = new Gato();
        miMascota.hacerSonido();  // Salida: Miau miau
    }

}


class Animal {
    void hacerSonido() {
        System.out.println("Sonido genérico de un animal");
    }
}

class Perro extends Animal {

    @Override
    void hacerSonido() {
        System.out.println("Guau guau");
    }
}

class Gato extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("Miau miau");
    }
}
