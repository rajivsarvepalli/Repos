import java.util.*;

import java.awt.*;
import javax.swing.*;
public class SolverVisualizer extends JPanel {
	private Board intial;
	public SolverVisualizer(Board intial) {//use intial board 
		this.intial = intial;
	}
	public void paint(Graphics g){//painting the graphics
		g.fillRect(100, 100, 400, 400);
		int x = 400/intial.dimension();
		x*=2;//use this to place rectangles properly 
		//System.out.println(x);
		int num = intial.dimension()*intial.dimension();//getting the area of the 
		int size = (int) Math.sqrt(400*400/num*1.0); //size of clear rectangle
		for(int i=100;i<=400;i+=x){
			for(int j=100;j<=400;j+=x){
				g.clearRect(i, j, size, size);
			}
		}
		for(int i=size+100;i<=400;i+=x){
			for(int j=size+100;j<=400;j+=x){
				g.clearRect(i, j, size, size);//clearing rectangles to create a board that looks like chess
			}
		}
		int r = x/2;
		int row =0;
		int col=0;
		Graphics2D g2 = (Graphics2D)g;//making string to palce numbers in boxes that are visible
		g2.setColor(Color.BLUE);
		g2.setFont(getFont().deriveFont(40.0f));
		for(int i = size/2+100; i<=500;i+=r){
			for(int j=size/2+100;j<=500;j+=r){
				g2.drawString(Integer.toString(intial.getArray()[row][col]), i, j);
				row++;
			}
			col++;
			row =0;
		}		
	}
	public static void main(String[] args) {//testing

		int[][] z ={{1,2,8},{0,3,7,},{4,5,6}};
		Board b = new Board(z);
		System.out.println(b.dimension());
		JFrame j = new JFrame();
		j.setSize(600, 600);
		j.getContentPane().add(new SolverVisualizer(b));
		j.setLocationRelativeTo(null);
		j.setBackground(Color.LIGHT_GRAY);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
}
