package co.edu.uniquindio.proyectoestructura.estructurasPropias.tarea;

import co.edu.uniquindio.proyectoestructura.modelo.Tarea;

public class ListaEnlazadaTarea {
        private NodoTarea cabeza;

        // Constructor
        public ListaEnlazadaTarea() {
            this.cabeza = null;
        }

        // Método para agregar una tarea
        public void agregarTarea(Tarea tarea) {
            NodoTarea nuevoNodo = new NodoTarea(tarea);
            if (cabeza == null) {
                cabeza = nuevoNodo;
            } else {
                NodoTarea temp = cabeza;
                while (temp.getSiguiente() != null) {
                    temp = temp.getSiguiente();
                }
                temp.setSiguiente(nuevoNodo);
            }
        }

        // Método para eliminar una tarea por descripción
        public void eliminarTarea(String descripcion) {
            if (cabeza == null) return;

            if (cabeza.getTarea().getDescripcion().equals(descripcion)) {
                cabeza = cabeza.getSiguiente();
                return;
            }

            NodoTarea actual = cabeza;
            NodoTarea anterior = null;

            while (actual != null && !actual.getTarea().getDescripcion().equals(descripcion)) {
                anterior = actual;
                actual = actual.getSiguiente();
            }

            if (actual != null) {
                anterior.setSiguiente(actual.getSiguiente());
            }
        }

        // Método para mostrar todas las tareas
        public void mostrarTareas() {
            if (cabeza == null) {
                System.out.println("No hay tareas en la lista.");
                return;
            }

            NodoTarea temp = cabeza;
            while (temp != null) {
                System.out.println(temp.getTarea().getDescripcion());
                temp = temp.getSiguiente();
            }
        }

        // Método para obtener la cabeza de la lista (opcional)
        public NodoTarea getCabeza() {
            return cabeza;
        }
    }

