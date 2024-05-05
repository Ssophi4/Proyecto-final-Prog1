package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelDatosEmisora extends JPanel {

	//Atributos
	private JLabel etiquetaTituloNombre;
	private JLabel etiquetaNombreEmisora;
	private JLabel etiquetaTituloModo;
	private JLabel etiquetaModoTransmision;
	private JLabel etiquetaTituloTipoMusica;
	private JLabel etiquetaTipoMusica;
	private JLabel etiquetaGIFPlaying;
	private JPanel panelEmisoraInfo;
	private final String rutaGIFPlay = "./Imagenes/MusicaPlay.gif";
	private final String rutaGIFStop = "./Imagenes/MusicaStop.png";
	private Icon GIFPlay;
	private Icon GIFStop;

	private static final long serialVersionUID = 1L;

	//Constructor
	public PanelDatosEmisora() {// inicializa el diseño del panel a una cuadrícula de 2x1
		this.setLayout(new GridLayout(2, 1));
		this.GIFPlay = new ImageIcon(rutaGIFPlay);
		this.GIFStop = new ImageIcon(rutaGIFStop);
		this.asignarValores();
		this.agregarComponentes();
	}

	//metodos
	public void asignarValores() {
		panelEmisoraInfo = new JPanel();
		panelEmisoraInfo.setLayout(new GridLayout(3, 2));
		panelEmisoraInfo.setBackground(new Color(160, 32, 240));
		etiquetaTituloNombre = new JLabel();
		etiquetaTituloNombre.setForeground(Color.white);
		etiquetaTituloModo = new JLabel();
		etiquetaTituloModo.setForeground(Color.white);
		etiquetaTituloTipoMusica = new JLabel();
		etiquetaTituloTipoMusica.setForeground(Color.white);
		etiquetaNombreEmisora = new JLabel();
		etiquetaNombreEmisora.setForeground(Color.black);
		etiquetaModoTransmision = new JLabel();
		etiquetaModoTransmision.setForeground(Color.black);
		etiquetaTipoMusica = new JLabel();
		etiquetaTipoMusica.setForeground(Color.black);
		etiquetaGIFPlaying = new JLabel(GIFStop);

	}

	public void actualizarAtributos(String nombreEmisora, String modoTransmision, String tipoMusica) {
		this.etiquetaNombreEmisora.setText(nombreEmisora);
		this.etiquetaModoTransmision.setText(modoTransmision);
		this.etiquetaTipoMusica.setText(tipoMusica);
	}
	public void actualizarPanelDatosEmisora(String[] titulos) {
		setBorder(new TitledBorder(titulos[0]));
		etiquetaTituloNombre.setText(titulos[1]);
		etiquetaTituloModo.setText(titulos[2]);
		etiquetaTituloTipoMusica.setText(titulos[3]);
	}

	public void actualizarGIFStatus(Icon icon) {
		etiquetaGIFPlaying.setIcon(icon);
	}

	public void agregarComponentes() {

		panelEmisoraInfo.add(etiquetaTituloNombre);
		panelEmisoraInfo.add(etiquetaNombreEmisora);
		panelEmisoraInfo.add(etiquetaTituloModo);
		panelEmisoraInfo.add(etiquetaModoTransmision);
		panelEmisoraInfo.add(etiquetaTituloTipoMusica);
		panelEmisoraInfo.add(etiquetaTipoMusica);
		this.add(panelEmisoraInfo);
		this.add(etiquetaGIFPlaying);

	}
	//Getters y setters
	public JLabel getEtiquetaTituloNombre() {
		return etiquetaTituloNombre;
	}

	public void setEtiquetaTituloNombre(JLabel etiquetaTituloNombre) {
		this.etiquetaTituloNombre = etiquetaTituloNombre;
	}

	public JLabel getEtiquetaNombreEmisora() {
		return etiquetaNombreEmisora;
	}

	public void setEtiquetaNombreEmisora(JLabel etiquetaNombreEmisora) {
		this.etiquetaNombreEmisora = etiquetaNombreEmisora;
	}

	public JLabel getEtiquetaTituloModo() {
		return etiquetaTituloModo;
	}

	public void setEtiquetaTituloModo(JLabel etiquetaTituloModo) {
		this.etiquetaTituloModo = etiquetaTituloModo;
	}

	public JLabel getEtiquetaModoTransmision() {
		return etiquetaModoTransmision;
	}

	public void setEtiquetaModoTransmision(JLabel etiquetaModoTransmision) {
		this.etiquetaModoTransmision = etiquetaModoTransmision;
	}

	public JLabel getEtiquetaTituloTipoMusica() {
		return etiquetaTituloTipoMusica;
	}

	public void setEtiquetaTituloTipoMusica(JLabel etiquetaTituloTipoMusica) {
		this.etiquetaTituloTipoMusica = etiquetaTituloTipoMusica;
	}

	public JLabel getEtiquetaTipoMusica() {
		return etiquetaTipoMusica;
	}

	public void setEtiquetaTipoMusica(JLabel etiquetaTipoMusica) {
		this.etiquetaTipoMusica = etiquetaTipoMusica;
	}

	public JLabel getEtiquetaImagenPortada() {
		return etiquetaGIFPlaying;
	}

	public void setEtiquetaImagenPortada(JLabel etiquetaImagenPortada) {
		this.etiquetaGIFPlaying = etiquetaImagenPortada;
	}

	public JPanel getPanelEmisoraInfo() {
		return panelEmisoraInfo;
	}

	public void setPanelEmisoraInfo(JPanel panelEmisoraInfo) {
		this.panelEmisoraInfo = panelEmisoraInfo;
	}

	public JLabel getEtiquetaGIFPlaying() {
		return etiquetaGIFPlaying;
	}

	public void setEtiquetaGIFPlaying(JLabel etiquetaGIFPlaying) {
		this.etiquetaGIFPlaying = etiquetaGIFPlaying;
	}

	public String getRutaGIFPlay() {
		return rutaGIFPlay;
	}

	public String getRutaGIFStop() {
		return rutaGIFStop;
	}

	public Icon getGIFPlay() {
		return GIFPlay;
	}

	public void setGIFPlay(Icon gIFPlay) {
		GIFPlay = gIFPlay;
	}

	public Icon getGIFStop() {
		return GIFStop;
	}

	public void setGIFStop(Icon gIFStop) {
		GIFStop = gIFStop;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
