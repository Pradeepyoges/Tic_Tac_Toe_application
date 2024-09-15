import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardwidth=600;
    int boardheight=650; //50 px for text on top

    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel=new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPanel=new JPanel();
    //create a button for 3*3 matrix
    JButton[][] board =new JButton[3][3];
    String playerX="X";
    String playerO="O";
    String currentplayer=playerX;
    boolean gameover=false;
    int turn=0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TIC-TAC-TOE");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.BLACK);
        frame.add(boardPanel);

        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                JButton tile=new JButton();
                board[r][c]=tile;
                boardPanel.add(tile);

                tile.setBackground(Color.WHITE);
                tile.setForeground(Color.BLUE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if (gameover) {
                            return;
                        }
                        JButton tile=(JButton) e.getSource();
                        if(tile.getText()==""){
                            tile.setText(currentplayer);
                            turn++;
                            checkWinner();
                            if(!gameover){
                                currentplayer= currentplayer==playerX?playerO:playerX;
                                textLabel.setText(currentplayer+"'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }
  void checkWinner(){
    //check for horizontal
    for(int r=0;r<3;r++){
        if(board[r][0].getText()=="") continue;
        if(board[r][0].getText()==board[r][1].getText() && board[r][1].getText()==board[r][2].getText()){
            for(int i=0;i<3;i++){
                setWinner(board[r][i]);
            }
            gameover=true;
            return;
        }
    }

    //check for vertical
    for(int c=0;c<3;c++){
        if(board[0][c].getText()=="") continue;
        if(board[0][c].getText()==board[1][c].getText() && board[1][c].getText()==board[2][c].getText()){
            for(int i=0;i<3;i++){
                setWinner(board[i][c]);
            }
            gameover=true;
            return;
        }
    }

    //check for diagonal
    if(board[0][0].getText()==board[1][1].getText() && board[1][1].getText()==board[2][2].getText() && board[0][0].getText()!=""){
       for(int i=0;i<3;i++){
        setWinner(board[i][i]);
       }
       gameover=true;
       return;
    }

    //check for antidiagonal
    if(board[0][2].getText()==board[1][1].getText() && board[1][1].getText()==board[2][0].getText() && board[0][2].getText()!=""){
        setWinner(board[0][2]);
        setWinner(board[1][1]);
        setWinner(board[2][0]);
        gameover=true;
        return;
     }

     //check for draw
     if(turn==9){
        for(int r=0;r<3;r++){
            for(int c=0;c<3;c++){
                setTie(board[r][c]);
            }
        }gameover=true;
     }
  }
   
  void setWinner(JButton tile){
    tile.setBackground(Color.GRAY);
    tile.setForeground(Color.GREEN);
    textLabel.setText(currentplayer+ " is the Winner!");
  }
  
  void setTie(JButton tile){
    tile.setForeground(Color.ORANGE);
    tile.setBackground(Color.GRAY);
    textLabel.setText("Tie!");
  }

}
