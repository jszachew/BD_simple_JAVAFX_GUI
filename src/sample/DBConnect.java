package sample;

import java.sql.*;

public class DBConnect {

    public Connection con;
    public  Statement st;
    public ResultSet rs;
    private static int nQuery=0;
    //private Controller controller;

    public DBConnect ()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hurtownia_kosmetykow","root","");
            st = con.createStatement();
        }
        catch (Exception ex)
        {
            System.out.println("ERROR: "+ ex);
        }
    }

    public ResultSet getData(String table)
    {
        try
        {
            String query = "select * from "+ table;
            rs= st.executeQuery(query);
            nQuery++;
            System.out.println(nQuery + " query made");
            return rs;
        }
        catch (Exception ex)
        {
            System.out.println("err:" + ex);
        }
        return rs;
    }

    public void addEmployee(String Imie, String Nazwisko, int Pensja, int Staz_prazy, String Stanowisko) throws SQLException {
       String query=  "INSERT INTO pracownicy (Imie, Nazwisko, Pensja, Staz_pracy,Stanowsko) VALUES (\"" +Imie +"\",\"" + Nazwisko+"\"," + Integer.toString(Pensja)+","+Integer.toString(Staz_prazy)+",\""+Stanowisko + "\");";
       System.out.println(query);
       addToDB(query);
    }
    public void removeEmployee(int toDel) throws SQLException {
        String query= "DELETE FROM pracownicy WHERE pracownicy.idPracownicy = " + toDel +";";
        System.out.println(query);
        addToDB(query);

    }

    public void updateDepartments(int IDdzialu, String Nazwa, int IDKierownika) throws SQLException {
        String query = "UPDATE dzialy SET iddzialy = " +IDdzialu +" , Nazwa_dzialu = \"" +Nazwa + "\" , pracownicy_idPracownicy_kierownik= "+IDKierownika  + " WHERE iddzialy="+IDdzialu+";";
        System.out.println(query);
        addToDB(query);
    }

    public void addToDB(String query) throws SQLException
    {
        st.executeUpdate(query);
    }

}
