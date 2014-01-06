package client;

import interfaces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;	

import	javax.naming.Context;	
import	javax.naming.NamingException;

import sessionBeans.*;
import client.ClientUtility;


public class SessionBeanClient {


public	static	void	main(String[]	args) throws IOException	{	
		

	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
					
	System.out.println("Bitte geben Sie einen Usernamen ein.");
	String user = console.readLine();
	System.out.println("Bitte geben Sie das zugehoerige Passwort ein.");
	String password = console.readLine();	
	
	
	SessionRemoteInterface	remote	=	doLookup();	
	
	if(remote.identityCheck(user, password)==false)
	{
		System.out.println("Ihr Username oder Password ist fehlerhaft");
	}
	else
	{
		System.out.println("Sie haben sich erfolgreich angemeldet, ");//+remote.whoIam());
	}
	
}	

private	static	SessionRemoteInterface	doLookup()	{	
	Context	context	=	null;	
	SessionRemoteInterface	remote	=	null;	
	try	{	
	 	//	1.	Obtaining	Context	
	 	context	=	ClientUtility.getInitialContext();	
	 	//	2.	Generate	JNDI	Lookup	name	
	 	String	lookupName	=	getLookupName();	
	 	//	3.	Lookup	and	cast	
	 	remote	=	(SessionRemoteInterface)	context.lookup(lookupName);	
	
	}	catch	(NamingException	e)	{	
	e.printStackTrace();	
	}	
return	remote;	
}	

	
private	static	String	getLookupName()		
{	
	String	appName	=	"pcl-eap";	
	
	/*	
		*	The	module	name	is	the	JAR	name	of	the	deployed	EJB	without	the	.jar	
		*	suffix.	
		*/	
	String	moduleName	=	"power_capturelyseBeans";	
		
	/*	
		*	AS7	allows	each	deployment	to	have	an	(optional)	distinct	name.	This	
		*	can	be	an	empty	string	if	distinct	name	is	not	specified.	
		*/	
	String	distinctName	=	"";	
		
	//	The	EJB	bean	implementation	class	name	
	String	beanName	=	SessionBean.class.getSimpleName();	
		
	//	Fully	qualified	remote	interface	name	
	final	String	interfaceName	=	SessionRemoteInterface.class.getName();	
	
	

	//	Create	a	look	up	string	name	
	String	name	=	"ejb:"	+	appName	+	"/"	+	moduleName	+	"/"	+	distinctName	
	+	"/"	+	beanName	+	"!"	+	interfaceName + "?stateful";
	System.out.println(name);
	return	name;	
	}	
}	
