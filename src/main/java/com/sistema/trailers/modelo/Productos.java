package com.sistema.trailers.modelo;
import javax.persistence.*;


    @Entity
    public class Productos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "codigo")
        private int codigo;

        private String descripcion;
        private String imagen;
        private double precio;

        public Productos(){



        }

		public Productos(int codigo, String descripcion, String imagen, double precio) {
			super();
			this.codigo = codigo;
			this.descripcion = descripcion;
			this.imagen = imagen;
			this.precio = precio;
		}

		public int getCodigo() {
			return codigo;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getImagen() {
			return imagen;
		}

		public void setImagen(String imagen) {
			this.imagen = imagen;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}
        
    }
