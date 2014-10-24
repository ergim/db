
package db;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author eco
 */
public class gui extends db{
    
    JFrame f;
    JLabel fname, lname, age;
    JTextField t, t1, t2;
    JButton b1=new JButton("Next");
    JButton b2=new JButton("Previus");
    JButton b3=new JButton("Last");
    JButton b4=new JButton("First");
    JButton up=new JButton("Update");
    JButton del=new JButton("Delete");
    JButton nr=new JButton("New");
    JButton save=new JButton("Save");
    
    
    
    public gui(){
        frame();
        btnAction();
    }
    
    public void frame(){
        
        f=new JFrame();
        f.setVisible(true);
        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fname=new JLabel("Firs name");
        lname=new JLabel("Last name");
        age=new JLabel("Age");
        
        t=new JTextField(10);
        t1=new JTextField(10);
        t2=new JTextField(10);
        
        JPanel p=new JPanel();
        p.add(fname);
        p.add(t);
        p.add(lname);
        p.add(t1);
        p.add(age);
        p.add(t2);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(up);
        p.add(del);
        p.add(nr);
        p.add((save));
        
        
        f.add(p);
        
        try{
            rs.next();
            t.setText(rs.getString("Fname"));
            t1.setText(rs.getString("Lname"));
            t2.setText(rs.getString("Age"));
        }catch(Exception e){
            
        }
        
    }
    
    public void btnAction(){
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    if(rs.next()){
                        t.setText(rs.getString("Fname"));
                        t1.setText(rs.getString("Lname"));
                        t2.setText(rs.getString("Age"));
                    }
                    else{
                        rs.previous();
                        JOptionPane.showMessageDialog(null, "No more ");
                    }
                }catch(Exception ex){
                    
                }
            }
        });
        
        b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    if(rs.previous()){
                        t.setText(rs.getString("Fname"));
                        t1.setText(rs.getString("Lname"));
                        t2.setText(rs.getString("Age"));
                    }
                    else{
                        rs.next();
                        JOptionPane.showMessageDialog(null, "No more ");
                    }
                }catch(Exception ex){
                    
                }
            }
        });
        
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    rs.last();
                        t.setText(rs.getString("Fname"));
                        t1.setText(rs.getString("Lname"));
                        t2.setText(rs.getString("Age"));
                    
                    
                }catch(Exception ex){
                    
                }
            }
        });
        
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    rs.first();
                        t.setText(rs.getString("Fname"));
                        t1.setText(rs.getString("Lname"));
                        t2.setText(rs.getString("Age"));
                    
                }catch(Exception ex){
                    
                }
            }
        });
        
        up.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String fname=t.getText();
                String lname=t1.getText();
                String age=t2.getText();
                
                try{
                    rs.updateString("Fname", fname);
                    rs.updateString("Lname", lname);
                    rs.updateString("Age", age);
                    rs.updateRow();
                    
                    JOptionPane.showMessageDialog(null, "Record update");
                    
                }catch(Exception ex){
                    
                }
            }
        });
        
        del.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try{
                    rs.deleteRow();
                    st.close();
                    rs.close();
                    
                    st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);      
                    String sql="select * from Table1";
                    rs=st.executeQuery(sql);
                    
                    rs.next();
                    t.setText(rs.getString("Fname"));
                    t1.setText(rs.getString("Lname"));
                    t2.setText(rs.getString("Age"));
                    
                }catch(Exception ex){
                    
                }
                
            }
        });
        
        nr.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                t.setText("");
                t1.setText("");
                t2.setText("");
            }
        });
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String fname=t.getText();
                String lname=t1.getText();
                String age=t2.getText();
                
                try{
                    rs.moveToInsertRow();
                    rs.updateString("Fname", fname);
                    rs.updateString("Lname", lname);
                    rs.updateString("Age", age);
                    rs.insertRow();
                    
                    st.close();
                    rs.close();
                    
                    st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);      
                    String sql="select * from Table1";
                    rs=st.executeQuery(sql);
                    
                    rs.next();
                    
                    t.setText(rs.getString("Fname"));
                    t1.setText(rs.getString("Lname"));
                    t2.setText(rs.getString("Age"));
                    
                    
                }catch(Exception ex){
                    
                }
            }
        });
    }
}
