import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	private JTextField input = new JTextField(40);
	private JLabel text = new JLabel("타이핑해보세요"); 
	private ScorePanel scorePanel = null;
	private EditPanel editPanel = null;
	private TextSource textSource = new TextSource(); // 단어 벡터 생성
	
	public GamePanel(ScorePanel scorePanel, EditPanel editPanel) {
		this.scorePanel = scorePanel;
		this.editPanel = editPanel;
		
		setLayout(new BorderLayout());
		add(new GameGroundPanel(), BorderLayout.CENTER);
		add(new InputPanel(), BorderLayout.SOUTH);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)(e.getSource());
				String inWord = t.getText();
				if(text.getText().equals(inWord)) { // 맞추기 성공
					// 점수 올리기
					scorePanel.increase();
					startGame();
					
					//input 창 지우기
					t.setText("");
				}
			}
		});
	}
	
	public void startGame() {
		// 단어 한 개 선택
		String newWord = textSource.get();
		text.setText(newWord);
		text.setBackground(Color.GREEN);
		text.setOpaque(true);
	}
	
	class GameGroundPanel extends JPanel {
		public GameGroundPanel() {
			setLayout(null);
			text.setSize(100, 30);
			text.setLocation(100,  10);
			add(text);
		}
	}
	
	class InputPanel extends JPanel {
		public InputPanel() {
			setLayout(new FlowLayout());
			this.setBackground(Color.CYAN);
			add(input);
		}
	}

}
