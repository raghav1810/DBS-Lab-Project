/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbs.lab.project;

import java.sql.*;

/**
 *
 * @author raghav
 */
public class DBSLabProject {

    /**
     * @param args the command line arguments
     */
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/course_payment";
    static Connection conn = null;
    static Statement stmt = null;
    
    static String cust_id_global;
    
    static String freecourses_vals[][]=new String[250][2];
    static String freecourses_columns[] = {"Customer ID", "Name"};
    
    static String domain_vals[][]=new String[250][1];
    static String domain_columns[] = {"Cost of each domain"};
    
    static String stats_vals[][]=new String[250][2];
    static String stats_columns[] = {"Customer ID", "Name"};
    
    public static boolean loginAuth(String id, String pwd){
		try{
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM customer WHERE customer_id="+id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()==false)
				return false;

			String pwd_db = rs.getString("password");
			if (pwd.equals(pwd_db)){
                                cust_id_global=id;
                                System.out.println("c_id= "+cust_id_global);
				return true;
                        }
			else
				return false;
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
		return false;
	}
    
    public static void addCustomer(String uname, String name, String pass, String college, String city, String email){
		try{
			stmt = conn.createStatement();
			String sql;
			sql = "insert into customer values("+ uname +", '"+ name +"', '" + pass +"', '"+ college +"', '"+city+"', '"+email+"')";
			stmt.executeUpdate(sql);
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
	}
    
    public static void freecourses(String p_id){
    	try{
			stmt = conn.createStatement();
			String sql;
			sql = "select course_name, course_id "
                                + "from course "
                                + "where course_id in (select course_id "
                                + "from professor "
                                + "where professor_id="+p_id+") and cost=0";
			ResultSet rs = stmt.executeQuery(sql);
                        int i=0;
			while(rs.next()){
				//Retrieve by column name
				int id  = rs.getInt("course_id");
				String name = rs.getString("course_name");
                                freecourses_vals[i][0] = Integer.toString(id);
                                freecourses_vals[i][1] = name;
			}
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
	}
    
    public static void domainCost(String d_id){
    	try{
			stmt = conn.createStatement();
			String sql;
			sql = "select sum(cost) as DomainCost from course where course_id in "+
			"(select course_id from specialization where domain_id="+d_id+")";
			ResultSet rs = stmt.executeQuery(sql);
                        int i=0;
			while(rs.next()){
				//Retrieve by column name
				String cost = Integer.toString(rs.getInt("DomainCost"));
                                domain_vals[i][0] = cost;
			}
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
	}
    
    public static void statsfn(String c_id){
    	try{
			stmt = conn.createStatement();
			String sql;
			sql = "select customer_id, customer_name from customer where customer_id in (select customer_id from completed_courses where course_id="+c_id+")";
			ResultSet rs = stmt.executeQuery(sql);
                        int i=0;
			while(rs.next()){
				//Retrieve by column name
				String name = rs.getString("customer_name");
				String id  = Integer.toString(rs.getInt("customer_id"));
                                stats_vals[i][0] = id;
                                stats_vals[i][1] = name;
			}
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
	}
    
    public static int getDomainCost(String d_id){
		try{
			stmt = conn.createStatement();
			CallableStatement cStmt = conn.prepareCall("{call testproc1(?, ?)}");
			cStmt.setInt(1, Integer.parseInt(d_id));
			cStmt.registerOutParameter(2, Types.INTEGER);

			
			boolean hadResults = cStmt.execute();
		    while (hadResults) {
		        ResultSet rs = cStmt.getResultSet();
		        hadResults = cStmt.getMoreResults();
		    }
		    int outputValue = cStmt.getInt(2); // index-based

			return outputValue;
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
		return -1;
	}
    
    public static int getTotalStudents(String d_id){
		try{
			// stmt = conn.createStatement();
			CallableStatement cStmt = conn.prepareCall("{call totstu(?, ?)}");
			cStmt.setInt(1, Integer.parseInt(d_id));
			cStmt.registerOutParameter(2, Types.INTEGER);

			
			boolean hadResults = cStmt.execute();
		    while (hadResults) {
		        ResultSet rs = cStmt.getResultSet();
		        hadResults = cStmt.getMoreResults();
		    }
		    int outputValue = cStmt.getInt(2); // index-based

			return outputValue;
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
		return -1;
	}
    
    public static void addTransaction(){
		try{
			stmt = conn.createStatement();
			String sql;
			sql = "insert into transaction(customer_id) values("+cust_id_global+");";
			stmt.executeUpdate(sql);
		}
		catch(Exception e){
			System.out.println("this happened : "+e);
		}
	}
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
//                System.out.println("hi");
                String classpath = System.getProperty("java.class.path");
                System.out.println(classpath);

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL,"dbs_user","password");
                System.out.println("connected");
        }
        catch (Exception e){
                System.out.println("Failed to connect : \n"+e);
        }
        
        
        Login obj=new Login();
        obj.setVisible(true);
        
        //int op = getDomainCost("1001");
        //System.out.println("op = "+op);
        
    }
    
}
