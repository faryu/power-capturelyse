package serviceLocator;

import java.util.Properties;
import javax.naming.InitialContext;
import org.jboss.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import interfaces.UserVerwaltungInterface;
import interfaces.VerbrauchVerwaltungInterface;

public class ServiceLocator implements ServiceLocatorInterface{
    
    static Logger logger = Logger.getLogger(ServiceLocator.class);
    
    private static final String URL_PKG_PREFIXES = "org.jboss.ejb.client.naming";  
    private static final String PROVIDER_URL = "remote://localhost:4447";  
    private static final String PREFIX = "ejb:";  
    private static final String SEPARATOR = "/";  
    transient private InitialContext ic;  
    private Properties initialContextProperties;
    
    private UserVerwaltungInterface myUserVerwaltung;
    private VerbrauchVerwaltungInterface myVerbrauchVerwaltung;
    private InitialContext ctx;
    static Context context = null;
    
    public void fillProperties() {  
        this.initialContextProperties = new Properties();  

        // We use the ejb: namespace (ejb client API-Project)  
        this.initialContextProperties.put(Context.URL_PKG_PREFIXES,
        	ServiceLocator.URL_PKG_PREFIXES);  

        // Fully qualified classname of the factory, which creates the InitialContext  
        this.initialContextProperties.put(Context.INITIAL_CONTEXT_FACTORY,  
                org.jboss.naming.remote.client.InitialContextFactory.class  
                        .getName());  

        // At which IP-Adress and at which Port we find JBoss 7?  
        // Default for JBoss AS 7 is Port 4447, instead of localhost you may use another concrete IP-adress  
        this.initialContextProperties.put(Context.PROVIDER_URL,  
                ServiceLocator.PROVIDER_URL);  

        // This generates automatically EJB receiver, otherwise we get an exception  
        this.initialContextProperties.put(  
                "jboss.naming.client.ejb.context", true);  

        // Without this we get a NullPointerException  
        this.initialContextProperties  
                .put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",  
                        "false");     
    }
    
    
    public ServiceLocator() {  
        this.fillProperties();                      
        try {  
            this.ic = new InitialContext(this.initialContextProperties);  
        } catch (NamingException e) {  
            e.printStackTrace(); 
        }  
    }  
    
    
    
    public String getGlobalJNDIName(String appName, String moduleName,String beanName, String ifName) {  
  
        return ServiceLocator.PREFIX + appName + ServiceLocator.SEPARATOR  
                + moduleName + ServiceLocator.SEPARATOR  
                + ServiceLocator.SEPARATOR + beanName + "!" + ifName;  
    } 
    
    public String getGlobalJNDINameStateful(String appName, String moduleName,String beanName, String ifName) {  
    	  
        return ServiceLocator.PREFIX + appName + ServiceLocator.SEPARATOR  
                + moduleName + ServiceLocator.SEPARATOR  
                + ServiceLocator.SEPARATOR + beanName + "?stateful" + ifName;  
    }  
    
    /** 
     *  
     * @param appName  
     *        application name of the deployed EJBs (.ear-name without suffix) 
     * @param moduleName 
     *        module name of the deployed EJBs (.jar-name without suffix or .war-name without suffix) 
     *        can be overridden via the ejb-jar.xml 
     * @param beanName 
     *        simple bean name without package 
     *        can be overridden in the @Stateful-annotation 
     * @param interfaceClazz 
     *        fully qualified name of the interface class 
     * @return proxy to the bean class 
     * @throws NamingException 
     * @throws ServiceLocatorException 
     */   
    @SuppressWarnings("unchecked")  
    public <T> T getStateless(String appName, String moduleName,String beanName, Class<T> interfaceClazz) throws NamingException{ 
        String fullIfName = interfaceClazz.getName();  
        String jndiName = this.getGlobalJNDINameStateful(appName, moduleName, beanName,  
                fullIfName);  
        return (T)ic.lookup(jndiName);  
        
    }  
    
    @SuppressWarnings("unchecked")  
    public <T> T getStateful(String appName, String moduleName,String beanName, Class<T> interfaceClazz) throws NamingException{ 
        String fullIfName = interfaceClazz.getName();  
        String jndiName = this.getGlobalJNDIName(appName, moduleName, beanName,  
                fullIfName);  
        return (T)ic.lookup(jndiName);  
        
    }  
           
    public void close() {  
        try {  
            this.ic.close();  
        } catch (NamingException e) {  
            e.printStackTrace();
        }  
    }  
    

    @Override
    public UserVerwaltungInterface getUserVerwaltung() {
	// TODO Automatisch generierter Methodenstub
	return myUserVerwaltung;
    }

    @Override
    public VerbrauchVerwaltungInterface getVerbrauchVerwaltung() {
	// TODO Automatisch generierter Methodenstub
	return myVerbrauchVerwaltung;
    }

}
