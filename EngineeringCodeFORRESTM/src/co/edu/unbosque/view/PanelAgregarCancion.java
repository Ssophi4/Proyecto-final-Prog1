package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PanelAgregarCancion extends JPanel implements DropTargetListener {

	//Aributos
	private static final long serialVersionUID = 1L;
	private JLabel etiquetaNombreCancion;
	private JLabel etiquetaNombreAutor;
	private JLabel etiquetaGeneroMusica;
	private JLabel etiquetaArchivo;
	private JTextField campoTextoNombreCancion;
	private JTextField campoTextoNombreAutor;
	private JTextField campoTextoGeneroMusica;
	private JTextArea campoTextoArchivo;
	private JTable tablaCanciones;
	private JScrollPane scroll;
	private JButton botonAgregar;
	private JButton botonCancelar;
	private JPanel panelRegistro;
	private DropTarget dropTarget;//permite arrastrar y soltar archivos.
	private DefaultTableModel modeloTabla;
	private final String AGREGAR = "Agregar";
	private final String CANCELAR = "CANCELAR";

	//constructor
	public PanelAgregarCancion() {//diseño del panel en una cuadrícula de 1 fila y 2 columnas.
		this.setLayout(new GridLayout(1, 2));
		this.asignarValores();
		this.agregarComponentes();
	}
	//metodos
	public void asignarValores() {//inicializar y agregar los componentes visuales al panel.
//Inicializa todos los componentes visuales y objetos necesarios.
		panelRegistro = new JPanel();
		panelRegistro.setLayout(new GridLayout(5, 2));
		panelRegistro.setBackground(new Color(123, 104, 238));

		//Agregar cancion VISUAL
		etiquetaNombreCancion = new JLabel();
		etiquetaNombreCancion.setForeground(Color.white);
		etiquetaNombreAutor = new JLabel();
		etiquetaNombreAutor.setForeground(Color.white);
		etiquetaGeneroMusica = new JLabel();
		etiquetaGeneroMusica.setForeground(Color.white);
		etiquetaArchivo = new JLabel();
		etiquetaArchivo.setForeground(Color.white);

		campoTextoNombreCancion = new JTextField();
		campoTextoNombreAutor = new JTextField();
		campoTextoGeneroMusica = new JTextField();
		campoTextoArchivo = new JTextArea();
		campoTextoArchivo.setForeground(Color.pink);

		botonAgregar = new JButton();
		botonAgregar.setActionCommand(AGREGAR);
		botonAgregar.setEnabled(false);

		botonCancelar = new JButton();
		botonCancelar.setActionCommand(CANCELAR);
		botonCancelar.setEnabled(false);

		campoTextoArchivo.setEditable(false);
		campoTextoArchivo.setBackground(new Color(160, 32, 240));//Arrastrar el archivo

		modeloTabla = new DefaultTableModel();
		tablaCanciones = new JTable(modeloTabla);
		tablaCanciones.setEnabled(false);
		tablaCanciones.setDragEnabled(false);
		scroll = new JScrollPane(tablaCanciones);
		modeloTabla.addColumn("");
		modeloTabla.addColumn("");
		modeloTabla.addColumn("");
		modeloTabla.addColumn("");


		dropTarget = new DropTarget(campoTextoArchivo, this);
	}
	public void agregarComponentes() {
//Agrega los componentes visuales al panel.
		panelRegistro.add(etiquetaNombreCancion);
		panelRegistro.add(campoTextoNombreCancion);
		panelRegistro.add(etiquetaNombreAutor);
		panelRegistro.add(campoTextoNombreAutor);
		panelRegistro.add(etiquetaGeneroMusica);
		panelRegistro.add(campoTextoGeneroMusica);
		panelRegistro.add(etiquetaArchivo);
		panelRegistro.add(campoTextoArchivo);
		panelRegistro.add(botonAgregar);
		panelRegistro.add(botonCancelar);
		this.add(panelRegistro);
		this.add(scroll);

	}//Crea una tabla con los datos proporcionados.
	public void crearTabla(String[][] datos) {
		for (int i = 0; i < datos.length; i++) {
			modeloTabla.addRow(datos[i]);
		}
	}

	public void actualizarTabla(int contador, String cancion, String autor, String genero) {
		String[] datos = { Integer.toString(contador), cancion, autor, genero };
		modeloTabla.addRow(datos);
	}

	public void borrarCampos() {
		this.campoTextoNombreCancion.setText("");
		this.campoTextoNombreAutor.setText("");
		this.campoTextoGeneroMusica.setText("");
		this.campoTextoArchivo.setText("");
	}

	public void actualizarPanelAgregarCancion(String[] titulos) {
		setBorder(new TitledBorder(titulos[0]));

		etiquetaNombreCancion.setText(titulos[1]);
		etiquetaNombreAutor.setText(titulos[2]);
		etiquetaGeneroMusica.setText(titulos[3]);
		etiquetaArchivo.setText(titulos[4]);
		botonAgregar.setText(titulos[5]);
		botonCancelar.setText(titulos[6]);

		modeloTabla.fireTableStructureChanged();
		tablaCanciones.getColumn(tablaCanciones.getColumnName(0)).setHeaderValue(titulos[10]);
		tablaCanciones.getColumn(tablaCanciones.getColumnName(1)).setHeaderValue(titulos[7]);
		tablaCanciones.getColumn(tablaCanciones.getColumnName(2)).setHeaderValue(titulos[8]);
		tablaCanciones.getColumn(tablaCanciones.getColumnName(3)).setHeaderValue(titulos[9]);
		tablaCanciones.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaCanciones.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaCanciones.getColumnModel().getColumn(2).setPreferredWidth(150);
		tablaCanciones.getColumnModel().getColumn(3).setPreferredWidth(100);

	}

	public void dragEnter(DropTargetDragEvent dtde) {
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public void dragOver(DropTargetDragEvent dtde) {

	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
	}
	public void drop(DropTargetDropEvent dtde) {
		try {


			Transferable tr = dtde.getTransferable();

			DataFlavor[] flavors = tr.getTransferDataFlavors();
			for (int i = 0; i < flavors.length; i++) {


				if (flavors[i].isFlavorJavaFileListType()) {

				
					dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);


					@SuppressWarnings("rawtypes")
					java.util.List list = (java.util.List) tr.getTransferData(flavors[i]);

					for (int j = 0; j < list.size(); j++) {

				
						campoTextoArchivo.append(list.get(j) + "\n");
					}

					dtde.dropComplete(true);

					i = flavors.length;
				}

			}
			dtde.rejectDrop();

		} catch (Exception e) {

			dtde.rejectDrop();
		}
	}

	//Getters and Setters
	public JLabel getEtiquetaNombreCancion() {
		return etiquetaNombreCancion;
	}

	public void setEtiquetaNombreCancion(JLabel etiquetaNombreCancion) {
		this.etiquetaNombreCancion = etiquetaNombreCancion;
	}

	public JLabel getEtiquetaNombreAutor() {
		return etiquetaNombreAutor;
	}

	public void setEtiquetaNombreAutor(JLabel etiquetaNombreAutor) {
		this.etiquetaNombreAutor = etiquetaNombreAutor;
	}

	public JLabel getEtiquetaGeneroMusica() {
		return etiquetaGeneroMusica;
	}

	public void setEtiquetaGeneroMusica(JLabel etiquetaGeneroMusica) {
		this.etiquetaGeneroMusica = etiquetaGeneroMusica;
	}

	public JLabel getEtiquetaArchivo() {
		return etiquetaArchivo;
	}

	public void setEtiquetaArchivo(JLabel etiquetaArchivo) {
		this.etiquetaArchivo = etiquetaArchivo;
	}

	public JTextField getCampoTextoNombreCancion() {
		return campoTextoNombreCancion;
	}

	public void setCampoTextoNombreCancion(JTextField campoTextoNombreCancion) {
		this.campoTextoNombreCancion = campoTextoNombreCancion;
	}

	public JTextField getCampoTextoNombreAutor() {
		return campoTextoNombreAutor;
	}

	public void setCampoTextoNombreAutor(JTextField campoTextoNombreAutor) {
		this.campoTextoNombreAutor = campoTextoNombreAutor;
	}

	public JTextField getCampoTextoGeneroMusica() {
		return campoTextoGeneroMusica;
	}

	public void setCampoTextoGeneroMusica(JTextField campoTextoGeneroMusica) {
		this.campoTextoGeneroMusica = campoTextoGeneroMusica;
	}

	public JTextArea getCampoTextoArchivo() {
		return campoTextoArchivo;
	}

	public void setCampoTextoArchivo(JTextArea campoTextoArchivo) {
		this.campoTextoArchivo = campoTextoArchivo;
	}

	public JTable getTablaCanciones() {
		return tablaCanciones;
	}

	public void setTablaCanciones(JTable tablaCanciones) {
		this.tablaCanciones = tablaCanciones;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JButton getBotonAgregar() {
		return botonAgregar;
	}

	public void setBotonAgregar(JButton botonAgregar) {
		this.botonAgregar = botonAgregar;
	}

	public JButton getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(JButton botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public JPanel getPanelRegistro() {
		return panelRegistro;
	}

	public void setPanelRegistro(JPanel panelRegistro) {
		this.panelRegistro = panelRegistro;
	}

	public DropTarget getDropTarget() {
		return dropTarget;
	}

	public void setDropTarget(DropTarget dropTarget) {
		this.dropTarget = dropTarget;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAGREGAR() {
		return AGREGAR;
	}

	public String getCANCELAR() {
		return CANCELAR;
	}

	public DefaultTableModel getModeloTabla() {
		return modeloTabla;
	}

	public void setModeloTabla(DefaultTableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

}
