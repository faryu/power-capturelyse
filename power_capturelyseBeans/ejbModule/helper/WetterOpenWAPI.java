package helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import entity.Wetter;


public class WetterOpenWAPI 
{	
	private static Document xmlStringToDoc(String xml)
	{
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=null;
		try {
				docBuilder = dbfac.newDocumentBuilder();
			} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		Document doc=null;        
		try 
		{
			doc = docBuilder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}
	
	private static String search(String plz)
    {	
		String sProjectName   = "mwawetter";
		String sApiKey        = "949b3d483eb403e3d38eb7ebb5bcbf40";		
		String hash = Helper.md5Java(sProjectName + sApiKey + plz);
		String url = "http://api.wetter.com/location/plz/search/"+plz+"/project/"+sProjectName+"/cs/"+hash;
		String xml="", result="";
		
		URL urlU;
		try 
		{
			urlU = new URL(url);		
			HttpURLConnection con = (HttpURLConnection) urlU.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/timestamp-query");
			con.connect();
			String out = con.getResponseMessage();
			if (out.equals("OK"))
			{
				InputStream streamInput = con.getInputStream();
				InputStreamReader streamReader = new InputStreamReader(streamInput); 
	
				//put output stream into a string
				BufferedReader br = new BufferedReader(streamReader);
				//put output stream into a string
				String line;
				while ((line = br.readLine()) != null) 
				{					
					result+= line; //System.out.println(line);
				}
			}
			else
				System.out.println("Seite nicht erreichbar.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
		//System.out.println("City URL: " + url);
        xml = result;         
        Document doc = xmlStringToDoc(xml);
        if (!xml.contains("Input error"))
        	return doc.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue()+","+doc.getElementsByTagName("adm_1_code").item(0).getChildNodes().item(0).getNodeValue();
        return "";
//		
//		System.out.println("City URL: " + url);
//		ClientRequest cr = new ClientRequest(url);
//		String result="";
//		try {
//			result = cr.get(String.class).getEntity();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        //WebResource wr = Client.create().resource(url);
//        String xml = result;//wr.accept("text/xml").get(String.class);        
//        Document doc = xmlStringToDoc(xml);
//        if (!xml.contains("Input error"))
//        	return doc.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue()+","+doc.getElementsByTagName("adm_1_code").item(0).getChildNodes().item(0).getNodeValue();
//        return "";
    }
	
	private static String getWetter(String cityName)
    {
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&mode=xml&units=metric";
        //System.out.println("Wetter URL: "+ url);
        String xml="", result="";
		
		URL urlU;
		try 
		{
			urlU = new URL(url);		
			HttpURLConnection con = (HttpURLConnection) urlU.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/timestamp-query");
			con.connect();
			String out = con.getResponseMessage();
			if (out.equals("OK"))
			{
				InputStream streamInput = con.getInputStream();
				InputStreamReader streamReader = new InputStreamReader(streamInput); 
	
				//put output stream into a string
				BufferedReader br = new BufferedReader(streamReader);
				//put output stream into a string
				String line;
				while ((line = br.readLine()) != null) 
				{					
					result+= line; //System.out.println(line);
				}
			}
			else
				System.out.println("Seite nicht erreichbar.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        xml = result;
        return xml;
    }	
	
	public static List<Wetter> abfrageStarten(List<String> allePlz) 
	{
		//String[] sAllePLZTest   = {"46414","46399", "20095", "10115", "56"};
		List<Wetter> wetterdatenEntitys=new ArrayList<Wetter> ();
		System.out.println("Starte Wetterabfrage für "+allePlz.size()+" PLZ.");
		for (String plz:allePlz)
		{
			String ort = search(plz);
			if (ort!="")
			{
				String wetterdaten = getWetter(ort);
				Document doc = xmlStringToDoc(wetterdaten);				
				String temp = doc.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("value").getTextContent();
				System.out.println("Timestamp "+doc.getElementsByTagName("lastupdate").item(0).getAttributes().item(0).getTextContent()+" für " + plz + " "+ort+", "+temp); //System.out.println("Aktuell: " + temp +"\r");
				//BigDecimal bigTemp = new BigDecimal(temp);
				wetterdatenEntitys.add(new Wetter((Integer.parseInt(plz)), new BigDecimal(temp), new Timestamp(System.currentTimeMillis())));
			}
			else
				System.out.println("Keinen Ort zu PLZ "+plz +" gefunden.");
		}		
		return wetterdatenEntitys;
	}

}
