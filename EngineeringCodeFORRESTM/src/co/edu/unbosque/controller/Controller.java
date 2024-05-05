package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.model.CancionIgualException;
import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.ExtensionIncorrectaException;
import co.edu.unbosque.view.View;

public class Controller implements ActionListener {

	private View vista;
	private Emisora emisora;

	public Controller() {
		this.vista = new View(this);
		this.emisora = new Emisora();
		this.iniciarEmisora();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub

		if (event.getActionCommand().equals(vista.getPanelInformacion().getPanelInformacionEmisora().getGUARDAR())) {
			this.gestionarDatosEmisora();
			this.vista.getPanelEmisora().getPanelDatosEmisora().actualizarAtributos(this.emisora.getNombreEmisora(),
					this.emisora.getModoTransmision(), this.emisora.getTipoDeMusica());
			this.emisora.escribirArchivoEmisora();
			this.vista.habilitarBotones();

		} else if (event.getActionCommand()
				.equals(vista.getPanelInformacion().getPanelInformacionEmisora().getCANCELAR())) {
			this.vista.getPanelInformacion().getPanelInformacionEmisora().restablecerCampos(
					this.emisora.getNombreEmisora(), this.emisora.getTipoDeMusica(), this.emisora.getModoTransmision(),
					this.emisora.getIdioma());
		} else if (event.getActionCommand().equals(vista.getPanelInformacion().getPanelAgregarCancion().getAGREGAR())) {
			this.gestionarIngresoCancion();

		} else if (event.getActionCommand()
				.equals(vista.getPanelInformacion().getPanelAgregarCancion().getCANCELAR())) {
			this.vista.getPanelInformacion().getPanelAgregarCancion().borrarCampos();

		} else if (event.getActionCommand()
				.equals(vista.getPanelEmisora().getPanelParrilla().getCOMMAND_AGREGAR_PARRILLA())) {
			this.gestionarAgregarCancionParrilla();

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelReproduccion().getCOMMAND_REPRODUCIR())) {
			this.emisora.getParrillaMusical().reproducir();
			vista.getPanelEmisora().getPanelDatosEmisora()
					.actualizarGIFStatus(vista.getPanelEmisora().getPanelDatosEmisora().getGIFPlay());

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelReproduccion().getCOMMAND_PAUSAR())) {
			this.emisora.getParrillaMusical().pausar();
			vista.getPanelEmisora().getPanelDatosEmisora()
					.actualizarGIFStatus(vista.getPanelEmisora().getPanelDatosEmisora().getGIFStop());

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelReproduccion().getCOMMAND_PARAR())) {
			this.emisora.getParrillaMusical().parar();
			vista.getPanelEmisora().getPanelDatosEmisora()
					.actualizarGIFStatus(vista.getPanelEmisora().getPanelDatosEmisora().getGIFStop());

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelReproduccion().getCOMMAND_AVANZAR())) {
			this.emisora.getParrillaMusical().reproducirSiguiente();

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelReproduccion().getCOMMAND_RETROCEDER())) {
			this.emisora.getParrillaMusical().reproducirAnterior();

		} else if (event.getActionCommand()
				.equals(this.vista.getPanelEmisora().getPanelParrilla().getCOMMAND_BORRAR_PARRILLA())) {
			this.gestionarBorradoParrilla();
		}
	}
	public void gestionarDatosEmisora() {
		if (!this.vista.getPanelInformacion().getPanelInformacionEmisora().getCampoTextoNombreEmisora().getText()
				.equals("")
				&& !this.vista.getPanelInformacion().getPanelInformacionEmisora().getComboModoTransmision()
						.getSelectedItem().equals("Seleccione...")
				&& !this.vista.getPanelInformacion().getPanelInformacionEmisora().getComboModoTransmision()
						.getSelectedItem().equals("Please Select")
				&& !this.vista.getPanelInformacion().getPanelInformacionEmisora().getCampoTextoTipoMusica().getText()
						.equals("")) {
			String nombreEmisora = this.vista.getPanelInformacion().getPanelInformacionEmisora()
					.getCampoTextoNombreEmisora().getText();
			String modoTransmision = (String) this.vista.getPanelInformacion().getPanelInformacionEmisora()
					.getComboModoTransmision().getSelectedItem();
			String tipoMusica = this.vista.getPanelInformacion().getPanelInformacionEmisora().getCampoTextoTipoMusica()
					.getText();
			String idioma = "";
			if (this.vista.getPanelInformacion().getPanelInformacionEmisora().getRadio_EN().isSelected()) {
				idioma = "EN";
			} else {
				idioma = "ES";
			}
			this.emisora.asignarInformacionEmisora(nombreEmisora, modoTransmision, tipoMusica, idioma);
			this.emisora.escribirArchivoEmisora();
			this.emisora.gestionarCaracteristicas();
			this.actualizarComponentesVista();

		} else {
			vista.mostrarMensajeError(this.emisora.getMensajesError()[0]);
			this.vista.getPanelInformacion().getPanelInformacionEmisora().restablecerCampos(
					this.emisora.getNombreEmisora(), this.emisora.getTipoDeMusica(), this.emisora.getModoTransmision(),
					this.emisora.getIdioma());
		}
	}

	public void gestionarIngresoCancion() {
		if (!this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoNombreCancion().getText().equals("")
				&& !this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoNombreAutor().getText()
						.equals("")
				&& !this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoGeneroMusica().getText()
						.equals("")
				&& !this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoArchivo().getText()
						.equals("")) {
			String nombreCancion = this.vista.getPanelInformacion().getPanelAgregarCancion()
					.getCampoTextoNombreCancion().getText();
			String nombreArtista = this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoNombreAutor()
					.getText();
			String genero = this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoGeneroMusica()
					.getText();
			String rutaArchivo = this.vista.getPanelInformacion().getPanelAgregarCancion().getCampoTextoArchivo()
					.getText();
			try {
				this.emisora.verificarExtensionArchivo(rutaArchivo, nombreCancion);

				if (this.emisora.agregarPistaMusical(nombreCancion, nombreArtista, genero, nombreCancion + ".mp3")
						&& this.emisora.getArchivo().copiarCancion(rutaArchivo, nombreCancion)) {
					int contador = emisora.getContadorCanciones();
					emisora.setContadorCanciones(contador + 1);
					this.vista.getPanelInformacion().getPanelAgregarCancion().actualizarTabla(contador, nombreCancion,
							nombreArtista, genero);
					this.vista.getPanelEmisora().getPanelParrilla().getComboNombreCancion().addItem(nombreCancion);
					vista.mostrarMensajeAviso(this.emisora.getMensajeAceptar());
					this.vista.getPanelInformacion().getPanelAgregarCancion().borrarCampos();
				} else {
					vista.mostrarMensajeError(this.emisora.getMensajesError()[1]);
					this.vista.getPanelInformacion().getPanelAgregarCancion().borrarCampos();
				}
			} catch (ExtensionIncorrectaException | CancionIgualException e) {
				// TODO Auto-generated catch block
				vista.mostrarMensajeError(this.emisora.getMensajesError()[4]);
				this.vista.getPanelInformacion().getPanelAgregarCancion().borrarCampos();
			}

		} else {
			vista.mostrarMensajeError(this.emisora.getMensajesError()[0]);
			this.vista.getPanelInformacion().getPanelAgregarCancion().borrarCampos();
		}

	}
	public void gestionarAgregarCancionParrilla() {
		if (!vista.getPanelEmisora().getPanelParrilla().getComboNombreCancion().getSelectedItem()
				.equals("Seleccionar canción")
				&& !vista.getPanelEmisora().getPanelParrilla().getComboNombreCancion().getSelectedItem()
						.equals("Select Song")) {
			String cancion = (String) vista.getPanelEmisora().getPanelParrilla().getComboNombreCancion()
					.getSelectedItem();
			emisora.gestionarParrilla(cancion);
			this.emisora.getParrillaMusical().agregarCancionPlayList();
			String[] tabla = emisora.llenarParrilla(cancion);
			int contador = emisora.getParrillaMusical().getContadorParrilla();
			if (contador <= 1) {
				this.emisora.getParrillaMusical().reproducirSiguiente();
				this.emisora.getParrillaMusical().parar();
			}
			emisora.getParrillaMusical().setContadorParrilla(contador + 1);
			vista.getPanelEmisora().getPanelParrilla().actualizarTabla(contador, tabla[0], tabla[1], tabla[2]);
			this.vista.getPanelEmisora().getPanelReproduccion().manejarBotones(true);

		} else {
			this.vista.mostrarMensajeError(this.emisora.getMensajesError()[0]);
		}
	}
	public void gestionarBorradoParrilla() {

		this.emisora.getParrillaMusical().parar();
		this.vista.getPanelEmisora().getPanelDatosEmisora()
				.actualizarGIFStatus(vista.getPanelEmisora().getPanelDatosEmisora().getGIFStop());
		this.vista.getPanelEmisora().getPanelParrilla().borrarContenidoTabla();
		this.emisora.getParrillaMusical().borrarParrilla();
		this.emisora.getArchivo().borrarArchivoParrilla();
		this.emisora.getParrillaMusical().setContadorParrilla(1);
		this.vista.getPanelEmisora().getPanelReproduccion().manejarBotones(false);

	}
	public void llenarDatosEmisoraTablas() {
		this.vista.getPanelEmisora().getPanelDatosEmisora().actualizarAtributos(this.emisora.getNombreEmisora(),
				this.emisora.getModoTransmision(), this.emisora.getTipoDeMusica());
		this.vista.getPanelInformacion().getPanelInformacionEmisora().cargarCampos(this.emisora.getNombreEmisora(),
				this.emisora.getModoTransmision(), this.emisora.getTipoDeMusica(), this.emisora.getIdioma());
		this.vista.getPanelInformacion().getPanelAgregarCancion()
				.crearTabla(this.emisora.asignarDatosTablaAgregarCancion());

		this.vista.getPanelEmisora().getPanelParrilla().crearTabla(this.emisora.asignarDatosTablaParrilla());
		if (this.emisora.getParrillaMusical().getPistasMusicales().size() == 0) {
			this.vista.getPanelEmisora().getPanelReproduccion().manejarBotones(false);
		}
	}

	public void iniciarEmisora() {
		this.emisora.gestionarCaracteristicas();
		this.emisora.cargarAtributosArchivoPistas();
		this.emisora.cargarInformacionArchivoParrilla();
		this.emisora.getParrillaMusical().generarPlayList();
		this.actualizarComponentesVista();
		this.llenarDatosEmisoraTablas();
		this.verificarDatosEmisora();
	}
	public void actualizarComponentesVista() {
		this.vista.actualizarView(this.emisora.getTitulosView());
		this.vista.getPanelEmisora().getPanelParrilla().actualizarPanelParrilla(this.emisora.getTitulosPanelParrilla());
		this.vista.getPanelEmisora().getPanelParrilla()
				.agregarCancionesComboBox(this.emisora.generarListaDeCancionesDisponibles());
		this.vista.getPanelEmisora().getPanelDatosEmisora()
				.actualizarPanelDatosEmisora(this.emisora.getTitulosPanelDatosEmisora());
		this.vista.getPanelInformacion().getPanelInformacionEmisora().actualizarPanelInformacionEmisora(
				this.emisora.getTitulosPanelInformacionEmisora(), this.emisora.getModoTransmision());
		this.vista.getPanelInformacion().getPanelAgregarCancion()
				.actualizarPanelAgregarCancion(this.emisora.getTitulosPanelAgregarCancion());
		this.vista.getPanelInformacion().getPanelAgregarCancion().getModeloTabla().fireTableDataChanged();
	}
	public void verificarDatosEmisora() {

		if (this.emisora.getNombreEmisora().equals("")) {
			this.vista.mostrarMensajeAviso(
					"Por favor ingresar los datos de la emisora.\nPlease fill the station information.");
			this.vista.getPestanas().setSelectedIndex(1);
		} else {
			this.vista.habilitarBotones();
		}

	}

}