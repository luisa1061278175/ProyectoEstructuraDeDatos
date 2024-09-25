package co.edu.uniquindio.proyectoestructura.util;

public class ExportadorCSV {
    /**
     *   public static void exportarCSV() {
     *         FileChooser fileChooser = new FileChooser();
     *         fileChooser.setTitle("Guardar como archivo CSV");
     *         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo CSV (*.csv)", "*.csv"));
     *         File file = fileChooser.showSaveDialog(null);
     *
     *         if (file != null) {
     *             try (FileWriter writer = new FileWriter(file)) {
     *                 writer.write("ID Reserva,Nombre Evento,Estado Reserva,ID Usuario\n");
     *                 for (ReservaDto reserva : modelFactoryController.obtenerReservasDto()) {
     *                     writer.write(reserva.id() + "," + reserva.evento().getNombreEvento() + "," +
     *                             reserva.estadoReserva() + "," +
     *                             reserva.usuario() + "\n");
     *                 }
     *                 writer.close();
     *                 System.out.println("Datos exportados a CSV correctamente.");
     *             } catch (IOException e) {
     *                 e.printStackTrace();
     *             }
     *         }
     *     }
     */
}
