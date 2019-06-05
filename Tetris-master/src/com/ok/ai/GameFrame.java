package tetris;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameFrame extends JFrame {
	MyPanel mp = new MyPanel();

	NextMyPanel np1 = new NextMyPanel(mp.size - 10, 100, 100, 1);
	NextMyPanel np2 = new NextMyPanel(mp.size - 10, 100, 100, 2);
	JButton startbutton;
	JButton restartbutton;
	JButton pausebutton;
	JButton stopbutton;
	JButton scorebutton;

	boolean flag = false;
	Timer time;
	int gametime;
	JLabel l1,l2,l3,l4;

	GameFrame() {
		super("테트리스");

		JPanel jp = new JPanel();
		l1 = new JLabel("SCORE " + mp.score+"  ");
		l2 = new JLabel("LEVEL : " + mp.level +"  ");
		l3 = new JLabel("RemoveLine :  " + mp.removeline);
		l4 = new JLabel("LEVELl↑ : Speed↑ ");
		jp.add(l1);
		jp.add(l2);
		jp.add(l3);
		jp.add(l4);



		startbutton = new JButton("게임 시작");
		startbutton.setBounds(345, 345, 100, 30);
		startbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sound("사랑은 은하수 다방에서.wav",true);//게임 시작과 동시에 배경음악이 들어감(무한 반복 되게 설정)
				if (flag != true){
					flag = true;
					mp.remutetris = false;
					mp.starts();
					requestFocus();
				}
				requestFocus();
				if(mp.flag ==1)
					setVisible(false);
			}
		});
		pausebutton = new JButton("정지");
		pausebutton.setBounds(345, 380, 100, 30);
		pausebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.pausestop();
				requestFocus();
			}
		});

		restartbutton = new JButton("다시 시작");
		restartbutton.setBounds(345, 415, 100, 30);
		restartbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.restarts();
				requestFocus();
			}
		});

		stopbutton = new JButton("게임 종료");
		stopbutton.setBounds(345, 450, 100, 30);
		stopbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.remutetris = true;
				IDFrame sf = new IDFrame(mp.score);
				setVisible(false);
			}
		});

		scorebutton = new JButton("순위 확인");
		scorebutton.setBounds(345, 485, 100, 30);
		scorebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoreFrame sf =new ScoreFrame();
			}
		});

		np1.setLocation(345, 10);
		np2.setLocation(345, 120);
		mp.setLocation(5, 10);
		jp.setBounds(345, 230, 100, 95);

		add(np1);
		add(np2);
		add(restartbutton);
		add(startbutton);
		add(pausebutton);
		add(stopbutton);
		add(scorebutton);
		add(mp);
		add(jp);
		addKeyListener(mp);

		np1.setBackground(Color.GRAY);
		np2.setBackground(Color.GRAY);
		mp.setBackground(Color.GRAY);
		jp.setBackground(Color.yellow);
		startbutton.setBackground(Color.CYAN);
		pausebutton.setBackground(Color.CYAN);
		restartbutton.setBackground(Color.CYAN);
		stopbutton.setBackground(Color.CYAN);
		scorebutton.setBackground(Color.CYAN);

		this.getContentPane().setBackground(Color.BLACK);


		setBackground(Color.black);
		setBounds(10, 10, 460, 560);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);

		time = new Timer(50, new TimerListener());
		time.start();

		Thread nt1 = new Thread(np1);
		nt1.start();
		nt1.setPriority(3);
		Thread nt2 = new Thread(np2);
		nt2.start();
		nt2.setPriority(3);
	}
	
	public void Sound(String file, boolean Loop){
		Clip clip;
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (Loop)
				clip.loop(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			gametime++;
			l1.setText("SCORE :  " + mp.score+"  ");
			if(mp.level == 6)
				l2.setText("LEVEL : " + mp.level +"[MAX]");
			else
				l2.setText("LEVEL : " + mp.level +"  ");
			l3.setText("RemoveLine :  " + mp.removeline);
		}
	}

}