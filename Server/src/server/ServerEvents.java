package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that implements server and handles database.
 */

public class ServerEvents {
	private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        while (true) new EchoClientHandler(this.serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        this.serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        public void run() {
            try {
                this.out = new PrintWriter(clientSocket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = null;
            while (true) {
                try {
                    inputLine = this.in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (inputLine == null) {
                    break;
                }
                try {
					this.handleInputLine(inputLine, this.out);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
            try {
                this.in.close();
                this.out.close();
                this.clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void handleInputLine(String inputLine, PrintWriter out) throws SQLException {
        	String[] data = inputLine.split("x",4);
        	Connection myConn = null;
    		Statement myStmt = null;
    		ResultSet myRs = null;
    		
            switch (data[0]) {
                case "W":
                	// Write received data to scores database if score id better than 10th score
                	try {
            			// Get a connection to database
                		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pongmaster?useLegacyDatetimeCode=false&serverTimezone=UTC", "student" , "student");
            			// Create a statement
                		myStmt = myConn.createStatement();
            			// Execute SQL query
            			myRs = myStmt.executeQuery("SELECT MIN(tempscores.score) AS min_in_top, COUNT(*) AS records FROM (SELECT scores.score FROM scores ORDER BY scores.score DESC, scores.date DESC LIMIT 10) tempscores");
            			int minInTop10 = 0, records = 0;
            			while (myRs.next()) {
            				try {
            				minInTop10 = Integer.parseInt(myRs.getString("min_in_top"));
            				records = Integer.parseInt(myRs.getString("records"));
            				}
            				catch(Exception e) {
            				}
            			}
            			int scoreFromData = Integer.parseInt(data[2]);
            			// Write data if score is bigger than the lowest from top 10 or there are less than 10 recors
            			if(scoreFromData > minInTop10 || records < 10) {
                			String insertScore = "INSERT INTO scores VALUES(\"" + data[1] + "\", " + data[2] + ", \"" + data[3] + "\")";          			
                			myStmt.executeUpdate(insertScore);
                        	out.println("Scores saved.");
            			}
            			else {
            				out.println("Not a highscore.");
            			}         				
            		}
            		catch (Exception exc) {
            			exc.printStackTrace();
            			out.println("Scores not saved.");
            		}
                	finally {
            			if (myRs != null) {
            				myRs.close();
            			}
            			if (myStmt != null) {
            				myStmt.close();
            			}
            			if (myConn != null) {
            				myConn.close();
            			}
            		}
                	break;
                case "R":
        			StringBuilder top10 = new StringBuilder("");
            		try {
            			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pongmaster?useLegacyDatetimeCode=false&serverTimezone=UTC", "student" , "student");
            			myStmt = myConn.createStatement();
            			myRs = myStmt.executeQuery("SELECT * FROM scores ORDER BY scores.score DESC, scores.date DESC LIMIT 10");
            			int records = 0;
            			while (myRs.next()) {
            				top10.append(myRs.getString("Nick") + "x" + myRs.getString("Score") + "x" + myRs.getString("Date") + "x");
            				records++;
            			}
            			// Write empty records 
            			if(records < 10) {
            				while(10 - records > 0) {
            					top10.append("xxx");
            					records++;
            				}
            			}
            			top10.deleteCharAt(top10.length() - 1);
            		}
            		catch (Exception exc) {
            			exc.printStackTrace();
            		}
            		finally {
            			if (myRs != null) {
            				myRs.close();
            			}
            			if (myStmt != null) {
            				myStmt.close();
            			}
            			if (myConn != null) {
            				myConn.close();
            			}
            		}
                	// Sending data to client
                    out.println(top10);
                    break;
                default:
                    out.println("Unknown case.");
            }
        }
    }
}
