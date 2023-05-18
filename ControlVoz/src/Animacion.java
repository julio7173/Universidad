public interface Animacion {
    void animar(Figura figura, Contenedor contenedor);
    Animacion DVD = new Animacion() {
        @Override
        public void animar(Figura figura, Contenedor contenedor) {
            int x = figura.getX();
            int y = figura.getY();
            int ancho = figura.getAncho();
            int alto = figura.getAltura();

            // Actualizar la posición de la figura
            x += figura.getVelocidadX();
            y += figura.getVelocidadY();

            // Rebotar cuando se alcanzan los bordes de la ventana
            if (x <= 0 || x + ancho >= contenedor.getWidth()) {
                figura.setVelocidadX(figura.getVelocidadX() * -1);
            }
            if (y <= 0 || y + alto >= contenedor.getHeight()) {
                figura.setVelocidadY(figura.getVelocidadY() * -1);
            }

            // Actualizar las coordenadas x e y de la figura
            figura.setX(x);
            figura.setY(y);
        }
    };
    Animacion saltar = new Animacion() {
        @Override
        public void animar(Figura figura, Contenedor contenedor) {
            int x = figura.getX();
            int y = figura.getY();
            int ancho = figura.getAncho();
            int alto = figura.getAltura();

            // Actualizar la posición de la figura
            x += figura.getVelocidadX();
            y += figura.getVelocidadY();

            // Rebotar cuando se alcanzan los bordes de la ventana
            if (x <= 0 || x + ancho >= contenedor.getWidth()) {
                figura.setVelocidadX(figura.getVelocidadX() * -1);
            }
            if (y <= 0 || y + alto >= contenedor.getHeight()) {
                figura.setVelocidadY(figura.getVelocidadY() * -1);
            }

            // Actualizar las coordenadas x e y de la figura
            figura.setX(x);
            figura.setY(y);
        }
    };
}