import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class GameFrame extends JFrame {
	private TextSource textSource = new TextSource("words.txt");
	private RecordSource recordSource = new RecordSource("records.dat");
	private ScorePanel scorePanel = new ScorePanel(recordSource);
	private EditPanel editPanel = new EditPanel(textSource);
	private GamePanel gamePanel = new GamePanel(scorePanel, editPanel, textSource);
	private MenuPanel menuPanel = new MenuPanel();

	private ImageIcon pressedIcon = new ImageIcon("pressed.gif");
	private ImageIcon overIcon = new ImageIcon("over.gif");
	
	private JMenuItem startItem = new JMenuItem("start");
	private JMenuItem stopItem = new JMenuItem("stop");

	JSplitPane hPane = new JSplitPane();
	JSplitPane pPane = new JSplitPane();
	
	public GameFrame() {
		setTitle("Ÿ���� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		splitPane2();
		//recordSource.addRecords(new User("test", 100));
		//add(new MenuPanel());

		//splitPane(); // JSplitPane�� �����Ͽ� ����Ʈ���� CENTER�� ����
		//makeMenu();

		//makeToolBar();
		setResizable(false);
		setVisible(true);
	}

	public void reset() {
		gamePanel = new GamePanel(scorePanel, editPanel, textSource);
		getContentPane().removeAll();
		splitPane2();
		scorePanel.update();
		revalidate();
		repaint();
	}

	public void gameOver(int score) {
		gamePanel = new GamePanel(scorePanel, editPanel, textSource);
		getContentPane().removeAll();
		getContentPane().add(new GameOverPanel(score, recordSource));
		revalidate();
		repaint();
	}

	private void splitPane() {
		getContentPane().add(hPane, BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(550);
		hPane.setEnabled(false);
		hPane.setLeftComponent(gamePanel);


	}

	private void splitPane2() {

		getContentPane().add(hPane, BorderLayout.CENTER);
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(550);
		hPane.setEnabled(false);
		hPane.setLeftComponent(menuPanel);


		pPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pPane.setDividerLocation(300);
		pPane.setTopComponent(scorePanel);
		pPane.setBottomComponent(editPanel);
		hPane.setRightComponent(pPane);
	}

	private void makeMenu() {
		JMenuBar mBar = new JMenuBar();
		setJMenuBar(mBar);
		JMenu fileMenu = new JMenu("Game");
		fileMenu.add(startItem);
		fileMenu.add(stopItem);
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("exit"));
		mBar.add(fileMenu);
		
		//startItem.addActionListener(new StartAction());
	}

	class MenuPanel extends JPanel {
		private ImageIcon normalIcon;

		public MenuPanel() {
			setLayout(null);

			ImageIcon originIcon = new ImageIcon("start.png");
			Image originImg = originIcon.getImage();
			Image changedImg= originImg.getScaledInstance(120, 55, Image.SCALE_SMOOTH );
			normalIcon = new ImageIcon(changedImg);

			this.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {


					JButton startBtn = new JButton(normalIcon);
					startBtn.setOpaque(false);
					startBtn.setBorderPainted(false);
					startBtn.setSize(100, 50);
					startBtn.setLocation(getWidth()/2 - 50, getHeight()/2);

					startBtn.addActionListener(new StartAction());

					MenuPanel.this.add(startBtn);

					// ready
					MenuPanel.this.removeComponentListener(this);
				}
			});


		}

		private class StartAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				GameFrame.this.remove(MenuPanel.this);
				getContentPane().removeAll();
				getContentPane().add(gamePanel);
				GameFrame.this.revalidate();
				GameFrame.this.repaint();


			}
		}


	}
	
}
