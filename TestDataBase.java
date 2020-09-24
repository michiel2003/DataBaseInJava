package test;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestDataBase {
	
	public static void main(String[] args) {
		TestDataBase exec = new TestDataBase();
		exec.frame();
	}

	private static List<String> getDBinfo(int columnindex, String command) {
		try {
			List<String> dbitems = new ArrayList<String>();
			Connection dbconnect = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mdecap\\AppData\\Roaming\\DBeaverData\\workspace6\\.metadata\\sample-database-sqlite-1\\Chinook.db");
			PreparedStatement prepstate = dbconnect.prepareStatement(command);
			prepstate.execute();
			ResultSet result = prepstate.getResultSet();
			while(result.next()) {
				String text = result.getString(columnindex);
				dbitems.add(text);
			}
			return dbitems;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("an error ocurred");
		}
		return null;
	}
	
	public void frame() {
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JList list1 = new JList(getDBinfo(1, "select * from Artist").toArray());
		JList list2 = new JList(getDBinfo(2, "select * from Album" ).toArray());
		JScrollPane scrollList2 = new JScrollPane(list2);
		JScrollPane scrollList1 = new JScrollPane(list1);
		frame.add(panel);
		panel.add(scrollList1);
		panel.add(scrollList2);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setVisible(true);
	}
	
	

}
