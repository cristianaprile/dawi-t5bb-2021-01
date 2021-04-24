package org.ciberfarma.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ciberfarma.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmCrudUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JTextField txtClave;
	private JTextField txtFech;
	private JTextField txtTipo;
	private JTextField txtEstado;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudUsuario frame = new FrmCrudUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código :");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario :");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clave :");
		lblNewLabel_4.setBounds(193, 86, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fech Nac :");
		lblNewLabel_5.setBounds(10, 130, 63, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tipo :");
		lblNewLabel_6.setBounds(10, 173, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Estado:");
		lblNewLabel_7.setBounds(193, 173, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(83, 8, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(83, 33, 194, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(83, 58, 194, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(83, 86, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setBounds(234, 83, 96, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		txtFech = new JTextField();
		txtFech.setBounds(83, 127, 86, 20);
		contentPane.add(txtFech);
		txtFech.setColumns(10);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(83, 170, 86, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(253, 170, 96, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registro();
			}
		});
		btnNewButton.setBounds(356, 32, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Consultar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnNewButton_1.setBounds(356, 82, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Listado");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnNewButton_2.setBounds(356, 126, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 212, 435, 166);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
	}

	 protected void listado() {
		
		 // Obtener un listado de los Usuarios 
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");	 
		 EntityManager em = fabrica.createEntityManager();
		 
		 List<Usuario> lsUsuarios;
		if (txtTipo.getText().isEmpty()) {
			lsUsuarios = em.createNamedQuery("Usuario.findAll" , Usuario.class).getResultList();
			
		} else {
			
			int tipo = Integer.parseInt(txtTipo.getText());
			lsUsuarios = em.createNamedQuery("Usuario.finAllxTipo" , Usuario.class).setParameter("xtipo", tipo).getResultList();
			
		}
		
		 
		 // Mostrar listado en el  txt/pdf
		
		txtS.setText("Listado de Usuario\n");
		for (Usuario u : lsUsuarios) {
			txtS.append(u.getCodigo()  + " \t" + u.getNombre() + " \t" + u.getApellido() + "\n");
		}
		
	}

	protected void consultar() {
		
		 // Obtener código a buscar
		 
		 int codigo = Integer.parseInt(txtCodigo.getText());
		 
		// Buscar el código en los Usuarios(Entidad), si existe muestra los datos, y si no avisa
        
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");	 
		 EntityManager em = fabrica.createEntityManager();
		 
		 Usuario u = em.find(Usuario.class, codigo);
		 
		 if (u == null) {
			 JOptionPane.showMessageDialog(this, "USUARIO NO REGISTRADO");
		 } else {
			 txtNombre.setText(u.getNombre());
			 txtApellido.setText(u.getApellido());
			 txtUsuario.setText(u.getUsuario());
			 txtClave.setText(u.getClave());
			 txtFech.setText(u.getFnacim());
			 txtTipo.setText(u.getTipo()+"");
			 txtEstado.setText(u.getEstado()+"");
		 }
	}

	void registro() {
		 
		// Variables de Entradas 
		 
		 String nombre = txtNombre.getText();
		 String apellido = txtApellido.getText();
		 String usuario = txtUsuario.getText();
		 String clave = txtClave.getText();
		 String fecha = txtFech.getText();
		 int tipo = Integer.parseInt(txtTipo.getText());
		 int estado = Integer.parseInt(txtEstado.getText());
		 
		 Usuario u = new Usuario();
		 u.setNombre(nombre);
		 u.setApellido(apellido);
		 u.setUsuario(usuario);
		 u.setClave(clave);
		 u.setFnacim(fecha);
		 u.setTipo(tipo);
		 u.setEstado(estado);
		
		 // Proceso 
		 
		 EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");
		 
		 EntityManager em = fabrica.createEntityManager();
		 
		 try {
			 em.getTransaction().begin();
			 em.persist(u); 
		     em.getTransaction().commit();     
		     JOptionPane.showMessageDialog(this, "Usuario registrado");
		} catch (Exception e) {
		     JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage() );
		}finally {
			em.close();
		}
	     	 
	}
}
