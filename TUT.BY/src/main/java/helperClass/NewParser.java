package helperClass;


import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NewParser {
    private static final String url = "jdbc:mysql://localhost:3306/userinfo";
    private static final String user = "root";
    private static final String password = "bcgfytw1992";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static String[] getDataFromSQL(){

        String query = "select usersLogin, usersPassword from usersinfo";
        String usersInfo[] = new String[20];
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            for(int i =0;rs.next();i+=2) {
                String usersLogin = rs.getString(1);
                usersInfo[i]=usersLogin;
                String usersPassword = rs.getString(2);
                usersInfo[i+1]=usersPassword;
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) {}
            try { stmt.close(); } catch(SQLException se) {}
            try { rs.close(); } catch(SQLException se) {}
        }

        return usersInfo;
    }


    public static String[] getDataFromXML(){
            String[] userInfo = new String[20];

        try {
            File inputFile = new File("userInfo.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("user");
            int a=0;

            for(int i=0;i<20;i+=2) {
                Node node = nList.item(a++);
                Element eElement = (Element) node;
                userInfo[i] = eElement.getElementsByTagName("username").item(0).getTextContent();
                userInfo[i+1] = eElement.getElementsByTagName("userpassword").item(0).getTextContent();
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public static String[] getDataFromCSV(){
        String[] userInfo = new String[20];

        String csvFile = "userInfo.csv";

        BufferedReader br = null;

        String line = "";

        String cvsSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                userInfo = line.split(cvsSplitBy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  userInfo;
    }
    public enum  Parsers{xml, csv, sql};

    public static String[] getDataFrom(Parsers parser) {
        String[] data = null;

        if(parser==Parsers.xml) {
            data = getDataFromXML();
        } else if(parser==Parsers.csv) {
            data = getDataFromCSV();
        } else if(parser==Parsers.sql){
            data = getDataFromSQL();
        }
        return data;
    }
}


