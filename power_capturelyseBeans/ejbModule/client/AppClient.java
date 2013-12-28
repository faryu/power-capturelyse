package client;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import entity.Adresse;
import entity.User;
import serviceLocator.ServiceLocator;
import interfaces.AdressVerwaltungInterface;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;


public class AppClient extends JFrame implements ActionListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
    private static UserVerwaltungInterface userverwaltung;
    
    @EJB
    private static VerbrauchVerwaltungInterface verbrauchsverwaltung;
    
    @EJB 
    private static AdressVerwaltungInterface adresseverwaltung;
    
    public JLabel lblNachname;
    public JTextField txtNachname;
    public JLabel lblVorname;
    public JTextField txtVorname;
    public JLabel lblPassword;
    public JPasswordField pwPassword;   
    public JLabel lblStrasse;
    public JTextField txtStrasse;
    public JLabel lblOrt;
    public JTextField txtOrt;
    public JLabel lblPlz;
    public JTextField txtPlz;
    
    public JLabel lblVerbrauch;
    public JTextField txtVerbrauch;
    
    public JButton btnAdd;
    public JButton btnUpdate;
    
    
    public AppClient(){
    	super("Verbrauchsvergleich");
    	this.setSize(550,400);
    	this.getContentPane().setLayout(null);
    	
    	lblNachname = new JLabel("Nachname");
    	lblNachname.setBounds(10,10,150,20);
    	lblVorname = new JLabel("Vorname");
    	lblVorname.setBounds(10,50,150,20);
    	lblPassword = new JLabel("Password");
    	lblPassword.setBounds(10,90,150,20);
    	lblStrasse = new JLabel("Strasse");
    	lblStrasse.setBounds(10,130,150,20);
    	lblOrt = new JLabel("Ort");
    	lblOrt.setBounds(10,170,150,20);
    	lblPlz = new JLabel("PLZ");
    	lblPlz.setBounds(10,200,150,20);
    	lblVerbrauch = new JLabel("Verbrauch");
    	lblVerbrauch.setBounds(10,230,150,20);
    	txtNachname = new JTextField();
    	txtNachname.setBounds(180,10,100,20);
    	txtVorname = new JTextField();
    	txtVorname.setBounds(180,50,100,20);
    	pwPassword = new JPasswordField();
    	pwPassword.setBounds(180,90,100,20);
    	
    	txtOrt = new JTextField();
    	txtOrt.setBounds(180,170,100,20);
    	txtStrasse = new JTextField();
    	txtStrasse.setBounds(180,130,100,20);
    	txtPlz = new JTextField();
    	txtPlz.setBounds(180,200,100,20);
    	txtVerbrauch = new JTextField();
    	txtVerbrauch.setBounds(180,220,100,20);
    	
    	btnAdd = new JButton("Hinzufügen");
    	btnAdd.setBounds(10,250,100,40);
    	btnAdd.addActionListener(this);
    	btnUpdate = new JButton("Update");
    	btnUpdate.setBounds(150,250,100,40);
    	btnUpdate.addActionListener(this);
    	
    	this.getContentPane().add(lblNachname);
    	this.getContentPane().add(txtNachname);
    	this.getContentPane().add(lblVorname);
    	this.getContentPane().add(txtVorname);
    	this.getContentPane().add(pwPassword);
    	this.getContentPane().add(lblPassword);
    	this.getContentPane().add(lblOrt);
    	this.getContentPane().add(txtOrt);
    	this.getContentPane().add(lblStrasse);
    	this.getContentPane().add(txtStrasse);
    	this.getContentPane().add(lblPlz);
    	this.getContentPane().add(txtPlz);
    	this.getContentPane().add(btnAdd);
    	this.getContentPane().add(btnUpdate);
    	
    	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    	this.setLocation((d.width-this.getWidth())/2, (d.height-this.getHeight())/2);
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(true);
    	this.setVisible(true);
    	
    }
    
	public static void main (String[]args) throws NamingException{
	    	new AppClient();
	    	
	    	ServiceLocator locator = new ServiceLocator();
	    	userverwaltung = (UserVerwaltungInterface)locator.getStateless("", "power_capturelyseBeans", "UserVerwaltungBean", UserVerwaltungInterface.class);
//	    	ServiceLocator locator2 = new ServiceLocator();
//	    	verbrauchsverwaltung = (VerbrauchVerwaltungInterface)locator2.getStateless("", "power_capturelyseBeans", "VerbrauchVerwaltungBean", VerbrauchVerwaltungInterface.class);
	    	User user = new User();	    	
//	    	Adresse adresse = new Adresse("Schwanenstr. 70a", 46399, "Bocholt", user);
//	    	Verbrauch verbrauch = new Verbrauch();
	    	
//	    	user = userverwaltung.createUser(user,adresse);
//	    	int id_user = user.getId_user();
//	    	System.out.println(userverwaltung.findUser(id_user));
	    		    	
//	    	System.out.println(id_user);
//	    	System.out.println(adresse);
		
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void printList(List list) {
	        for (Object elem: list) {
	            if (elem.getClass().isArray()) {
	                for (Object arrElem: (Object[])elem)
	                    System.out.print(arrElem + " ");
	                System.out.println();
	            }
	            else
	                System.out.println(elem);
	        }
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdd){
			try{
				
				String nachname = txtNachname.getText();
				String vorname = txtVorname.getText();
				@SuppressWarnings("deprecation")
				String password = pwPassword.getText();
				String strasse = txtStrasse.getText();
				String ort = txtOrt.getText();
				Integer plz = Integer.parseInt(txtPlz.getText());
				User user = new User(nachname, vorname, password);
				Adresse adresse = new Adresse(strasse, plz, ort, user);
				user = userverwaltung.createUser(user, adresse);
				int id_user = user.getId_user();
		    	System.out.println(userverwaltung.findUser(id_user));
				
				//Diese Exception wird auf jeden Fall ausgeloest, keine Ahnung, warum!?
			}catch(Exception ex){
				JOptionPane.showMessageDialog(this,"Remote Exception !!");
				ex.printStackTrace();
			}
		}
		
	}
	

}

