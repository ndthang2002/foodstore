package com.ttttn.utilities;



import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class SendMail {
  @Autowired
  JavaMailSender mailSender;

  public  void send_Email(String recipientEmail, String link)
      throws MessagingException, UnsupportedEncodingException {
  MimeMessage message = mailSender.createMimeMessage();  
  MimeMessageHelper helper = new   MimeMessageHelper(message);
   
  helper.setFrom("nguyendinhthang23082002@gmail.com", "Food shop Support");
  helper.setTo(recipientEmail);
   
  String subject = "Này! đây là link thay đổi mật khẩu";
   
  String content = "<p>Xin chào,</p>"
          + "<p>Bạn đã yêu cầu đặt  lại mật khẩu của mình.</p>"
          + "<p>Click vào link bên dưới để thay đổi mật khẩu của bạn:</p>"
          + "<p><a href=\"" + link + "\">Thay đổi mật khẩu</a></p>"
          + "<br>"
          + "<p>bỏ qua email này nếu bạn đã nhớ mật khẩu, "
          + "hoặc không phải bạn yêu cầu.</p>";
   
  helper.setSubject(subject);
   
  helper.setText(content, true);
  mailSender.send(message);
  System.out.println("mail da duoc gui");
}
  
//  public  void guimail(String email, String link ) {
//    try {
//    MimeMessage mail = mailSender.createMimeMessage(); 
//    MimeMessageHelper helper = new MimeMessageHelper(mail);
//    helper.setFrom("nguyendinhthang23082002@gmail.com");
//    helper.setTo(email);
//    helper.setReplyTo("nguyendinhthang23082002@gmail.com");
//    helper.setSubject("Chào bạn ");
//    helper.setText("Mật khẩu của bạn là: vui lòng không cung cấp mật khẩu này cho ai 123"+link+"an di con gi nua");
//    // gui mail
//    mailSender.send(mail);
//  
//  }catch (Exception e) {
//    e.printStackTrace();
//    System.out.println("loi gui mail");
//    // TODO: handle exception
//  }
//  }
}
