package shell;



import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
 
 
public class ConnectSSH {
 

	  public static void main(String[] arg){
	    try{
	      JSch jsch=new JSch();  
	      
	      JFileChooser chooser = new JFileChooser();
	      chooser.setDialogTitle("Choose your privatekey(ex. ~/.ssh/id_dsa)");
	      chooser.setFileHidingEnabled(false);
	      int returnVal = chooser.showOpenDialog(null);
	      if(returnVal == JFileChooser.APPROVE_OPTION) {
	        System.out.println("You chose "+
				   chooser.getSelectedFile().getAbsolutePath()+".");
	        jsch.addIdentity(chooser.getSelectedFile().getAbsolutePath()
				 , "Imran"
				 );
	      }
	 
	      String host=null;
	      if(arg.length>0){
	        host=arg[0];
	      }
	      else{
	        host=JOptionPane.showInputDialog("Enter username@hostname",
	                                         System.getProperty("user.name")+
	                                         "@localhost"); 
	      }
	      String user=host.substring(0, host.indexOf('@'));
	      host=host.substring(host.indexOf('@')+1);
	 
	      Session session=jsch.getSession(user, host, 22);
     
	      /*
	      String xhost="127.0.0.1";
	      int xport=0;
	      String display=JOptionPane.showInputDialog("Enter display name", 
	                                                 xhost+":"+xport);
	      xhost=display.substring(0, display.indexOf(':'));
	      xport=Integer.parseInt(display.substring(display.indexOf(':')+1));
	      session.setX11Host(xhost);
	      session.setX11Port(xport+6000);
	      */
	 
	      // username and password will be given via UserInfo interface.
	      UserInfo ui=new MyUserInfo();
	      session.setUserInfo(ui);
	      session.connect();
	 
	      String command=JOptionPane.showInputDialog("Enter command", 
	                                                 "set|grep SSH");
	 
	      Channel channel=session.openChannel("exec");
	      ((ChannelExec)channel).setCommand(command);
	 
	      // X Forwarding
	      // channel.setXForwarding(true);
	 
	      //channel.setInputStream(System.in);
	      channel.setInputStream(null);
	 
	      //channel.setOutputStream(System.out);
	 
	      //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
	      //((ChannelExec)channel).setErrStream(fos);
	      ((ChannelExec)channel).setErrStream(System.err);
	 
	      InputStream in=channel.getInputStream();
	 
	      channel.connect();
	 
	      byte[] tmp=new byte[1024];
	      while(true){
	        while(in.available()>0){
	          int i=in.read(tmp, 0, 1024);
	          if(i<0)break;
	          System.out.print(new String(tmp, 0, i));
	        }
	        if(channel.isClosed()){
	          if(in.available()>0) continue; 
	          System.out.println("exit-status: "+channel.getExitStatus());
	          break;
	        }
	        try{Thread.sleep(1000);}catch(Exception ee){}
	      }
	      channel.disconnect();
	      session.disconnect();
	    }
	    catch(Exception e){
	      System.out.println(e);
	    }
	  }
	 
	  public static class MyUserInfo implements UserInfo, UIKeyboardInteractive{
	    public String getPassword(){ return passwd; }
	    public boolean promptYesNo(String str){
	      Object[] options={ "yes", "no" };
	      int foo=JOptionPane.showOptionDialog(null, 
	             str,
	             "Warning", 
	             JOptionPane.DEFAULT_OPTION, 
	             JOptionPane.WARNING_MESSAGE,
	             null, options, options[0]);
	       return foo==0;
	    }
	  
	    String passwd;
	    JTextField passwordField=(JTextField)new JPasswordField(20);
	 
	    public String getPassphrase(){ return null; }
	    public boolean promptPassphrase(String message){ return true; }
	    public boolean promptPassword(String message){
	      Object[] ob={passwordField}; 
	      int result=
	        JOptionPane.showConfirmDialog(null, ob, message,
	                                      JOptionPane.OK_CANCEL_OPTION);
	      if(result==JOptionPane.OK_OPTION){
	        passwd=passwordField.getText();
	        return true;
	      }
	      else{ 
	        return false; 
	      }
	    }
	    public void showMessage(String message){
	      JOptionPane.showMessageDialog(null, message);
	    }
	    final GridBagConstraints gbc = 
	      new GridBagConstraints(0,0,1,1,1,1,
	                             GridBagConstraints.NORTHWEST,
	                             GridBagConstraints.NONE,
	                             new Insets(0,0,0,0),0,0);
	    private Container panel;
	    public String[] promptKeyboardInteractive(String destination,
	                                              String name,
	                                              String instruction,
	                                              String[] prompt,
	                                              boolean[] echo){
	      panel = new JPanel();
	      panel.setLayout(new GridBagLayout());
	 
	      gbc.weightx = 1.0;
	      gbc.gridwidth = GridBagConstraints.REMAINDER;
	      gbc.gridx = 0;
	      panel.add(new JLabel(instruction), gbc);
	      gbc.gridy++;
	 
	      gbc.gridwidth = GridBagConstraints.RELATIVE;
	 
	      JTextField[] texts=new JTextField[prompt.length];
	      for(int i=0; i<prompt.length; i++){
	        gbc.fill = GridBagConstraints.NONE;
	        gbc.gridx = 0;
	        gbc.weightx = 1;
	        panel.add(new JLabel(prompt[i]),gbc);
	 
	        gbc.gridx = 1;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        gbc.weighty = 1;
	        if(echo[i]){
	          texts[i]=new JTextField(20);
	        }
	        else{
	          texts[i]=new JPasswordField(20);
	        }
	        panel.add(texts[i], gbc);
	        gbc.gridy++;
	      }
	 
	      if(JOptionPane.showConfirmDialog(null, panel, 
	                                       destination+": "+name,
	                                       JOptionPane.OK_CANCEL_OPTION,
	                                       JOptionPane.QUESTION_MESSAGE)
	         ==JOptionPane.OK_OPTION){
	        String[] response=new String[prompt.length];
	        for(int i=0; i<prompt.length; i++){
	          response[i]=texts[i].getText();
	        }
		return response;
	      }
	      else{
	        return null;  // cancel
	      }
	    }
	  }
	}
    /**
     * @param args
     */
//    public static void main(String[] args) {
//        String host= "10.36.21.4";
//        String user="mohammei";
//        String password="mohammei";
//        String command1="ls -ltr";
//        try{
//             
//            java.util.Properties config = new java.util.Properties(); 
//            config.put("StrictHostKeyChecking", "no");
//            JSch jsch = new JSch();
//            Session session=jsch.getSession(user,host, 22);
//            session.connect();
//            System.out.println("Connected");
//            
//           // session.setPassword(password);
//            session.setConfig(config);
//           // session.connect();
//           // System.out.println("Connected");
//             
//            Channel channel=session.openChannel("exec");
//            ((ChannelExec)channel).setCommand(command1);
//            channel.setInputStream(null);
//            ((ChannelExec)channel).setErrStream(System.err);
//             
//            InputStream in=channel.getInputStream();
//            channel.connect();
//            byte[] tmp=new byte[1024];
//            while(true){
//              while(in.available()>0){
//                int i=in.read(tmp, 0, 1024);
//                if(i<0)break;
//                System.out.print(new String(tmp, 0, i));
//              }
//              if(channel.isClosed()){
//                System.out.println("exit-status: "+channel.getExitStatus());
//                break;
//              }
//              try{Thread.sleep(1000);}catch(Exception ee){}
//            }
//            channel.disconnect();
//            session.disconnect();
//            System.out.println("DONE");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
// 
//    }
 
