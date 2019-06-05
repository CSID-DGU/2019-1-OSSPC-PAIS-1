package tetris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ScoreFrame extends JFrame{
	Scanner file;
	JLabel j[] = new JLabel[12];
	
	ScoreFrame(String name,int sc) {
		super("기록");
		
		ArrayList<UserList> rank = new ArrayList<UserList>();
		
		String id = name;
		int score = sc;
		int length = 0;
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter("score.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.append(id +" "+ sc);
		pw.println();
		pw.flush();
		
		try {
			file = new Scanner(new File("score.txt"));
			String line;
			while (file.hasNext()) {
				line = file.nextLine();
				Scanner lineScan = new Scanner(line);
				String id1 = lineScan.next();
				int num  = lineScan.nextInt();
				rank.add(new UserList(id1, num));
				length++;
			}
		} catch (Exception ex) {
			System.out.println("파일을 여는데 문제가 생겼습니다");
		}
		
		for(int i=0;i<length;i++){
			for(int j=0;i<length-j;j++){
				Collections.sort(rank);
			}
		}
		
		j[0] = new JLabel("ID : " + name +",   MY SCORE : " + sc);
		j[1] = new JLabel("-------------------------------------------");
		
		for(int i=0; i<10; i++){
			if(length<=i)
				break;
			else
				j[i+2] = new JLabel((i+1)+"등!  ID : " + rank.get(i).getID() + ", SCORE : " + rank.get(i).getScore());

		}
		for(int i = 0; i<12; i++){
		add(j[i]);
		}
		
		getContentPane().setBackground(Color.yellow);
		setBounds(35, 5, 1295, 725);
		setLayout(new FlowLayout());
		setVisible(true);
	}

	ScoreFrame() {
		super("기록");	
		ArrayList<UserList> rank = new ArrayList<UserList>();
		int length = 0;
		
		try {
			file = new Scanner(new File("score.txt"));
			String line;
			while (file.hasNext()) {
				line = file.nextLine();
				Scanner lineScan = new Scanner(line);
				String id1 = lineScan.next();
				int num  = lineScan.nextInt();
				rank.add(new UserList(id1, num));
				length++;
			}
		} catch (Exception ex) {
			System.out.println("파일을 여는데 문제가 생겼습니다");
		}
		
		for(int i=0;i<length;i++){
			for(int j=0;i<length-j;j++){
				Collections.sort(rank);
			}
		}
		
		j[0] = new JLabel("점 수 확 인 ");
		j[1] = new JLabel("-------------------------------------------");
		
		for(int i=0; i<10; i++){
			if(length<=i)
				break;
			else
				j[i+2] = new JLabel((i+1)+"등!  ID : " + rank.get(i).getID() + ", SCORE : " + rank.get(i).getScore());

		}
		for(int i = 0; i<12; i++){
			add(j[i]);
		}
		
		getContentPane().setBackground(Color.yellow);
		setBounds(35, 5, 1295, 725);
		setLayout(new FlowLayout());
		setVisible(true);	
	}	
}
