import org.junit.Test;

import encryption.IEncryption;
import encryption.SlideEncryption;

import static org.junit.Assert.*;

public class SlideEncryptionTest {

 @Test
 public void testEncode() {
   String negMsg = "Amazing!";

   String msg =     "This is a secret message! Shhh";
   String slide1 = "hThis is a secret message! Shh";
   String slideNeg = "mazing!A";
   String hello = "Hello";
   String hello3 = "loHel";

   IEncryption slideEncryption = new SlideEncryption(msg);
   IEncryption leaveAlone2 = new SlideEncryption(msg);
   IEncryption negative2 = new SlideEncryption(negMsg);
   IEncryption sHello = new SlideEncryption(hello);

   slideEncryption.encode(1);
   leaveAlone2.encode(0);
   negative2.encode(-1);
   sHello.encode(-3);


   assertEquals("Wrong Slide Encryption",slide1 + "\n" + msg,
           slideEncryption.toString());

   assertEquals("Wrong Slide Encryption",msg + "\n" + msg,
           leaveAlone2.toString());

   assertEquals("Wrong Slide Encryption",slideNeg + "\n" + negMsg,
           negative2.toString());
   assertEquals("Wrong Slide Encryption (-3)",hello3 + "\n" + hello,
           sHello.toString());
 }

  @Test
  public void testRollEncodeTwice() {
    String negMsg = "Amazing!";
    IEncryption slideEncryption = new SlideEncryption(negMsg);
    slideEncryption.encode(16);
    assertEquals("Wrong Slide Encryption",negMsg + "\n" + negMsg,
            slideEncryption.toString());
  }
}